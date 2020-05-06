package com.capgemini.librarymanagementsystem.service;


	import java.util.LinkedList;
import java.util.List;

	import com.capgemini.librarymanagementsystem.dao.AdminDAO;
	import com.capgemini.librarymanagementsystem.dto.AdminBean;
	import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.StudentBean;
import com.capgemini.librarymanagementsystem.factory.AdminFactory;

	public class AdminServiceImp implements AdminService{
		private AdminDAO dao = AdminFactory.getAdminDAO();
		public boolean register(AdminBean info) {
			return dao.register(info) ;
		}

		public AdminBean auth(String email, String password) {
			return dao.auth(email, password);
		}

		public LinkedList<BookBean> searchBookTitle(String bTitle) {

			return dao.searchBookTitle(bTitle);
		}

		public LinkedList<BookBean> searchBookAuthor(String bAuthor) {

			return dao.searchBookAuthor(bAuthor);
		}

		public LinkedList<BookBean> searchBookType(int bookType) {
			return dao.searchBookType(bookType);
		}

		public boolean update(BookBean bean) {
			return dao.update(bean);
		}

		public boolean delete(int bId) {
			return dao.delete(bId);
		}

		public boolean addBook(BookBean info) {
			return dao.addBook(info);	
		}

		public LinkedList<Integer> getBookIds() {
			return dao.getBookIds();
		}

		public LinkedList<BookBean> getBooksInfo() {
			return dao.getBooksInfo();
		}

		

		@Override
		public List<StudentBean> showUsers() {
			return dao.showUsers();
		}

		@Override
		public List<RequestBean> showRequests() {
			return dao.showRequests();
		}

		@Override
		public boolean bookIssue(StudentBean student, BookBean book) {
			return dao.bookIssue(student, book);
		}

		@Override
		public boolean isBookReceived(StudentBean student, BookBean book) {
			return dao.isBookReceived(student, book);
		}

		
		
		

}
