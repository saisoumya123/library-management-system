package com.capgemini.librarymanagementsystem.dao;


	import java.util.LinkedList;
import java.util.List;

	import com.capgemini.librarymanagementsystem.database.DataBase;
	import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.StudentBean;
import com.capgemini.librarymanagementsystem.exception.AdminException;
import com.capgemini.librarymanagementsystem.exception.StudentException;

	public class StudentDAOImp implements StudentDAO {

		public boolean register(StudentBean info) {
			for(StudentBean studentBean : DataBase.student) {
				if(studentBean.getEmail().equals(info.getEmail())) {
					return false;
				}
			}
			DataBase.student.add(info);
			return true;
		}

		public StudentBean auth(String email, String password) {
			for(StudentBean bean : DataBase.student) {
				if(bean.getEmail().equals(email)&& bean.getPassword().equals(password)) {
					System.out.println("Login Successful");
					return bean;
				} 
			}
			throw new StudentException("Invalid email and password");
			
		}

		public LinkedList<BookBean> searchBookTitle(String bname) {
			LinkedList<BookBean> searchList=new LinkedList<BookBean>();
			for(int i=0;i<=DataBase.book.size()-1;i++)
			{
				BookBean retrievedBook=DataBase.book.get(i);
				String retrievedBname=retrievedBook.getName();
				if(bname.equals(retrievedBname))
				{
					searchList.add(retrievedBook);	
					return searchList;
					
					
				}
			}
			if(searchList.size()==0)
			{
				throw new StudentException ("Book is not found");
			}
			else
			{
				return searchList;
			}
		}


		public LinkedList<BookBean> searchBookAuthor(String bAuthor) {
			LinkedList<BookBean> searchList=new LinkedList<BookBean>();
			for(int i=0;i<=DataBase.book.size()-1;i++)
			{
				BookBean retrievedBook=DataBase.book.get(i);
				String retrievedBAuthor=retrievedBook.getAuthor();
				if(bAuthor.equals(retrievedBAuthor))
				{
					searchList.add(retrievedBook);	
				}
			}
			if(searchList.size()==0)
			{
				throw new StudentException ("Book is not found");
			}
			else
			{
				return searchList;
			}	
		}


		public LinkedList<BookBean> searchBookType(String bookType) {
			LinkedList<BookBean> searchList=new LinkedList<BookBean>();
			for(int i=0;i<=DataBase.book.size()-1;i++)
			{
				BookBean retrievedBook=DataBase.book.get(i);
				String retrievedBookType=retrievedBook.getCategory();
				if(bookType.equals(retrievedBookType))
				{
					searchList.add(retrievedBook);	
				}
			}
			if(searchList.size()==0)
			{
				throw new AdminException("Book not found");
			}
			else
			{
				return searchList;
			}	
		}


		public LinkedList<Integer> getBookIds() {
			LinkedList<Integer> idList=new LinkedList<Integer>();
			for(int i=0;i<=DataBase.book.size()-1;i++)
			{
				BookBean retrievedBook=DataBase.book.get(i);
				int retrievedBookId=retrievedBook.getId();
				idList.add(retrievedBookId);
			}
			return idList;

		}


		public LinkedList<BookBean> getBooksInfo() {
			return DataBase.book;
		}

		

		public boolean returnBook(int bId) {
			boolean returned = DataBase.book.contains(getBookIds());
			if(returned) {
				BookBean b = DataBase.book.get(bId);
				DataBase.book.add(b.getId(),b);
				return true;
			}
			return false;
		}

		public boolean req(int bId, String author) {
			boolean req = DataBase.book.contains(getBookIds());
			if(req) {
				return true;
			}else {
			return false;
		}
	
		}

		@Override
		public LinkedList<BookBean> searchBookType(int bookType) {
			LinkedList<BookBean> searchList=new LinkedList<BookBean>();
			for(int i=0;i<=DataBase.book.size()-1;i++)
			{
				BookBean retrievedBook=DataBase.book.get(i);
				int retrievedBookType=retrievedBook.getId();
				if(bookType==retrievedBookType)
				{
					searchList.add(retrievedBook);	
				}
			}
			if(searchList.size()==0)
			{
				throw new AdminException("Book not found");
			}
			else
			{
				return searchList;
			}	
		
		}

		@Override
		public RequestBean bookRequest(StudentBean student, BookBean book) {
			boolean flag = false, 
					isRequestExists = false;
					RequestBean requestInfo = new RequestBean();
					StudentBean userInfo2 = new StudentBean();
					BookBean bookInfo2 = new BookBean();

					for (RequestBean requestInfo2 : DataBase.request) {
						if (book.getId() == requestInfo2.getBookInfo().getId()) {
							isRequestExists = true;

						}

					}

					if (!isRequestExists) {
						for (StudentBean user : DataBase.student) {
							if (user.getId() == student.getId()) {
								for (BookBean book1 : DataBase.book) {
									if (book1.getId() == book1.getId()) {
										userInfo2 = user;
										bookInfo2 = book1;
										flag = true;
									}
								}
							}
						}
						if (flag == true) {
							requestInfo.setBookInfo(bookInfo2);
							requestInfo.setStudentInfo(userInfo2);
							DataBase.request.add(requestInfo);
							return requestInfo;
						}

					}

					throw new AdminException("Invalid request or you cannot request that book");
		
		}

		@Override
		public RequestBean bookReturn(StudentBean student, BookBean book) {
			for (RequestBean requestInfo : DataBase.request) {

				if (requestInfo.getBookInfo().getId() == book.getId() &&
						requestInfo.getStudentInfo().getId() == student.getId() &&
						requestInfo.isIssued() == true) {


					System.out.println("Returning Issued book only");
					requestInfo.setReturned(true);

					return requestInfo;
					
				}
		
				
		}
			return null;
		}
	}
		
