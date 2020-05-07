package com.capgemini.librarymanagementsystemspring.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystemspring.dao.AdminDAO;
import com.capgemini.librarymanagementsystemspring.dto.BookBean;
@Service
public class AdminServiceImp implements AdminService{
	@Autowired
	private AdminDAO dao;
	@Override
	public boolean update(BookBean book) {
		return dao.update(book);
	}

	@Override
	public boolean delete(int bId) {
		return dao.delete(bId);
	}

	@Override
	public boolean addBook(BookBean info) {
		return dao.addBook(info);
	}

	@Override
	public List<Integer> getBookIds() {
		return dao.getBookIds();
	}

	@Override
	public List<BookBean> getBooksInfo() {
		return dao.getBooksInfo();
	}


	@Override
	public BookBean searchBookTitle(String name) {
		return dao.searchBookTitle(name);
	}

	@Override
	public BookBean searchBookAuthor(String Author) {
		return dao.searchBookAuthor(Author);
	}

	@Override
	public BookBean searchBookType(int bookType) {
		return dao.searchBookType(bookType);
	}

	@Override
	public boolean issueBook(int id, int book_id) {
		return dao.issueBook(id, book_id);
	}

	@Override
	public boolean returnBook(int id, int book_id) {
		return dao.returnBook(id, book_id);
	}

}
