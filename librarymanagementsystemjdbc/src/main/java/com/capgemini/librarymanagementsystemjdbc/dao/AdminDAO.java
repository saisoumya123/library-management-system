package com.capgemini.librarymanagementsystemjdbc.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;


public interface AdminDAO {
	
	
	boolean update(BookBean book);
	boolean delete(int bId);
	boolean addBook(BookBean info);
	LinkedList<BookBean> getBookIds();
	List<BookBean> getBooksInfo();
	BookBean searchBookTitle(String booktitle);
	BookBean searchBookAuthor(String author);
	BookBean searchBookType(int bookid);
	boolean issueBook(int book_Id, int id);	
	boolean returnBook(int book_id,int id);
}
