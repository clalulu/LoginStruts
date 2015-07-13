package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbUtil {
	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn != null) {
			return conn;
		} else {
			try {
				InputStream inputStream = DbUtil.class.getClassLoader()
						.getResourceAsStream("db.properties");
				Properties properties = new Properties();
				if (properties != null) {
					properties.load(inputStream);

					String dbDriver = properties.getProperty("Driver");
					String url = properties
							.getProperty("url");
					String userName = properties.getProperty("userName");
					String password = properties.getProperty("password");

					Class.forName(dbDriver).newInstance();
					conn = DriverManager.getConnection(url,
							userName, password);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
	}
}