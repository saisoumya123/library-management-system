package com.capgemini.librarymanagementsystemjdbc.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.StudentBean;
import com.capgemini.librarymanagementsystemjdbc.dto.AdminBean;
import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;


public interface AdminDAO {
	
	
	int update(BookBean book);
	int delete(int bId);
	int addBook(BookBean info);
	LinkedList<Integer> getBookIds();
	List<BookBean> getBooksInfo();
	BookBean searchBookTitle(String bname);
	BookBean searchBookAuthor(String bAuthor);
	BookBean searchBookType(int bookType);
	boolean issueBook(int bId, String email);
	
	
}
