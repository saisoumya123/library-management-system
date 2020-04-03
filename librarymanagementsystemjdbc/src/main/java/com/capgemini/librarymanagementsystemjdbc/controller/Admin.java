package com.capgemini.librarymanagementsystemjdbc.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.cfg.CreateKeySecondPass;
import org.hibernate.dialect.Database;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAOImp;
import com.capgemini.librarymanagementsystemjdbc.dao.StudentDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.StudentDAOImp;
import com.capgemini.librarymanagementsystemjdbc.dao.UserDAOImp;
import com.capgemini.librarymanagementsystemjdbc.dto.AdminBean;
import com.capgemini.librarymanagementsystemjdbc.factory.AdminFactory;
import com.capgemini.librarymanagementsystemjdbc.factory.StudentFactory;
import com.capgemini.librarymanagementsystemjdbc.factory.UserFactory;
import com.capgemini.librarymanagementsystemjdbc.service.AdminService;
import com.capgemini.librarymanagementsystemjdbc.service.StudentService;
import com.capgemini.librarymanagementsystemjdbc.service.UserService;

public class Admin {
	public static void main(String[] args) {
		doReg();
	}

	public static void doReg() {

		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Press 1 to register");
			System.out.println("Press 2 to login");
			UserService service1 = UserFactory.getUserService();
			AdminService service = AdminFactory.getAdminService();

			int i = scanner.nextInt();
			switch(i) {
			case 1:
				
					System.out.println("Enter Name");
					String regName = scanner.next();
					System.out.println("Enter Email");
					String regEmail = scanner.next();
					System.out.println("Enter Password");
					String regPassword = scanner.next();
					System.out.println("Enter Mobile");
					long regMobile = scanner.nextLong();
					
					
					System.out.println("enter role");
					String regRole = scanner.next();
					System.out.println("Enter ID");
					int regID = scanner.nextInt();
					
					UserBean bean = new UserBean();
					bean.setId(regID);
					bean.setName(regName);
					bean.setMobile(regMobile);
					bean.setEmail(regEmail);
					bean.setPassword(regPassword);
					bean.setRole(regRole);
					try {
					int check = service1.register(bean);
					if(check == 0) {
						System.out.println("Email already exist");


					} else {
						System.out.println("Registered");

					}}catch(Exception e) {
					System.out.println("invalid");
					}

					break;
				case 2:
					
					System.out.println("Enter email");
					String email = scanner.next();
					System.out.println("Enter Password");
					String password = scanner.next();
					try {
						String auth = service1.auth(email, password);

					if(auth.equalsIgnoreCase("admin")) {
						
							admin();
							
						}else if(auth.equalsIgnoreCase("student")){
							student();
						}
					
					else {
						System.out.println("invalid email and password");
					}
					}catch(Exception e) {
						System.out.println("invalid credentials");
					}

					break;
					}
					//break;
			}while(true);	
	}

				
		
					public static void admin() {
						UserService service1 = UserFactory.getUserService();
						AdminService service = AdminFactory.getAdminService();
						
						Scanner scanner = new Scanner(System.in);
					System.out.println("Press 1 to Add Books");
					System.out.println("Press 2 to update");
					System.out.println("Press 3 to Search the Book by Author");
					System.out.println("Press 4 to Search the Book by Title");
					System.out.println("Press 5 to Search the Book by Id");
					System.out.println("Press 6 to remove the Books");
					System.out.println("Press 7 to Get the Book Id's");
					System.out.println("Press 8 to Get the Book Information");
					System.out.println("Press 9 to issue book");
					System.out.println("Press 10 to main");
					
					int choice1 = scanner.nextInt();
					switch (choice1) {
					case 1:

						System.out.println("Enter ID");
						int bookId = scanner.nextInt();
						System.out.println("Enter Book Name"); 
						String bookName = scanner.next();
						System.out.println("Enter Author"); 
						String bookAuthor = scanner.next();
						System.out.println("Enter Category");
						String bookCategory = scanner.next();
						System.out.println("Enter Publisher Name");
						String bookPubName =  scanner.next();

						BookBean bean2 = new BookBean();

						bean2.setId(bookId); 
						bean2.setName(bookName);
						bean2.setCategory(bookCategory);
						bean2.setAuthor(bookAuthor);
						bean2.setPublishername(bookPubName);

						int check = service.addBook(bean2);
						if(check==0) {
							System.out.println("Book already exists");
						} else {
							System.out.println("Book added");
						}
						break;
					case 2:
						System.out.println("enter id");
						int bId = scanner.nextInt();
						System.out.println("enter name");
						String name = scanner.next();
						BookBean book = new BookBean();
						
						book.setId(bId);
						book.setName(name);
						int updated = service.update(book);
						if(updated == 0) {
							System.out.println("book is not updated");
						}else {
							System.out.println("book  updated");
						}
						break;
					case 3:
						System.out.println("Enter Book author");
						String author = scanner.next();
						BookBean bean4 = new BookBean();
						bean4.setAuthor(author);
						BookBean list1 = service.searchBookAuthor(author);
						if(list1!=null) {
							System.out.println(list1.getId());
							System.out.println(list1.getName());
							System.out.println(list1.getAuthor());
							System.out.println(list1.getCategory());
							System.out.println(list1.getPublishername());
							System.out.println(list1);
						} else {
							System.out.println("Book Not Found");
						}
						break;
					case 4:
						System.out.println("Enter book Name");
						String bookName1 = scanner.next();
						BookBean bean3 = new BookBean();
						bean3.setName(bookName1);
						BookBean list = service.searchBookTitle(bookName1);
						if(list!=null) {
							System.out.println(list.getId());
							System.out.println(list.getName());
							System.out.println(list.getAuthor());
							System.out.println(list.getCategory());
							System.out.println(list.getPublishername());
							System.out.println(list);
						} else {
							System.out.println("Book Not Found");
						}
						break;
						
					case 5:
						System.out.println("Enter Book id");
						int id = scanner.nextInt();
						BookBean bean5 = new BookBean();
						bean5.setId(id);
						BookBean list2 = service.searchBookType(id);
						if(list2!=null) {
							System.out.println(list2.getId());
							System.out.println(list2.getName());
							System.out.println(list2.getAuthor());
							System.out.println(list2.getCategory());
							System.out.println(list2.getPublishername());
							System.out.println(list2);
						} else {
							System.out.println("Book Not Found");
						}
						break;
					case 6:
						System.out.println("Enter the book_Id:");
						int book_Id = scanner.nextInt();
						if (book_Id == 0) {
							System.out.println("Enter the Valid Book_Id");
						} else {
							BookBean bean6 = new BookBean();
							
							bean6.setId(book_Id);
							int remove = service.delete(book_Id);
							if (remove == 0) {
								System.out.println("The Book is not removed");
							} else {
								System.out.println("The Book is removed");
							}
						}
						
						break;
					case 7:
						LinkedList<Integer> ids = service.getBookIds();
						for (Integer integer : ids) {

							if (integer != null) {
								System.out.println(integer);
							} else {
								System.out.println("No Books Ids are available");
							}
						}
						break;
					case 8:
						List<BookBean> info = service.getBooksInfo();
						for (BookBean bookBean : info) {

							if (bookBean != null) {
								System.out.println(bookBean);
							} else {
								System.out.println("Books info is not present");
							}
						}
						break;
					case 9:
						
						break;
					
				

		
	}

	}
					public static void student() {
						Scanner scanner = new Scanner(System.in);
						UserService service1 = UserFactory.getUserService();
						AdminService service = AdminFactory.getAdminService();
						StudentService service2 = StudentFactory.getStudentService();
						StudentDAOImp dao = new StudentDAOImp();
						AdminDAOImp adao = new AdminDAOImp();
						UserDAOImp udao = new UserDAOImp();
						do {
							try {
								System.out.println("Press 1 to Search the Book by Author");
									System.out.println("Press 2 to Search the Book by Title");
									System.out.println("Press 3 to Search the Book by Id");
									System.out.println("Press 4 to Get the Book Id's");
									System.out.println("Press 5 to Get the Book Information");
									System.out.println("Press 6 to return book");
									System.out.println("Press 7 to request book");
									System.out.println("Press 8 to main");


									int choice2 = scanner.nextInt();
									switch (choice2) {
									case 1:
										System.out.println("Enter Book author");
										String author = scanner.next();
										BookBean bean4 = new BookBean();
										bean4.setAuthor(author);
										BookBean list1 = service2.searchBookAuthor(author);
										if(list1!=null) {
											System.out.println(list1.getId());
											System.out.println(list1.getName());
											System.out.println(list1.getAuthor());
											System.out.println(list1.getCategory());
											System.out.println(list1.getPublishername());
											System.out.println(list1);
										} else {
											System.out.println("Book Not Found");
										}
										break;
									case 2:
										System.out.println("Enter book Name");
										String bookName1 = scanner.next();
										BookBean bean3 = new BookBean();
										bean3.setName(bookName1);
										BookBean list = service2.searchBookTitle(bookName1);
										if(list!=null) {
											System.out.println(list.getId());
											System.out.println(list.getName());
											System.out.println(list.getAuthor());
											System.out.println(list.getCategory());
											System.out.println(list.getPublishername());
											System.out.println(list);
										} else {
											System.out.println("Book Not Found");
										}
										break;
									case 3:
										
										System.out.println("Enter Book id");
										int id = scanner.nextInt();
										BookBean bean5 = new BookBean();
										bean5.setId(id);
										BookBean list2 = service2.searchBookType(id);
										if(list2!=null) {
											System.out.println(list2.getId());
											System.out.println(list2.getName());
											System.out.println(list2.getAuthor());
											System.out.println(list2.getCategory());
											System.out.println(list2.getPublishername());
											System.out.println(list2);
										} else {
											System.out.println("Book Not Found");
										}
										break;
									case 4:
										LinkedList<Integer> ids = service2.getBookIds();
										for (Integer integer : ids) {

											if (integer != null) {
												System.out.println(integer);
											} else {
												System.out.println("No Books Ids are available");
											}
										}
										break;
									case 5:
										List<BookBean> info = service2.getBooksInfo();
										for (BookBean bookBean : info) {

											if (bookBean != null) {
												System.out.println(bookBean);
											} else {
												System.out.println("Books info is not presernt");
											}
										}
										break;
									case 6:
										System.out.println("enter id");
										int returned = scanner.nextInt();
										boolean returnBook = service2.returnBook(returned);
										if(returnBook) {
											System.out.println("Book returned");
										}else {
											System.out.println("book not returned");
										}
										break;
									case 7:
					
										System.out.println("enter book id"); 
										int reqBook = scanner.nextInt();
										
										boolean book = service2.req(reqBook,null); 
										if(book) {
											
											adao.issueBook(reqBook, null);
											System.out.println("requested successfully");
										}else {
											System.out.println("book not found");
											}
					 
										break;
									case 8:
										doReg();
									default:
										break;
									}
								} catch (Exception e) {
									System.out.println("Invalid Credentials");
								}
								break;

							
							
							}while(true);
						} 
					}


