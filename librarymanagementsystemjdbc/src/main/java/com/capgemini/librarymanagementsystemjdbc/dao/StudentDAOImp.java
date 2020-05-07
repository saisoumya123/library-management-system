package com.capgemini.librarymanagementsystemjdbc.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

//import com.capgemini.librarymanagementsystemjdbc.controller.Admin;
import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestBookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemjdbc.utility.JdbcUtility;

public class StudentDAOImp implements StudentDAO {
	Scanner scan = new Scanner(System.in);
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	@Override
	public BookBean searchBookTitle(String bookTitle) {
		conn = JdbcUtility.getConnection();
		BookBean bean = new BookBean();
		try {
			// String query = pro.getProperty("search_book_name");
			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.search_book_name);
			pstmt.setString(1, bookTitle);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				bean.setBid(rs.getInt("bid"));
				bean.setBook_title(rs.getString("book_title"));
				bean.setCategory(rs.getString("category"));
				bean.setAuthor(rs.getString("author"));
				bean.setCopies(rs.getInt("copies"));
				bean.setBook_publisher(rs.getString("book_publisher"));
				bean.setPublisher_name(rs.getString("publisher_name"));
				bean.setISBN(rs.getInt("isbn"));
				bean.setCopyright_year(rs.getInt("copyright_year"));
				bean.setStatus(rs.getString("status"));
				return bean;
			} else {
				System.out.println("book not found");
			}

		} catch (Exception e) {
			throw new LMSException("Book is not present with this name");
		}
		return null;
	}

	@Override
	public BookBean searchBookAuthor(String Author) {
		conn = JdbcUtility.getConnection();
		BookBean bean = new BookBean();
		try {
			// String query = pro.getProperty("search_book_author");
			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.search_book_author);
			pstmt.setString(1, Author);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				bean.setBid(rs.getInt("bid"));
				bean.setBook_title(rs.getString("book_title"));
				bean.setCategory(rs.getString("category"));
				bean.setAuthor(rs.getString("author"));
				bean.setCopies(rs.getInt("copies"));
				bean.setBook_publisher(rs.getString("book_publisher"));
				bean.setPublisher_name(rs.getString("publisher_name"));
				bean.setISBN(rs.getInt("isbn"));
				bean.setCopyright_year(rs.getInt("copyright_year"));
				bean.setStatus(rs.getString("status"));
				return bean;
			} else {
				System.out.println("book not found");
			}

		} catch (Exception e) {
			throw new LMSException("Book not found with this Author");
		}
		return null;
	}

	@Override
	public BookBean searchBookType(int bookId) {
		conn = JdbcUtility.getConnection();
		BookBean bean = new BookBean();
		try {
			// String query = pro.getProperty("search_book_id");
			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.search_book_id);
			pstmt.setInt(1, bookId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				bean.setBid(rs.getInt("bid"));
				bean.setBook_title(rs.getString("book_title"));
				bean.setCategory(rs.getString("category"));
				bean.setAuthor(rs.getString("author"));
				bean.setCopies(rs.getInt("copies"));
				bean.setBook_publisher(rs.getString("book_publisher"));
				bean.setPublisher_name(rs.getString("publisher_name"));
				bean.setISBN(rs.getInt("isbn"));
				bean.setCopyright_year(rs.getInt("copyright_year"));
				bean.setStatus(rs.getString("status"));
				return bean;
			} else {
				System.out.println("book not found");
			}

		} catch (Exception e) {
			throw new LMSException("Book not found with this id");
		}
		return null;
	}

	@Override
	public List<BookBean> getBookIds() {
		conn = JdbcUtility.getConnection();
		try {

			List<BookBean> list = new LinkedList<BookBean>();

			// String query = pro.getProperty("get_bookId");
			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.get_bookId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BookBean bean = new BookBean();
				bean.setBid(rs.getInt("bid"));
				list.add(bean);
			}
			return list;

		} catch (Exception e) {
			throw new LMSException("No book id found");
		}
	}

	@Override
	public List<BookBean> getBooksInfo() {
		conn = JdbcUtility.getConnection();
		try {
			List<BookBean> li = new LinkedList<BookBean>();

			// String query = pro.getProperty("get_allBook");
			PreparedStatement stmt = conn.prepareStatement(QueryMapper.get_allBook);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				BookBean bean = new BookBean();
				bean.setBid(rs.getInt("bid"));
				bean.setBook_title(rs.getString("book_title"));
				bean.setCategory(rs.getString("category"));
				bean.setAuthor(rs.getString("author"));
				bean.setCopies(rs.getInt("copies"));
				bean.setBook_publisher(rs.getString("book_publisher"));
				bean.setPublisher_name(rs.getString("publisher_name"));
				bean.setISBN(rs.getInt("isbn"));
				bean.setCopyright_year(rs.getInt("copyright_year"));
				bean.setStatus(rs.getString("status"));
				li.add(bean);
			}
			return li;

		} catch (Exception e) {
			throw new LMSException("No books in library");
		}
	}

	@Override
	public boolean req(int id, int book_id) {
		conn = JdbcUtility.getConnection();
		try {

			// String query = pro.getProperty("reqBookDetails");
			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.reqBookDetails);
			pstmt.setInt(1, book_id);
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					// String query1 = pro.getProperty("reqInsert");
					PreparedStatement pstmt1 = conn.prepareStatement(QueryMapper.reqInsert);
					pstmt1.setInt(1, book_id);
					pstmt1.setInt(2, id);
					pstmt1.setInt(3, id);
					pstmt1.setInt(4, book_id);
					pstmt1.setInt(5, id);
					pstmt1.setString(6, "request");
					int rs1 = pstmt1.executeUpdate();
					return true;
				}
			}

		} catch (Exception e) {
			throw new LMSException("Cannot insert book");
		}
		return false;
	}

	@Override
	public boolean reqReturnBook(int book_Id, int id) {
		conn = JdbcUtility.getConnection();
		try {

			// String query = pro.getProperty("retBookDetails");
			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.retBookDetails);
			pstmt.setInt(1, book_Id);
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					// String query1 = pro.getProperty("retRequest");
					PreparedStatement pstmt1 = conn.prepareStatement(QueryMapper.retRequest);
					pstmt1.setInt(1, book_Id);
					pstmt1.setInt(2, id);
					pstmt1.setInt(3, id);
					pstmt1.setInt(4, book_Id);
					pstmt1.setInt(5, id);
					pstmt1.setString(6, "return");
					int rs1 = pstmt1.executeUpdate();
					return true;
				}
			}

		} catch (Exception e) {
			throw new LMSException("Cannot place the request for the book");
		}
		return false;
	}
}
