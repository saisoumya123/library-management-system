package com.capgemini.librarymanagementsystemjdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.capgemini.librarymanagementsystemjdbc.dto.AdminBean;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;

public class UserDAOImp implements UserDAO{

	

	@Override
	public int register(UserBean bean) {
		int rs1 = 0;
		 try { 
			  try(FileInputStream fin = new FileInputStream("db.properties")){
		  
		  Properties pro = new Properties();
		  pro.load(fin);
		  Class.forName(pro.getProperty("path")).newInstance(); 
		 UserBean user =  new UserBean();
		  try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro.getProperty("user"),pro.getProperty("password"))){ 
			  String query = pro.getProperty("register_admin"); 
			  System.out.println(query);
			  try( PreparedStatement pstmt=conn.prepareStatement(query);){ 
				  
		  pstmt.setString(1, bean.getName());
		  
		  pstmt.setString(2, bean.getEmail());
		  pstmt.setString(3, bean.getPassword());
		  pstmt.setLong(4, bean.getMobile());
		  pstmt.setString(5, bean.getRole());
		  pstmt.setInt(6, bean.getId());
		  System.out.println("rs");
		  rs1=pstmt.executeUpdate();
		  System.out.println(rs1);
		  } } }
		  
		  }catch(Exception e) {
			  //e.printStackTrace();
			 System.out.println("invalid"); 
			  } 
		 
		return rs1;
		
	}

	@Override
	public String auth(String email, String password) {
		//boolean authorized = false;
		String var1 = null;
		try { 
			  try(FileInputStream fin = new FileInputStream("db.properties")){
		  
		  Properties pro = new Properties();
		
		  pro.load(fin);
		  Class.forName(pro.getProperty("path"));
		  try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro.getProperty("user"), pro.getProperty("password"))){
			  String query = pro.getProperty("auth_admin");
			  
			  try(PreparedStatement pstmt = conn.prepareStatement(query)){
				  pstmt.setString(1, email);
				  pstmt.setString(2, password);
				  
				  try(ResultSet rs = pstmt.executeQuery()){
					  while(rs.next()) {
						 
						  String db_email = rs.getString(1);
						  String db_password = rs.getString(2);
						  
						  if(db_email.equals(email) && db_password.equals(password)) {
							  //authorized=true;
							  
							  System.out.println("login succesful"); 
							  String query1 = pro.getProperty("role_que");
								PreparedStatement  pstmt1 = conn.prepareStatement(query1);
								pstmt1.setString(1, email);
								  ResultSet rs1 = pstmt1.executeQuery();
								  if(rs1.next()) {
								 if(rs1.getString(1).equalsIgnoreCase("admin")){
									var1 = "admin";
								 }else {
									  var1 = "student";
								 }
						  }
							  
						  else {
							  var1="invalid";
							//  System.out.println("invalid login");
						  }
						  }
					  }
					  
				  }
			  }
		  }
			  }
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	
		return var1;
	
	}
			}
		
