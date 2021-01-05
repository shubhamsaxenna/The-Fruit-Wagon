package database;

import java.sql.Connection;
import java.sql.DriverManager;
public class Connect
{
	static Connection con;
	public static Connection connect()
	{
		try
	    {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/game","root","1234");
		}				 
        catch(Exception e)
		{
			System.out.println("Connect method error");	
		}
	return con;
	}
}
