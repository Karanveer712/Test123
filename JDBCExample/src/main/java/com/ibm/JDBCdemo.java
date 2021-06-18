package com.ibm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCdemo {
	
	//jdbc driver name and database url
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/world";
	
	//database credentials
	static final String USERNAME = "root";
	static final String PASSWORD = "1234";


	public static void main(String[] args) throws SQLException {
		
		JDBCdemo jdbcDemo = new JDBCdemo();
		jdbcDemo.getCityInformation();
		
	
	}
	
	private void getCityInformation() throws SQLException {
		Connection connection = null;
		Statement stmt = null;
		try
		{
			//step 1: register jdbc
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//step 2 Open a connection
			
			System.out.println("Connecting to database");
			connection = DriverManager.getConnection(DB_URL, USERNAME , PASSWORD);
			
			//step 3 execute a querry
			
			stmt = connection.createStatement();
			
			
	String sql = "select ID,Name,CountryCode,District, Population from city where id<21 ";
	
	// select * from city		
			ResultSet rs = stmt.executeQuery(sql);
			
			//step 4 extract data from result set
			while(rs.next()) {
				//retreive by column name
				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				String countrycode = rs.getString("CountryCode");
				String district = rs.getString("District");
				int population = rs.getInt("Population");
				
				//Display values
				System.out.println("ID: "+ id);
				System.out.println(" Name: "+ name);
				System.out.println("CountryCode: "+ countrycode);
				System.out.println("District: "+ district);
				System.out.println("Population: "+ population );
			}
			// step 5 close resources
			
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch( ClassNotFoundException e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
	}

}
