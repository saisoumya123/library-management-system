package com.capgemini.librarymanagementsystem.controller;


import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.capgemini.librarymanagementsystem.dto.AdminBean;
import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.StudentBean;
import com.capgemini.librarymanagementsystem.exception.ValidationException;
import com.capgemini.librarymanagementsystem.factory.AdminFactory;
import com.capgemini.librarymanagementsystem.factory.StudentFactory;
import com.capgemini.librarymanagementsystem.service.AdminService;
import com.capgemini.librarymanagementsystem.service.StudentService;
import com.capgemini.librarymanagementsystem.validation.ValidationAdminStudent;



public class AdminController {
	public static void main(String[] args) {
		doReg();
	}

	
				public static void doReg() {

					boolean flag = false;

					int regId = 0; 
					String regName = null; 
					String regMobile = null;
					String regEmail = null;
					String regPassword = null;

					int regId1 = 0; 
					String regName1 = null; 
					String regMobile1 = null;
					String regEmail1 = null;
					String regPassword1 = null;

					int bookId = 0; 
					String bookAuthor = null; 
					String bookName = null;
					String bookCategory = null;
					String bookPublisherName = null;
					String bookIssuedate = null;
					String bookReturndate = null;

					ValidationAdminStudent validation = new ValidationAdminStudent();

					Scanner scanner = new Scanner(System.in);

					do {
						System.out.println("----------WELCOME TO LIBRARY-----------");
						System.out.println("Press 1 to Admin Page");
						System.out.println("Press 2 to Student Page");
						System.out.println("-----------------------------------");

						int i = scanner.nextInt();
						switch(i) {
						case 1:
							AdminService service = AdminFactory.getAdminService();
							do{
								System.out.println("-----------------------------------");
								System.out.println("Press 1 to Admin Register");
								System.out.println("Press 2 to Login");
								System.out.println("Press 3 to exit");
								System.out.println("-----------------------------------");
								int choice = scanner.nextInt();
								switch (choice) {
								case 1:
									do {
										try {
											System.out.println("Enter ID :");
											regId = scanner.nextInt();
											validation.validatedId(regId);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Id should contains only digits");
										} catch (ValidationException e) {
											flag = false;
											System.err.println(e.getMessage());
										} catch (javax.xml.bind.ValidationException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} while (!flag);



									do {
										try {
											System.out.println("Enter Name :");
											regName = scanner.next();
											validation.validatedName(regName);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Name should contains only Alphabates");
										} catch (ValidationException e) {
											flag = false;
											System.err.println(e.getMessage());
										} catch (javax.xml.bind.ValidationException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} while (!flag);



									do {
										try {
											System.out.println("Enter Mobile :");
											regMobile = scanner.next();
											validation.validatedMobile(regMobile);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Mobile Number  should contains only numbers");
										} catch (ValidationException e) {
											flag = false;
											System.err.println(e.getMessage());
										} catch (javax.xml.bind.ValidationException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} while (!flag);



									do {
										try {
											System.out.println("Enter Email :");
											regEmail = scanner.next();
											validation.validatedEmail(regEmail);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Email should be proper ");
										} catch (ValidationException e) {
											flag = false;
											System.err.println(e.getMessage());
										} catch (javax.xml.bind.ValidationException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} while (!flag);



									do {
										try {
											System.out.println("Enter Password :");
											regPassword = scanner.next();
											validation.validatedPassword(regPassword);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Enter correct Password ");
										} catch (ValidationException e) {
											flag = false;
											System.err.println(e.getMessage());
										} catch (javax.xml.bind.ValidationException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} while (!flag);

									AdminBean bean = new AdminBean();
									bean.setAid(regId);
									bean.setAname(regName);
									bean.setEmail(regEmail);
									bean.setPassword(regPassword);

									boolean check = service.register(bean);
									if(check) {
										System.out.println("Registered");
									} else {
										System.out.println("Email already exist");
									}	

									break;

								case 2:
									System.out.println("Enter email :");
									String email = scanner.next();
									System.out.println("Enter Password :");
									String password = scanner.next();
									try {
										AdminBean authBean = service.auth(email, password);
										System.out.println("Logged in");

										do {
											System.out.println("-----------------------------------");
											System.out.println("Press 1 to Add Books");
											System.out.println("Press 2 to update");
											System.out.println("Press 3 to Search the Book by Author");
											System.out.println("Press 4 to Search the Book by Title");
											System.out.println("Press 5 to Search the Book by Category");
											System.out.println("Press 6 to remove the Books");
											System.out.println("Press 7 to Get the Book Id's");
											System.out.println("Press 8 to Get the All The Books ");
											System.out.println("Press 9 to Book Issue");
											//System.out.println("Press 10 to Show Users");
											System.out.println("Press 10 to Show Requests");
											System.out.println("Press 11 Receive Returned Book");
											System.out.println("Press 12 to main");
											System.out.println("-----------------------------------");
											int choice1 = scanner.nextInt();
											switch (choice1) {
											case 1:

												do {
													try {
														System.out.println("Enter Book-ID :");
														bookId = scanner.nextInt();
														validation.validatedId(bookId);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("Id should contains only digits");
													} catch (ValidationException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);




												do {
													try {
														System.out.println("Enter Book Name :");
														bookName = scanner.next();
														validation.validatedName(bookName);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("Book-Name should contains only Alphabates");
													} catch (ValidationException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);



												do {
													try {
														System.out.println("Enter Author :");
														bookAuthor = scanner.next();
														validation.validatedName(bookAuthor);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("Author Name should contains only Alphabates");
													} catch (ValidationException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);


												do {
													try {
														System.out.println("Enter Category :");
														bookCategory = scanner.next();
														validation.validatedName(bookCategory);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("Book-Category should contains only Alphabates");
													} catch (ValidationException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);


												do {
													try {
														System.out.println("Enter PublisherName :");
														bookPublisherName = scanner.next();
														validation.validatedName(bookPublisherName);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("Book-PublisherName should contains only Alphabates");
													} catch (ValidationException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												/*
												 * do { try { System.out.println("Enter IssueDate :"); bookIssuedate =
												 * scanner.next(); validation.validatedDate(bookIssuedate); flag = true; } catch
												 * (InputMismatchException e) { flag = false;
												 * System.err.println("Book-Issue Date should be in dd/mm/yyyy format"); } catch
												 * (ValidationException e) { flag = false; System.err.println(e.getMessage()); }
												 * } while (!flag);
												 */

												BookBean bean1 = new BookBean();
												bean1.setId(bookId);	
												bean1.setName(bookName);
												bean1.setCategory(bookCategory);
												bean1.setAuthor(bookAuthor);
												bean1.setPublishername(bookPublisherName);
												//bean1.setIssuedate(bookIssuedate);
												boolean check2 = service.addBook(bean1);
												if(check2) {
													System.out.println("Book Added");
												} else {
													System.out.println("Book already exist");
												}
												break;
											case 2:
												System.out.println("Enter the updated id :");
												int bid= scanner.nextInt();

												BookBean bean2 = new BookBean();
												bean2.setId(bid);
												int updated = service.update(bid);
												if (updated != 0) {
													System.out.println("Book is updated");
												} else {
													System.out.println("Book is not updated");
												}
												break;
											case 3:
												System.out.println("Search the book by the Author Name:");
												String author = scanner.next();

												BookBean bean3 = new BookBean();
												bean3.setAuthor(author);
												List<BookBean> bookauthor = service.searchBookAuthor(author);
												for (BookBean bookBean : bookauthor) {

													if (bookBean != null) {
														System.out.println("Book_Id is-->"+bookBean.getId());
														System.out.println("Book_Name is-->"+bookBean.getName());
														System.out.println("Book_Author is-->"+bookBean.getAuthor());
														System.out.println("Book_Category is-->"+bookBean.getCategory());
														System.out.println("Book_PublisherName is-->"+bookBean.getPublishername());
													} else {
														System.out.println("No books are available written by this author");
													}
												}
												break;
											case 4:
												System.out.println("  Search the book by the Book_Title :");
												String btitle = scanner.next();

												BookBean bean4 = new BookBean();
												bean4.setAuthor(btitle);
												List<BookBean> booktitle = service.searchBookTitle(btitle);
												for (BookBean bookBean : booktitle) {	
													if (bookBean != null) {
														System.out.println("-----------------------------------");
														System.out.println("Book_Id is-->"+bookBean.getId());
														System.out.println("Book_Name is-->"+bookBean.getName());
														System.out.println("Book_Author is-->"+bookBean.getAuthor());
														System.out.println("Book_Category is-->"+bookBean.getCategory());
														System.out.println("Book_PublisherName is-->"+bookBean.getPublishername());
													} else {
														System.out.println("No books are available with this title.");
													}
												}
												break;
											case 5:
												System.out.println("Search the book by the Book_Id's :");
												int bids = scanner.nextInt();

												BookBean bean5 = new BookBean();
												bean5.setId(bids);
												List<BookBean> bookIds = service.searchBookType(bids);
												for (BookBean bookBean : bookIds) {
													if (bookBean != null) {
														System.out.println("Book_Id is-->"+bookBean.getId()
														+"Book_Name is-->"+bookBean.getName()
														+"Book_Author is-->"+bookBean.getAuthor()
														+"Book_Category is-->"+bookBean.getCategory()
														+"Book_PublisherName is-->"+bookBean.getPublishername());
													} else {
														System.out.println("No books are available with this Id.");
													}
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
													boolean remove = service.delete(book_Id);
													if (remove) {
														System.out.println("The Book is removed");
													} else {
														System.out.println("The Book is not removed");
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
												LinkedList<BookBean> info = service.getBooksInfo();
												for (BookBean bookBean : info) {

													if (bookBean != null) {
														System.out.println("-----------------------------------");
														System.out.println("Book_Id is-->"+bookBean.getId());
														System.out.println("Book_Name is-->"+bookBean.getName());
														System.out.println("Book_Author is-->"+bookBean.getAuthor());
														System.out.println("Book_Category is-->"+bookBean.getCategory());
														System.out.println("Book_PublisherName is-->"+bookBean.getPublishername());
													} else {
														System.out.println("Books info is not present");
													}
												}
												break;
											case 9:
												StudentBean userBean2 = new StudentBean();
												BookBean bookBean2 = new BookBean();
												System.out.println("enter Book Id");
												int bId = scanner.nextInt();
												System.out.println("enter User Id");
												int uId = scanner.nextInt();

												bookBean2.setId(bId);;
												userBean2.setId(uId);

												try {
													boolean isIssued = service.bookIssue(userBean2, bookBean2);
													if (isIssued) {
														System.out.println("Book Issued");
													} else {
														System.out.println("Book cannot be issued");
													}

												} catch (Exception e) {
													System.out.println("Invalid data request book cannot be issued");
												}
												break;
									/*
									 * case 10: try { System.out.println("Users of Library are :");
									 * System.out.println("-------------------------------");
									 * 
									 * List<StudentBean> userInfos = service.showUsers(); for (StudentBean infos :
									 * userInfos) {
									 * 
									 * System.out.println("User id ---------- " + infos.getId());
									 * System.out.println("User Name -------- " + infos.getName());
									 * System.out.println("User Email------ " + infos.getEmail());
									 * System.out.println("User No Of Books Borrowed ------- " +
									 * infos.getBooksBorrowed());
									 * 
									 * } } catch (Exception e) { System.out.println("no books present in library");
									 * } break;
									 */
											case 10:
												try {
													System.out.println("Requests for Books are :");
													System.out.println("-------------------------------");

													List<RequestBean> requestInfos = service.showRequests();
													for (RequestBean info1 : requestInfos) {

														System.out.println("Book id ---------- " + info1.getBookInfo().getId());
														System.out.println("Book Name -------- " + info1.getBookInfo().getAuthor());
														System.out.println("User id----------- " + info1.getStudentInfo().getId());
														System.out.println("User Name -------- " + info1.getStudentInfo().getName());
														System.out.println("Book Issued ------" + info1.isIssued());
														System.out.println("Book Returned------" + info1.isReturned());

													}
												} catch (Exception e) {
													System.out.println("no books present in library");
												}
												break;
											case 11:
												System.out.println("Receive Returned Book");
												System.out.println("-----------------------");
												System.out.println("Enter Student Id");
												int user1 = scanner.nextInt();
												System.out.println("Enter Book Id");
												int book1 = scanner.nextInt();

												StudentBean student = new StudentBean();
												BookBean book = new BookBean();
												student.setId(user1);
												book.setId(book1);
												boolean isReceive = service.isBookReceived(student, book);
												if(isReceive) {
													System.out.println(" Received Returned book");
												}else {
													System.out.println("Invalid ! Admin unable to receive");
												}
												break;
											case 12:
												doReg();
											default:
												System.out.println("Invalid Choice");
												break;
											}
										} while(true);
									} catch (Exception e) {
										System.out.println("Invalid Credentials");
									}

									break;
								case 3:
									doReg();
									break;
								default:
									System.out.println("Invalid Choice");
									break;
								}
							} while(true);

						case 2:
							StudentService service1 = StudentFactory.getStudentService();
							do {
								System.out.println("-----------------------------------");
								System.out.println("Press 1 to Student Register");
								System.out.println("Press 2 to Student Login");
								System.out.println("3 to main");
								System.out.println("-----------------------------------");
								int choice = scanner.nextInt();
								switch (choice) {
								case 1:
									do {
										try {
											System.out.println("Enter ID :");
											regId1 = scanner.nextInt();
											validation.validatedId(regId1);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Id should contains only digits");
										} catch (ValidationException e) {
											flag = false;
											System.err.println(e.getMessage());
										} catch (javax.xml.bind.ValidationException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} while (!flag);




									do {
										try {
											System.out.println("Enter Name :");
											regName1 = scanner.next();
											validation.validatedName(regName1);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Name should contains only Alphabates");
										} catch (ValidationException e) {
											flag = false;
											System.err.println(e.getMessage());
										} catch (javax.xml.bind.ValidationException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} while (!flag);


									do {
										try {
											System.out.println("Enter Mobile :");
											regMobile1 = scanner.next();
											validation.validatedMobile(regMobile1);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Mobile Number  should contains only numbers");
										} catch (ValidationException e) {
											flag = false;
											System.err.println(e.getMessage());
										} catch (javax.xml.bind.ValidationException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} while (!flag);


									do {
										try {
											System.out.println("Enter Email :");
											regEmail1 = scanner.next();
											validation.validatedEmail(regEmail1);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Email should be proper ");
										} catch (ValidationException e) {
											flag = false;
											System.err.println(e.getMessage());
										} catch (javax.xml.bind.ValidationException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} while (!flag);

									do {
										try {
											System.out.println("Enter Password :");
											regPassword1 = scanner.next();
											validation.validatedPassword(regPassword1);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Enter correct Password ");
										} catch (ValidationException e) {
											flag = false;
											System.err.println(e.getMessage());
										} catch (javax.xml.bind.ValidationException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} while (!flag);

									StudentBean bean1 = new StudentBean();
									bean1.setId(regId1);
									bean1.setName(regName1);
									
									bean1.setEmail(regEmail1);
									bean1.setPassword(regPassword1);

									boolean check = service1.register(bean1);
									if(check) {
										System.out.println("Registered");
									} else {
										System.out.println("Email already exist");
									}
									break;
								case 2:
									System.out.println("Enter email :");
									String email = scanner.next();
									System.out.println("Enter Password :");
									String password = scanner.next();
									try {
										StudentBean studentBean = service1.auth(email, password);
										System.out.println("Logged in");
										do {
											System.out.println("--------------------------------------------");
											System.out.println("Press 1 to Search the Book by Author");
											System.out.println("Press 2 to Search the Book by Title");
											System.out.println("Press 3 to Search the Book by Id");
											System.out.println("Press 4 to Get the Book Id's");
											System.out.println("Press 5 to Get the Books Information");
											System.out.println("Press 6 to Request the Book");
											System.out.println("Press 7 to Return the Book");
											System.out.println("Press 8 to main");
											System.out.println("--------------------------------------------");
											int choice2 = scanner.nextInt();
											switch (choice2) {
											case 1:
												System.out.println("Search the book by the Author Name :");
												String author = scanner.next();

												BookBean bean2 = new BookBean();
												bean2.setAuthor(author);
												List<BookBean> bookauthor = service1.searchBookAuthor(author);
												for (BookBean bookBean : bookauthor) {

													if (bookBean != null) {
														System.out.println("-----------------------------------");
														System.out.println("Book_Id is-->"+bookBean.getId());
														System.out.println("Book_Name is-->"+bookBean.getName());
														System.out.println("Book_Author is-->"+bookBean.getAuthor());
														System.out.println("Book_Category is-->"+bookBean.getCategory());
														System.out.println("Book_PublisherName is-->"+bookBean.getPublishername());
													} else {
														System.out.println("No books are available written by this author");
													}
												}
												break;
											case 2:
												System.out.println("Search the book by the Book_Title :");
												String btitle = scanner.next();

												BookBean bean3 = new BookBean();
												bean3.setAuthor(btitle);
												List<BookBean> booktitle = service1.searchBookAuthor(btitle);
												for (BookBean bookBean : booktitle) {	
													if (bookBean != null) {
														System.out.println("-----------------------------------");
														System.out.println("Book_Id is-->"+bookBean.getId());
														System.out.println("Book_Name is-->"+bookBean.getName());
														System.out.println("Book_Author is-->"+bookBean.getAuthor());
														System.out.println("Book_Category is-->"+bookBean.getCategory());
														System.out.println("Book_PublisherName is-->"+bookBean.getPublishername());
													} else {
														System.out.println("No books are available with this title.");
													}
												}
												break;
											case 3:
												System.out.println("  Search the book by the Book_Id's :");
												String bids = scanner.next();

												BookBean bean4 = new BookBean();
												bean4.setAuthor(bids);
												List<BookBean> bookIds = service1.searchBookAuthor(bids);
												for (BookBean bookBean : bookIds) {
													if (bookBean != null) {
														System.out.println("-----------------------------------");
														System.out.println("Book_Id is-->"+bookBean.getId());
														System.out.println("Book_Name is-->"+bookBean.getName());
														System.out.println("Book_Author is-->"+bookBean.getAuthor());
														System.out.println("Book_Category is-->"+bookBean.getCategory());
														System.out.println("Book_PublisherName is-->"+bookBean.getPublishername());
													} else {
														System.out.println("No books are available with this Id.");
													}
												}
												break;
											case 4:
												LinkedList<Integer> ids = service1.getBookIds();
												for (Integer integer : ids) {

													if (integer != null) {
														System.out.println(integer);
													} else {
														System.out.println("No Books Ids are available");
													}
												}
												break;
											case 5:
												LinkedList<BookBean> info = service1.getBooksInfo();


												for (BookBean bookBean : info) {

													if (bookBean != null) {
														System.out.println("-----------------------------------");
														System.out.println("Book_Id is-->"+bookBean.getId());
														System.out.println("Book_Name is-->"+bookBean.getName());
														System.out.println("Book_Author is-->"+bookBean.getAuthor());
														System.out.println("Book_Category is-->"+bookBean.getCategory());
														System.out.println("Book_PublisherName is-->"+bookBean.getPublishername());
													} else {
														System.out.println("Books info is not present");
													}
												}
												break;
											case 6:
												System.out.println("Enter book id");
												int bId = scanner.nextInt();
												BookBean bookBean = new BookBean();
												bookBean.setId(bId);


												System.out.println("Enter user id");
												int userId = scanner.nextInt();
												StudentBean userBean = new StudentBean();
												userBean.setId(userId);


												try {
													RequestBean request = service1.bookRequest(userBean, bookBean);
													System.out.println("Request placed to admin");
													System.out.println("-----------------------------------");
													System.out.println("User Id-----" + request.getStudentInfo().getId());
													System.out.println("User name---" + request.getStudentInfo().getName());
													System.out.println("Book Id-----" + request.getBookInfo().getId());
													System.out.println("Book Name---" + request.getBookInfo().getName());

												} catch (Exception e) {

													System.out.println("Enter valid data or Invalid Request");
												}
												break;
											case 7:

												System.out.println("Returning a book:");
												System.out.println("------------------");
												System.out.println("Enter User Id :");
												int user  = scanner.nextInt();
												System.out.println("Enter Book Id : ");
												int book = scanner.nextInt();
												StudentBean userBean7 = new StudentBean();
												userBean7.setId(user);
												BookBean bookBean7 = new BookBean();
												bookBean7.setId(book);

												try {
													RequestBean requestInfo = service1.bookReturn(userBean7, bookBean7);
													System.out.println("Book is Returning to Admin");
													System.out.println("-----------------------------------");
													System.out.println("User Id ------"+requestInfo.getStudentInfo().getId());
													System.out.println("Book Id ------"+requestInfo.getBookInfo().getId());
													System.out.println("Is Returning --"+requestInfo.isReturned());

												} catch (Exception e) {
													System.out.println("Invalid Return");
												}

												break;
											case 8:
												doReg();
											default:
												break;
											}
										} while(true);
									} catch (Exception e) {
										System.out.println("Invalid Credentials");
									}
									break;

								case 3:
									doReg();
								default:
									System.out.println("Invalid Choice");
									break;
								}
							} while(true);
						}
					}while(true);

				}
			}
		


