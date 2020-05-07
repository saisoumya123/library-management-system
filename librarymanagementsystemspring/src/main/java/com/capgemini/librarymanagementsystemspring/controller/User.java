package com.capgemini.librarymanagementsystemspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.librarymanagementsystemspring.dto.BookBean;
import com.capgemini.librarymanagementsystemspring.dto.UserBean;
import com.capgemini.librarymanagementsystemspring.dto.lmsResponse;
import com.capgemini.librarymanagementsystemspring.service.AdminService;
import com.capgemini.librarymanagementsystemspring.service.StudentService;
import com.capgemini.librarymanagementsystemspring.service.UserService;
import com.capgemini.librarymanagementsystemspring.service.UserServiceImp;

@RestController
public class User {
@Autowired
	UserService service;// = new UserServiceImp();
	AdminService service1;
	StudentService service2;
@PostMapping(path = "/addUser" , consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE},
								produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE}	)

public  lmsResponse addUser(@RequestBody UserBean bean) {
	boolean isAdded =  service.register(bean);
	lmsResponse response = new lmsResponse();
	if(isAdded) {
		response.setMessage("record inserted");
	}else {
		response.setError(true);
		response.setMessage("unable to add");
	}
	return response;
}

@PostMapping(path = "/login")
public lmsResponse authentication(@RequestBody String email , String password) {
	UserBean userLogin = service.auth(email, password);
	lmsResponse response = new lmsResponse();
	if(userLogin != null) {
		response.setMessage("Login succesfull");
	}else {
		response.setError(true);
		response.setMessage("Cannot login");
	}
	return response;
}


@PostMapping(path = "/addBook" , consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE},
								produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
public lmsResponse addBook(@RequestBody BookBean bean) {
	boolean isBookAdded = service1.addBook(bean);
	lmsResponse response = new lmsResponse();
	if(isBookAdded) {
		response.setMessage("Book added succesfully");
	}else {
		response.setError(true);
		response.setMessage("Book cannot be added");
	}
	return response;
	
}

@PutMapping(path = "/bookUpdate" , consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE},
									produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
public lmsResponse updateBook(@RequestBody BookBean bean) {
	boolean isBookUpdated = service1.update(bean);
	lmsResponse response = new lmsResponse();
	if(isBookUpdated) {
		response.setMessage("Book updated succesfully");
	}else {
		response.setError(true);
		response.setMessage("Book cannot be updated");
	}
	return response;
}

@DeleteMapping(path = "/deleteBook/{bId} ", consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE},
	produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
public lmsResponse deleteBook(@PathVariable(name = "bId") int bId) {
	boolean isBookDeleted = service1.delete(bId);
	lmsResponse response = new lmsResponse();
	if(isBookDeleted) {
		response.setMessage("Book deleted succesfully");
	}else {
		response.setError(true);
		response.setMessage("Book cannot be deleted");
	}
	return response;
}

@GetMapping(path = "/getBookId" , produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
public lmsResponse getBookIds() {
	List<Integer> getId = service1.getBookIds();
	lmsResponse response = new lmsResponse();
	if(getId != null && !getId.isEmpty()) {
		response.setMessage("Book id found");
		response.setBookBeanId(getId);
	}else {
		response.setError(true);
		response.setMessage("No id found");
	}
	return response;
}

@GetMapping(path = "/getBookInfo" , produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
public lmsResponse getBookInfo() {
	List<BookBean> getInfo = service1.getBooksInfo();
	lmsResponse response = new lmsResponse();
	if(getInfo != null && !getInfo.isEmpty()) {
		response.setMessage("Book info found");
		response.setBookBeanList(getInfo);
	}else {
		response.setError(true);
		response.setMessage("No info found in db");
	}
	return response;
}

@GetMapping(path = "/getBookByName" ,produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
public lmsResponse getBookByName(String bookTitle) {
	BookBean bean = service1.searchBookTitle(bookTitle);
	lmsResponse response = new lmsResponse();
	if(bean != null) {
		response.setMessage("Book info found");
		response.setBook(bean);
	}else {
		response.setError(true);
		response.setMessage("No info found in db");
	}
	return response;
}

@GetMapping(path = "/getBookByAuthor" , produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
public lmsResponse getBookByAuthor(String author) {
	BookBean bean = service1.searchBookAuthor(author);
	lmsResponse response = new lmsResponse();
	if(bean != null) {
		response.setMessage("Book info found");
		response.setBook(bean);
	}else {
		response.setError(true);
		response.setMessage("No info found in db");
	}
	return response;
}

@GetMapping(path = "/getBookById" , produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
public lmsResponse getBookById(int bId) {
	BookBean bean = service1.searchBookType(bId);
	lmsResponse response = new lmsResponse();
	if(bean != null) {
		response.setMessage("Book info found");
		response.setBook(bean);
	}else {
		response.setError(true);
		response.setMessage("No info found in db");
	}
	return response;
}

@PostMapping(path = "/issueBook" , consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE},
produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
public lmsResponse issueBook(@RequestBody int id , int bId) {
	boolean isBookIssued = service1.issueBook(id, bId);
	lmsResponse response = new lmsResponse();
	if(isBookIssued) {
		response.setMessage("Book issued succesfully");
	}else {
		response.setError(true);
		response.setMessage("Book cannot be issued");
	}
	return response;
}

@PostMapping(path = "/returnBook" , consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE},
produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
public lmsResponse returnBook(@RequestBody int bId , int id) {
	boolean isBookReturned = service1.returnBook(bId, id);
	lmsResponse response = new lmsResponse();
	if(isBookReturned) {
		response.setMessage("Book returned succesfully");
	}else {
		response.setError(true);
		response.setMessage("Book cannot be returned");
	}
	return response;	
}

@PostMapping(path = "/requestBook" , consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE},
produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
public lmsResponse requestBook(@RequestBody int id , int bId) {
	boolean isBookRequested = service2.req(id, bId);
	lmsResponse response = new lmsResponse();
	if(isBookRequested) {
		response.setMessage("Book requested succesfully");
	}else {
		response.setError(true);
		response.setMessage("Book cannot be requested");
	}
	return response;	
}

@PostMapping(path = "/returnRequestBook" , consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE},
produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
public lmsResponse returnRequestBook(@RequestBody int id , int bId) {
	boolean isBookReturnRequested = service2.reqReturnBook(id, bId);
	lmsResponse response = new lmsResponse();
	if(isBookReturnRequested) {
		response.setMessage("Book return requested succesfully");
	}else {
		response.setError(true);
		response.setMessage("Book cannot place return request");
	}
	return response;	
}


}
