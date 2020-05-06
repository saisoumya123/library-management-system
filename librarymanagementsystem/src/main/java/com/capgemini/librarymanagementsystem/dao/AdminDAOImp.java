package com.capgemini.librarymanagementsystem.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem.database.DataBase;
import com.capgemini.librarymanagementsystem.dto.AdminBean;
import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.StudentBean;
import com.capgemini.librarymanagementsystem.exception.AdminException;
import com.capgemini.librarymanagementsystem.exception.BookException;
import com.capgemini.librarymanagementsystem.exception.StudentException;

public class AdminDAOImp implements AdminDAO {

	public boolean register(AdminBean info) {
		for (AdminBean adminBean : DataBase.admin) {
			if (adminBean.getEmail().equals(info.getEmail())) {
				return false;
			}
		}
		DataBase.admin.add(info);
		return true;

	}

	public AdminBean auth(String email, String password) {
		for (AdminBean bean : DataBase.admin) {
			if (bean.getEmail().equals(email) && bean.getPassword().equals(password)) {
				System.out.println("Login Successful");
				return bean;
			}
		}
		throw new AdminException("Invalid email and password");

	}

	public LinkedList<BookBean> searchBookTitle(String bname) {
		LinkedList<BookBean> searchList = new LinkedList<BookBean>();
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			String retrievedBname = retrievedBook.getName();
			if (bname.equals(retrievedBname)) {
				searchList.add(retrievedBook);
				return searchList;
			}
		}
		if (searchList.size() == 0) {
			throw new AdminException("Book not found");
		} else {
			return searchList;
		}

	}

	public LinkedList<BookBean> searchBookAuthor(String bAuthor) {

		LinkedList<BookBean> searchList = new LinkedList<BookBean>();
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			String retrievedBAuthor = retrievedBook.getAuthor();
			if (bAuthor.equals(retrievedBAuthor)) {
				searchList.add(retrievedBook);
			}
		}
		if (searchList.size() == 0) {
			throw new AdminException("Book not found");
		} else {
			return searchList;
		}

	}

	public LinkedList<BookBean> searchBookType(int bookType) {
		LinkedList<BookBean> searchList = new LinkedList<BookBean>();
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			int retrievedBookType = retrievedBook.getId();
			if (bookType == retrievedBookType) {
				searchList.add(retrievedBook);
			}
		}
		if (searchList.size() == 0) {
			throw new AdminException("Book not found");
		} else {
			return searchList;
		}
	}

	public boolean delete(int bId) {
		boolean status = false;
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			int retrievedId = retrievedBook.getId();
			if (bId == retrievedId) {
				status = true;
				DataBase.book.remove(i);
				break;
			}
		}
		return status;
	}

	public boolean addBook(BookBean book) {
		for (BookBean bean : DataBase.book) {
			if (bean.getId() == book.getId()) {
				return false;
			}
		}
		DataBase.book.add(book);
		return true;
	}

	public LinkedList<Integer> getBookIds() {
		LinkedList<Integer> idList = new LinkedList<Integer>();
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			int retrievedBookId = retrievedBook.getId();
			idList.add(retrievedBookId);
		}
		return idList;
	}

	public LinkedList<BookBean> getBooksInfo() {

		return DataBase.book;
	}

	public List<StudentBean> showUsers() {
		List<StudentBean> usersList = new LinkedList<StudentBean>();
		for (StudentBean studentBean : DataBase.student) {

			studentBean.getId();
			studentBean.getName();
			studentBean.getEmail();
			studentBean.getBooksBorrowed();
			usersList.add(studentBean);

		}
		return usersList;
	}

	public List<RequestBean> showRequests() {
		List<RequestBean> infos = new LinkedList<RequestBean>();
		for (RequestBean requestInfo : DataBase.request) {
			requestInfo.getBookInfo();
			requestInfo.getStudentInfo();
			requestInfo.isIssued();
			requestInfo.isReturned();
			infos.add(requestInfo);
		}
		return infos;
	}

	public boolean bookIssue(StudentBean student, BookBean book) {
		boolean isValid = false;

		RequestBean requestInfo = new RequestBean();

		int noOfBooksBorrowed = student.getBooksBorrowed();
		for (RequestBean info : DataBase.request) {
			if (info.getStudentInfo().getId() == student.getId()) {
				if (info.getBookInfo().getId() == book.getId()) {
					requestInfo = info;

					isValid = true;

				}
			}
		}

		if (isValid)

		{

			for (BookBean info2 : DataBase.book) {
				if (info2.getId() == book.getId()) {
					book = info2;
				}

			}

			for (StudentBean studentInfo : DataBase.student) {
				if (studentInfo.getId() == student.getId()) {
					student = studentInfo;
					noOfBooksBorrowed = student.getBooksBorrowed();

				}

			}

			if (noOfBooksBorrowed < 3) {

				// boolean isRemoved = DataBase.book.remove(book);
				int booksAvailable = book.getCopies();
				if (booksAvailable > 0) {
					booksAvailable--;
					book.setCopies(booksAvailable);
					noOfBooksBorrowed++;
					System.out.println(noOfBooksBorrowed);
					student.setBooksBorrowed(noOfBooksBorrowed);
					// DataBase.REQUESTDB.remove(requestInfo);
					requestInfo.setIssued(true);
					return true;
				} else {
					throw new AdminException("Book can't be borrowed");
				}

			} else {
				throw new AdminException("Student Exceeds maximum limit");
			}

		} else {
			throw new AdminException("Book data or Student data is incorrect");

		}
	}

	public boolean isBookReceived(StudentBean student, BookBean book) {
		boolean isValid = false;
		RequestBean requestInfo1 = new RequestBean();
		for (RequestBean requestInfo : DataBase.request) {

			if (requestInfo.getBookInfo().getId() == book.getId()
					&& requestInfo.getStudentInfo().getId() == student.getId() && requestInfo.isReturned() == true) {
				isValid = true;
				requestInfo1 = requestInfo;
			}
		}
		if (isValid) {

			book.setAuthor(requestInfo1.getBookInfo().getAuthor());
			book.setName(requestInfo1.getBookInfo().getName());
			// DataBase.book.add(book);
			int noOfBooksAvailble = book.getCopies();
			noOfBooksAvailble++;
			book.setCopies(noOfBooksAvailble);
			DataBase.request.remove(requestInfo1);

			for (StudentBean userInfo2 : DataBase.student) {
				if (userInfo2.getId() == student.getId()) {
					student = userInfo2;
				}

			}
			int noOfBooksBorrowed = student.getBooksBorrowed();
			noOfBooksBorrowed--;
			student.setBooksBorrowed(noOfBooksBorrowed);
			return true;

		}

		return false;
	}

	@Override
	public boolean update(BookBean bean) {

		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			if (retrievedBook.getId() == bean.getId()) {
				retrievedBook.setName(bean.getName());
				return true;

			}

			else {
				throw new BookException("Invalid Book");
			}
		}
		throw new AdminException("Book not updated");
	}

}
