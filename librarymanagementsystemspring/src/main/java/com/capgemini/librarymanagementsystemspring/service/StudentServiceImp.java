package com.capgemini.librarymanagementsystemspring.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystemspring.dao.StudentDAO;
import com.capgemini.librarymanagementsystemspring.dao.StudentDAOImp;
import com.capgemini.librarymanagementsystemspring.dto.BookBean;
//import com.capgemini.librarymanagementsystemspring.factory.StudentFactory;
@Service
public class StudentServiceImp implements StudentService{
	@Autowired
	 StudentDAO dao;// = new StudentDAOImp();
	@Override
	public BookBean searchBookTitle(String name) {
		return dao.searchBookTitle(name);
	}

	@Override
	public BookBean searchBookAuthor(String Author) {
		return dao.searchBookAuthor(Author);
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
	public BookBean searchBookType(int bookType) {
		return dao.searchBookType(bookType);
	}

	@Override
	public boolean req(int id, int book_id) {
		return dao.req(id, book_id);
	}

	@Override
	public boolean reqReturnBook(int id, int book_id) {
		return dao.reqReturnBook(id, book_id);
	}

}
