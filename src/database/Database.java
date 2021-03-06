package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database 
{
    private final BaseSetting bs = new BaseSetting();
    private Connection connection = null;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/BMG_db";
    private String login = "Joseph";
    private String password = "j0j0";
    
    
    public Database() {
        this.driver = bs.getDriver();
        this.url = bs.getUrl();
        this.login = bs.getLogin();
        this.password = bs.getPassword();
        this.connection = bs.getConnection();
    }
    
    public Database(String lgn, String pswd) 
    {
	this.login = lgn;
	this.password = pswd;
        this.driver = bs.getDriver();
        this.url = bs.getUrl();
        this.login = bs.getLogin();
        this.password = bs.getPassword();
        this.connection = bs.getConnection();
    }
    
    public boolean disconnect()
    {
	boolean res = false;
	
	try 
	{
	    connection.close();
	    res = connection.isClosed();
	} catch (SQLException sqle) 
	{
	    System.out.println("SQL Exception in disconnect method");
	    sqle.printStackTrace();
	}
	
	return res;
    }
    
    public boolean connect()
    {
	boolean res = false;
	
	try
	{
	    Class.forName(driver);
	    connection = DriverManager.getConnection(url,login,password);
	    res = !(connection.isClosed());
	}
	catch (SQLException sqle)
	{
	    System.out.println("SQL Exception in connect method");
	    sqle.printStackTrace();
	} catch (ClassNotFoundException cnfe) {
            System.out.println("ClassNotFound Exception in connect method");
	    cnfe.printStackTrace();
        }
	return res;
    }
    
    public Connection deconnection()
    {
	if (connection != null)
	    disconnect();
	
	return connection;
    }
    
    public Connection getConnection()
    {
	return this.connection;
    }

}
