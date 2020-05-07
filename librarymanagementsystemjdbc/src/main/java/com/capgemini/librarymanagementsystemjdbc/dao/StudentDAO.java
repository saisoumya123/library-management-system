package com.capgemini.librarymanagementsystemjdbc.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestBookBean;

public interface StudentDAO {
	public BookBean searchBookTitle(String bookTitle); 
	public BookBean searchBookAuthor(String Author);
	public List<BookBean> getBookIds();
	public List<BookBean> getBooksInfo();
	boolean reqReturnBook(int book_Id, int id);
	BookBean searchBookType(int bookId);
	boolean req(int id, int book_id);
}
