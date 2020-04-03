package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;

public interface StudentService {
	public BookBean searchBookTitle(String name); 
	public BookBean searchBookAuthor(String Author);
	
	public LinkedList<Integer> getBookIds();
	public List<BookBean> getBooksInfo();
	boolean returnBook(int id);
	BookBean searchBookType(int bookType);
	boolean req(int id, String email);
}
