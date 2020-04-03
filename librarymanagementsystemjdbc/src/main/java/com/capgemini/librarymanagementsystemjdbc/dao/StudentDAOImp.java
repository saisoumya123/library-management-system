package com.capgemini.librarymanagementsystemjdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;

public class StudentDAOImp implements StudentDAO{
	Scanner scan = new Scanner(System.in);
	@Override
	public BookBean searchBookTitle(String name) {
		BookBean bean = new BookBean();
		try(FileInputStream	fin = new FileInputStream("db.properties")){

				Properties pro = new Properties();
				pro.load(fin);

				Class.forName(pro.getProperty("path")).newInstance();
				//Class.forName("com.mysql.jdbc.Driver").newInstance();
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
	public boolean req(int id,String email) {
		AdminDAOImp imp = new AdminDAOImp();
		UserDAOImp dao1 = new UserDAOImp(); 
		try(FileInputStream	fin = new FileInputStream("db.properties")){

			Properties pro = new Properties();
			pro.load(fin);
			//List<BookBean> li = new LinkedList<BookBean>();
			//UserBean u = new UserBean();
			BookBean b = new BookBean();
			Class.forName(pro.getProperty("path")).newInstance();
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro.getProperty("user"),pro.getProperty("password"))){
			String query = pro.getProperty("req_book");
				try(PreparedStatement pstmt = conn.prepareStatement(query)){	
				 pstmt.setInt(1, id);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						
					}
					return true;
					/*
					 * while(rs.next()) { System.out.println("enter the book id to be requested");
					 * int i1 = scan.nextInt(); imp.issueBook(i1); return true; }
					 */
					
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean returnBook(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
