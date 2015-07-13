package action;   
  
import java.sql.Connection;   
import java.sql.DriverManager;   
import java.sql.PreparedStatement;   
import java.sql.ResultSet;  
import java.util.Map;  

  



import javax.servlet.http.HttpSession;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport; 

import model.User;
import util.DbUtil;

  
/**  
 * @author Dinesh Rajput  
 *  
 */  
@SuppressWarnings("serial")   
public class LoginAction  extends ActionSupport{   
 private String username;   
    private String password;   
 
       
 public String execute() {   
  String ret = ERROR;   
       Connection conn = null;   
  
       try {   
          String URL = "url";   
          Class.forName("driver");   
          conn = DriverManager.getConnection(URL, "userName", "password");   
          String sql = "SELECT username FROM users WHERE";   
          sql+=" username = ? AND password = ?";   
          PreparedStatement ps = conn.prepareStatement(sql);   
          ps.setString(1, username);   
          ps.setString(2, password);   
          ResultSet rs = ps.executeQuery();   
  
          while (rs.next()) {   
           username = rs.getString(1);   
           ret = SUCCESS;   
          }   
       } catch (Exception e) {   
        addActionError(getText("error.login"));     
          ret = ERROR;   
       } finally {   
          if (conn != null) {   
             try {   
                conn.close();   
             } catch (Exception e) {   
             }   
          }   
       }   
       return ret;   
    }   

 
/* public String logout() throws Exception {
	 
		HttpSession session = request.getSession();
		session.invalidate();		
	 
		return SUCCESS;
	}*/
    
}  
