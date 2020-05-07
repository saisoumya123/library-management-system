package com.capgemini.librarymanagementsystemspring.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemspring.dto.BookBean;
import com.capgemini.librarymanagementsystemspring.dto.BorrowedBookBean;
import com.capgemini.librarymanagementsystemspring.dto.RequestBookBean;

public interface StudentDAO {
	public BookBean searchBookTitle(String bookTitle); 
	public BookBean searchBookAuthor(String Author);
	public List<Integer> getBookIds();
	public List<BookBean> getBooksInfo();
	boolean reqReturnBook(int book_Id, int id);
	BookBean searchBookType(int bookId);
	boolean req(int id, int book_id);
}
