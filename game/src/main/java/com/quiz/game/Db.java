package com.quiz.game;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {
	
	public  Connection getConnection(){
		Connection con=null;
		try {
			
			//load DriverManager
			Class<?> fn = Class.forName("org.postgresql.Driver");
			if(fn != null) {
				System.out.println("Connection is established ");
			}else {
				System.out.println("connection is not done ");
			}
		
			//create connection obj
			//	String url="jdbc:mysql://localhost:3307/students";
				String url="jdbc:postgresql://localhost:5432/game";
				String un="postgres";
				String pwd="bcp";
				String urll=url+"?user=root&password=";
				con = DriverManager.getConnection(url,un,pwd);
				System.out.println(con);
				
				if(con!=null) {
					System.out.println("connection with url"+url);
				}
			} catch (Exception e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//return null;
		return con;
	}

}
