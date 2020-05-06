package com.capgemini.librarymanagementsystemjdbc.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.StubNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemjdbc.utility.JdbcUtility;
import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;

public class AdminDAOImp implements AdminDAO {
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	Properties properties = new Properties();
	BookBean bean = new BookBean();

	@Override
	public boolean update(BookBean book) throws LMSException {
		conn = JdbcUtility.getConnection();
		int count = 0;
		try {

			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.update);
			pstmt.setString(1, book.getBook_title());
			pstmt.setInt(2, book.getBid());
			count = pstmt.executeUpdate();
			System.out.println(count);
			return true;

		} catch (Exception e) {
			throw new LMSException("Invalid entries");
		}
	}

	@Override
	public boolean delete(int bid) {
		conn = JdbcUtility.getConnection();
		int count;
		try {
			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.delete);
			pstmt.setInt(1, bid);
			count = pstmt.executeUpdate();
			return true;

		} catch (Exception e) {
			throw new LMSException("Cannot delete the book");
		}
	}

	@Override
	public boolean addBook(BookBean info) {
		conn = JdbcUtility.getConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.add_book);
			pstmt.setInt(1, info.getBid());
			pstmt.setString(2, info.getBook_title());
			pstmt.setString(3, info.getCategory());
			pstmt.setString(4, info.getAuthor());
			pstmt.setInt(5, info.getCopies());
			pstmt.setString(6, info.getBook_publisher());
			pstmt.setString(7, info.getPublisher_name());
			pstmt.setInt(8, info.getISBN());
			pstmt.setInt(9, info.getCopyright_year());
			pstmt.setString(10, info.getStatus());
			int count = pstmt.executeUpdate();
			return true;

		} catch (Exception e) {
			throw new LMSException("Cannot add the book or book already exist");
		}
	}

	@Override
	public LinkedList<BookBean> getBookIds() {
		conn = JdbcUtility.getConnection();
		try {
			LinkedList<BookBean> list = new LinkedList<BookBean>();
			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.get_bookId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BookBean bean = new BookBean();
				bean.setBid(rs.getInt("bid"));
				list.add(bean);
			}
			return list;

		} catch (Exception e) {
			throw new LMSException("Cannot get book ids");
		}
	}

	@Override
	public List<BookBean> getBooksInfo() {
		conn = JdbcUtility.getConnection();
		try {
			List<BookBean> li = new LinkedList<BookBean>();

			try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.get_allBook)) {
				ResultSet rs = pstmt.executeQuery();
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
			}
		} catch (Exception e) {
			throw new LMSException("No books found");
		}
	}

	@Override
	public BookBean searchBookTitle(String booktitle) {
		conn = JdbcUtility.getConnection();
		BookBean bean = new BookBean();
		try {
			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.search_book_name);
			pstmt.setString(1, booktitle);
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
			throw new LMSException("No book found with this name");
		}
		return null;
	}

	@Override
	public BookBean searchBookAuthor(String author) {
		conn = JdbcUtility.getConnection();
		BookBean bean = new BookBean();
		try {
			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.search_book_author);
			pstmt.setString(1, author);
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
	public BookBean searchBookType(int bookid) {
		conn = JdbcUtility.getConnection();
		BookBean bean = new BookBean();
		try {
			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.search_book_id);
			pstmt.setInt(1, bookid);
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
	public boolean issueBook(int book_Id, int id) {
		conn = JdbcUtility.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.book_details);
			pstmt.setInt(1, book_Id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// String query1 = pro.getProperty("Request_book");
				PreparedStatement pstmt1 = conn.prepareStatement(QueryMapper.Request_book);
				pstmt1.setInt(1, book_Id);
				pstmt1.setInt(2, id);
				ResultSet rs1 = pstmt1.executeQuery();
				if (rs1.next()) {
					// String query2 = pro.getProperty("borrow_book");
					PreparedStatement pstmt2 = conn.prepareStatement(QueryMapper.borrow_book);
					pstmt2.setInt(1, id);
					ResultSet rs2 = pstmt2.executeQuery();
					if (rs2.next()) {
						int noOfBooksBorrowed = rs2.getInt("id");
						if (noOfBooksBorrowed < 3) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							LocalDate date = LocalDate.now();
							Calendar c = Calendar.getInstance();
							c.setTime(new java.util.Date());
							c.add(Calendar.DATE, 15);
							String date1 = sdf.format(c.getTime());
							// String query3 = pro.getProperty("book_issue");
							PreparedStatement pstmt3 = conn.prepareStatement(QueryMapper.book_issue);
							pstmt3.setInt(1, id);
							pstmt3.setInt(2, book_Id);
							pstmt3.setObject(3, date);
							pstmt3.setObject(4, date1);
							int count = pstmt3.executeUpdate();
							if (count != 0) {
								// String query4 = pro.getProperty("borrowBookValues");
								PreparedStatement pstmt4 = conn.prepareStatement(QueryMapper.borrowBookValues);
								pstmt4.setInt(1, book_Id);
								pstmt4.setInt(2, id);
								pstmt4.setInt(3, id);
								pstmt4.executeUpdate();
							}
							// String query5 = pro.getProperty("reqDelete");
							PreparedStatement pstmt5 = conn.prepareStatement(QueryMapper.reqDelete);
							pstmt5.setInt(1, id);
							pstmt5.setInt(2, book_Id);
							pstmt5.executeUpdate();
						}
						// String query6 = pro.getProperty("updateBookDetails");
						PreparedStatement pstmt6 = conn.prepareStatement(QueryMapper.updateBookDetails);
						pstmt6.setInt(1, book_Id);
						pstmt6.executeUpdate();
					}
					return true;
				}

			} else {
				System.out.println("you have crossed limit");
			}

		} catch (Exception e) {
			throw new LMSException("cannot issue book");
		}
		return false;
	}

	@Override
	public boolean returnBook(int book_id, int id) {
		conn = JdbcUtility.getConnection();
		try {
			// String query = pro.getProperty("issueBook");
			PreparedStatement pstmt = conn.prepareStatement(QueryMapper.issueBook);
			pstmt.setInt(1, book_id);
			pstmt.setInt(2, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// String query1 = pro.getProperty("reqBook");
				PreparedStatement pstmt1 = conn.prepareStatement(QueryMapper.reqBook);
				pstmt1.setInt(1, book_id);
				pstmt1.setInt(2, id);
				ResultSet rs1 = pstmt1.executeQuery();
				if (rs.next()) {
					// String query2 = pro.getProperty("updateBook_Details");
					PreparedStatement pstmt2 = conn.prepareStatement(QueryMapper.updateBook_Details);
					pstmt2.setInt(1, book_id);
					pstmt2.executeUpdate();

					// String query3 = pro.getProperty("delBookDetails");
					PreparedStatement pstmt3 = conn.prepareStatement(QueryMapper.delBookDetails);
					pstmt3.setInt(1, book_id);
					pstmt3.setInt(2, id);
					pstmt3.executeUpdate();

					// String query4 = pro.getProperty("delBorrowBook");
					PreparedStatement pstmt4 = conn.prepareStatement(QueryMapper.delBorrowBook);
					pstmt4.setInt(1, book_id);
					pstmt4.setInt(2, id);
					pstmt4.executeUpdate();

					// String query5 = pro.getProperty("delRequestBook");
					PreparedStatement pstmt5 = conn.prepareStatement(QueryMapper.delRequestBook);
					pstmt5.setInt(1, book_id);
					pstmt5.setInt(2, id);
					pstmt5.executeUpdate();

					return true;
				}
			}

		} catch (Exception e) {
			throw new LMSException("Cannot return book");
		}
		return false;
	}

}
