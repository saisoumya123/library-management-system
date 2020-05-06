package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAO;
import com.capgemini.librarymanagementsystemjdbc.factory.AdminFactory;
import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;

public class AdminServiceImp implements AdminService{
	private AdminDAO dao = AdminFactory.getAdminDAO();
	

	
	@Override
	public boolean delete(int bId) {
		return dao.delete(bId);
	}

	@Override
	public boolean addBook(BookBean info) {
		return dao.addBook(info);
	}

	@Override
	public LinkedList<BookBean> getBookIds() {
		return dao.getBookIds();
	}

	@Override
	public List<BookBean> getBooksInfo() {
		return dao.getBooksInfo();
	}

	
	@Override
	public BookBean searchBookTitle(String booktitle) {
		return dao.searchBookTitle(booktitle);
	}

	@Override
	public BookBean searchBookAuthor(String author) {
		return dao.searchBookAuthor(author);
	}

	@Override
	public BookBean searchBookType(int bookid) {
		return dao.searchBookType(bookid);
	}

	@Override
	public boolean update(BookBean book) {
		return dao.update(book);
	}

	@Override
	public boolean issueBook(int book_Id, int id) {
		return dao.issueBook(book_Id, id);
	}

	@Override
	public boolean returnBook(int book_Id, int id) {
		return dao.returnBook(book_Id, id);
	}

}
