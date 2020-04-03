package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAO;
import com.capgemini.librarymanagementsystemjdbc.factory.AdminFactory;
import com.capgemini.librarymanagementsystemjdbc.dto.AdminBean;
import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;

public class AdminServiceImp implements AdminService{
	private AdminDAO dao = AdminFactory.getAdminDAO();
	

	
	@Override
	public int delete(int bId) {
		return dao.delete(bId);
	}

	@Override
	public int addBook(BookBean info) {
		return dao.addBook(info);
	}

	@Override
	public LinkedList<Integer> getBookIds() {
		return dao.getBookIds();
	}

	@Override
	public List<BookBean> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public boolean issueBook(int bId,String email) {
		return dao.issueBook(bId, email);
	}

	@Override
	public BookBean searchBookTitle(String bname) {
		return dao.searchBookTitle(bname);
	}

	@Override
	public BookBean searchBookAuthor(String bAuthor) {
		return dao.searchBookAuthor(bAuthor);
	}

	@Override
	public BookBean searchBookType(int bookType) {
		return dao.searchBookType(bookType);
	}

	@Override
	public int update(BookBean book) {
		return dao.update(book);
	}

}
