package com.capgemini.librarymanagementsystemjdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import com.capgemini.librarymanagementsystemjdbc.exception.AdminException;

import com.capgemini.librarymanagementsystemjdbc.dto.AdminBean;
import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.StudentBean;

public class AdminDAOImp implements AdminDAO{
	
	
	
	@Override
	public int update(BookBean book) {
		int count=0;
			try {
		try(FileInputStream	fin = new FileInputStream("db.properties")){
			
			Properties pro = new Properties();
			pro.load(fin);
		
			Class.forName(pro.getProperty("path"));

			try(Connection conn =DriverManager.getConnection(pro.getProperty("dburl"),pro.getProperty("user"),pro.getProperty("password"));){ 
				String query = pro.getProperty("update"); 
				try(	PreparedStatement pstmt=conn.prepareStatement(query);){
					pstmt.setString(1,book.getName());
					pstmt.setInt(2, book.getId());
					
					 count = pstmt.executeUpdate();
					System.out.println(count);

				}
			}
		}}
		catch (Exception e) {
			e.printStackTrace();
		
		}
		return count;
	}

	@Override
	public int delete(int id) {
		int count = 0;
		try {
			try(FileInputStream fin = new FileInputStream("db.properties")){
				  
				  Properties pro = new Properties(); 
				  pro.load(fin);
				  
				  Class.forName(pro.getProperty("path")).newInstance();
				  try(Connection conn =DriverManager.getConnection(pro.getProperty("dburl"),pro.getProperty("user"),pro.getProperty("password"));){ 
				String query = pro.getProperty("delete"); 
				try(	PreparedStatement pstmt=conn.prepareStatement(query);){
					
					//pstmt.setInt(1, id);
					pstmt.setInt(1, id);
					 count = pstmt.executeUpdate();
					
				}
			}
		}}
		catch (Exception e) {
			e.printStackTrace();
		//	System.out.println("invalid credentials");
		}
		
		
		
		return count;
	}

	@Override
	public int addBook(BookBean info) {
		int count=0;
		try {
			try(FileInputStream fin = new FileInputStream("db.properties")){
				  
				  Properties pro = new Properties(); 
				  pro.load(fin);
				  
				  Class.forName(pro.getProperty("path")).newInstance();
				  try(Connection conn =DriverManager.getConnection(pro.getProperty("dburl"),pro.getProperty("user"),pro.getProperty("password"));){ 
				  //String query = "select * from bookdetails where bId=?";
				String query = pro.getProperty("add_book"); 
				try(	PreparedStatement pstmt=conn.prepareStatement(query);){
					//pstmt.setInt(1, info.getId());
					pstmt.setInt(1, info.getId());
					pstmt.setString(2, info.getName());
					pstmt.setString(3, info.getAuthor());
					pstmt.setString(4, info.getCategory());
					pstmt.setString(5, info.getPublishername());
					 count = pstmt.executeUpdate();
					
				}
			}
			}}
		catch (Exception e) {
			e.printStackTrace();
		//	System.out.println("invalid credentials");
		}
		return count;
		
		
		
		
	}

	@Override
	public LinkedList<Integer> getBookIds() {
		try {
		  try(FileInputStream fin = new FileInputStream("db.properties")){
		  
		  Properties pro = new Properties(); 
		  pro.load(fin);
		  
		  Class.forName(pro.getProperty("path")).newInstance();
		//  List<BookBean> li = new LinkedList<BookBean>();
		  List<BookBean> list = new LinkedList<BookBean>();
		  try(Connection conn =DriverManager.getConnection(pro.getProperty("dburl"),pro.getProperty("user"),pro.getProperty("password"));){ 
			  String query =pro.getProperty("get_bookId");
			  try(PreparedStatement pstmt =conn.prepareStatement(query);){
				  ResultSet rs = pstmt.executeQuery(query);
					while(rs.next()) {	
							BookBean bean = new BookBean();
							bean.setId(rs.getInt("id"));
							list.add(bean);
							System.out.println(bean.getId());
					}	  }}
		  } } catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		 
		 
		
		

	}

	@Override
	public List<BookBean> getBooksInfo() {
		try(FileInputStream	fin = new FileInputStream("db.properties")){

			Properties pro = new Properties();
		pro.load(fin);
			List<BookBean> li = new LinkedList<BookBean>();
			Class.forName(pro.getProperty("path")).newInstance();
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro.getProperty("user"),pro.getProperty("password"))){
			String query = pro.getProperty("get_allBook");
				try(Statement stmt = conn.createStatement()){	
					ResultSet rs = stmt.executeQuery(query);
					while(rs.next()) {	
						BookBean bean = new BookBean();
						bean.setId(rs.getInt("id"));
						bean.setName(rs.getString("name"));
						bean.setAuthor(rs.getString("author"));
						bean.setCategory(rs.getString("category"));
						bean.setPublishername(rs.getString("publishername"));
						li.add(bean);
					
					}
					return li;
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		

	}
	@Override
	public boolean issueBook(int bId, String email) {
		System.out.println(bId);
		System.out.println("issue book called");
		BookBean bean = new BookBean();
		try(FileInputStream	fin = new FileInputStream("db.properties")){

			Properties pro = new Properties();
			pro.load(fin);

			Class.forName(pro.getProperty("path")).newInstance();
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro.getProperty("user"),pro.getProperty("password"))){
				String query = pro.getProperty("issue_book");
				try(PreparedStatement pstmt = conn.prepareStatement(query)){
					pstmt.setString(1, email);
					pstmt.setInt(2, bId);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
					Calendar cal = Calendar.getInstance();
					pstmt.setDate(3, java.sql.Date.valueOf(sdf.format(cal.getTime())));
					cal.add(Calendar.DAY_OF_MONTH, 7);
					String newDate = sdf.format(cal.getTime());
					pstmt.setDate(4, java.sql.Date.valueOf(newDate));
					
					int count = pstmt.executeUpdate();
					if(count!=0) {
						return true;
					}else {
						return false;
					}
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
		 
	}
	@Override
	public BookBean searchBookTitle(String name) {
		BookBean bean = new BookBean();
		try(FileInputStream	fin = new FileInputStream("db.properties")){

			Properties pro = new Properties();
			pro.load(fin);

			Class.forName(pro.getProperty("path")).newInstance();
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro.getProperty("user"),pro.getProperty("password"))){
				String query = pro.getProperty("search_book_name");
				try(PreparedStatement pstmt = conn.prepareStatement(query)){
					pstmt.setString(1, name);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {	
						bean.setId(rs.getInt("id"));
						bean.setName(rs.getString("name"));
						bean.setAuthor(rs.getString("author"));
						bean.setCategory(rs.getString("category"));
						bean.setPublishername(rs.getString("publishername"));
						return bean;
					} else {
						System.out.println("book not found");
					}
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	

					}
	
	
	
	@Override
	public BookBean searchBookAuthor(String Author) {
		BookBean bean = new BookBean();
		try(FileInputStream	fin = new FileInputStream("db.properties")){

			Properties pro = new Properties();
			pro.load(fin);

			Class.forName(pro.getProperty("path")).newInstance();
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro.getProperty("user"),pro.getProperty("password"))){
				String query = pro.getProperty("search_book_author");
				try(PreparedStatement pstmt = conn.prepareStatement(query)){
					pstmt.setString(1, Author);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {	
						bean.setId(rs.getInt("id"));
						bean.setName(rs.getString("name"));
						bean.setAuthor(rs.getString("author"));
					bean.setCategory(rs.getString("category"));
						bean.setPublishername(rs.getString("publishername"));
						return bean;
					} else {
						System.out.println("book not found");
					}
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		

		
	}
	@Override
	public BookBean searchBookType(int bookType) {
		BookBean bean = new BookBean();
		try(FileInputStream	fin = new FileInputStream("db.properties")){

			Properties pro = new Properties();
			pro.load(fin);

			Class.forName(pro.getProperty("path")).newInstance();
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro.getProperty("user"),pro.getProperty("password"))){
				String query = pro.getProperty("search_book_id");
				try(PreparedStatement pstmt = conn.prepareStatement(query)){
					pstmt.setInt(1, bookType);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {	
						bean.setId(rs.getInt("id"));
						bean.setName(rs.getString("name"));
						bean.setAuthor(rs.getString("author"));
						bean.setCategory(rs.getString("category"));
						bean.setPublishername(rs.getString("publishername"));
						return bean;
					} else {
						System.out.println("book not found");
					}
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		

	}
	

}
