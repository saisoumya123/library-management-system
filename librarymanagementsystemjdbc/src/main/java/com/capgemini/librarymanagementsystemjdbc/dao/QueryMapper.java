package com.capgemini.librarymanagementsystemjdbc.dao;

public interface QueryMapper {
	String register_user = "insert into user values(?,?,?,?,?,?)";
	String login = "select * from user where email=? and password=?";
	String add_book = "insert into book_details values (?,?,?,?,?,?,?,?,?,?)";
	String search_book_name = "select * from book_details where book_title=?";
	String search_book_author = "select * from book_details where author=?";
	String search_book_id = "select * from book_details where bid=?";
	String delete = "delete from book_details where bid=?";
	String get_allBook = "select * from book_details";
	String update = "update book_details set book_title=? where bid=?";
	String role_que = "select role from user where email=?";
	String req_book = "select * from bookdetails where id = ?";
	String get_bookId = "select bid from book_details";
	String book_details = "select * from book_details where bid=? and copies >=1";
	String Request_book = "select * from request_book where bid=? and id=?";
	String borrow_book = "select count(id) as id from borrow_book where id=?";
	String book_issue = "insert into book_issue values(?,?,?,?)";
	String borrowBookValues = "insert into borrow_book values(?,?,(select email from user where id=?))";
	String reqDelete = "delete from request_book where id=? and bid=?";
	String updateBookDetails = "update book_details set copies= copies-1 where bid=?";
	
	String issueBook = "select * from book_issue where bid=? and id=?";
	String reqBook = "select * from request_book where bid=? and id=?";
	String updateBook_Details = "update book_details set copies = copies+1 where bid=?";
	String delBookDetails = "delete from book_issue where bid=? and id=?";
	String delBorrowBook = "delete from borrow_book where bid=? and id=?";
	String delRequestBook = "delete from request_book where bid=? and id=?";
	
	String reqBookDetails = "select * from book_details where bid=?";
	String reqInsert = "insert into request_book values(?,?,(select name from user where id=?),(select book_title from book_details where bid=?),(select email from user where id=?),?)";
	
	String retBookDetails = "select * from book_details where bid=?";
	String retRequest = "insert into request_book values(?,?,(select name from user where id=?),(select book_title from book_details where bid=?),(select email from user where id=?),?)";

}
