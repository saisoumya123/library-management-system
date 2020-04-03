package com.capgemini.librarymanagementsystem.service;


	import java.util.LinkedList;
import java.util.List;

	import com.capgemini.librarymanagementsystem.dto.AdminBean;
	import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.StudentBean;

	public interface AdminService {
		
		boolean register(AdminBean info);
		AdminBean auth(String email, String password);
		LinkedList<BookBean> searchBookTitle(String bname);
		LinkedList<BookBean> searchBookAuthor(String bAuthor);
		LinkedList<BookBean> searchBookType(int bookType);
		int update(int bId);
		boolean delete(int bId);
		boolean addBook(BookBean info);
		LinkedList<Integer> getBookIds();
		public LinkedList<BookBean> getBooksInfo() ;
		public boolean issueBook(int bId);
		public List<StudentBean> showUsers(); 
		public List<RequestBean> showRequests();
		public boolean bookIssue(StudentBean student, BookBean book);
		public boolean isBookReceived(StudentBean student, BookBean book);
}
