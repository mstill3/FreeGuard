
package freeguard;

import java.sql.*;

public class Connectivity
{ 
	   
	    public static void main(String[] args) //main method
	    {
                
                DatabaseManager db = new DatabaseManager();
//                db.runQuery("use mydatabase");
//                String query = "select password from User where username = 'mstill3'";
                String query = "use mydatabase; show tables";
                ResultSet rs = db.getQueryValue(query);
                System.out.println(DatabaseManager.getString(rs));
            }

}


