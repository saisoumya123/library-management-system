package com.capgemini.librarymanagementsystem.service;


	import java.util.LinkedList;
import java.util.List;

	import com.capgemini.librarymanagementsystem.dao.StudentDAO;
	import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.StudentBean;
	import com.capgemini.librarymanagementsystem.factory.StudentFactory;

	public class StudentServiceImp implements StudentService {
		private StudentDAO dao = StudentFactory.getStudentDAO();
		public boolean register(StudentBean info) {
			return dao.register(info) ;
		}

		public StudentBean auth(String email, String password) {
			return dao.auth(email, password);
		}

		public LinkedList<BookBean> searchBookTitle(String bname) {
			return dao.searchBookTitle(bname);    
		}

		public LinkedList<BookBean> searchBookAuthor(String bAuthor) {
			return dao.searchBookAuthor(bAuthor);   
		}

		
		public LinkedList<Integer> getBookIds() {
			return dao.getBookIds();  
		}

		public LinkedList<BookBean> getBooksInfo() {
			return dao.getBooksInfo();   
		}

		
		@Override
		public LinkedList<BookBean> searchBookType(int bookType) {
			return dao.searchBookType(bookType);
		}

		@Override
		public RequestBean bookRequest(StudentBean student, BookBean book) {
			return dao.bookRequest(student, book);
		}

		@Override
		public RequestBean bookReturn(StudentBean student, BookBean book) {
			return dao.bookReturn(student, book);
		}

		
		

		

		

}
