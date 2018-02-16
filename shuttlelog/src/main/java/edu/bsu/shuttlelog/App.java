package edu.bsu.shuttlelog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
	
			
    public static void main( String[] args )
    {
    	final String JDBCURL = "jdbc:mysql://csor12c.dhcp.bsu.edu:3306/yliu12";
    	final String USER = "yliu12";
    	final String PASSWORD = "1234";
    	
        System.out.println( "Hello World!" );
        try {
			Connection myConn = DriverManager.getConnection(JDBCURL,USER, PASSWORD);
	        System.out.println( "CONNECT SUCCESSFUL!" );

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
