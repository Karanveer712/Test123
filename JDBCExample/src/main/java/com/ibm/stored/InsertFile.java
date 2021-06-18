package com.ibm.stored;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertFile {

	public static void main(String[] args) {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/jdbc_example";
			String username = "root";
			String password = "1234";
			
              Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url,username,password);
			
			//for insert file
			
			PreparedStatement ps = con.prepareStatement("insert into filedb values(?,?)");
			File f = new File("D:\\JDBC\\workspace\\JDBCExample\\src\\main\\resources\\textfile.txt");
			
			FileReader fr = new FileReader(f);
			
			ps.setInt(1,109);
			ps.setCharacterStream(2,fr,(int)f.length());
			
			int i = ps.executeUpdate();
			
			System.out.println(i + " records affected");
			
			//after running check database
			
			//retreive file
			// create filedb table
			
			PreparedStatement ps1 = con.prepareStatement("select * from filedb");
			
			ResultSet rs = ps1.executeQuery();
			rs.next(); // now on 1st row
			
			Clob c = rs.getClob(2);  // text character
			
			Reader r = c.getCharacterStream();
			
			//create a file content will store from db to file
			
			FileWriter fw = new FileWriter("D:\\JDBC\\workspace\\JDBCExample\\src\\main\\resources\\RetreiveFile.txt");
			
			int a;
			while((a=r.read())!=-1)
				fw.write((char)a);
			
			fw.close();
			
			System.out.println("success");
			con.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}

	}

}
