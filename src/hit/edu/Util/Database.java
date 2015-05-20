package hit.edu.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
	public static final String url="jdbc:mysql://127.0.0.1/mrp";
	public static final String name="com.mysql.jdbc.Driver";
	public static final String user="root";
	public static final String password="root";
	
	public Connection conn = null;  
    
    public Connection DatabaseConn(){
    	try{
    		Class.forName(name);
    		conn = DriverManager.getConnection(url, user, password);
    		return conn;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace(); 
    		return null;
    	}
    }
    
    public void close() {  
        try
        {  
            this.conn.close();
        } 
        catch (SQLException e) 
        {  
            e.printStackTrace();  
        }  
    }  
}
