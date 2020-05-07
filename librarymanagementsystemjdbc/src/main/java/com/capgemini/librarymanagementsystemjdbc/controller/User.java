package com.capgemini.librarymanagementsystemjdbc.controller;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.ValidationException;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;
import com.capgemini.librarymanagementsystemjdbc.factory.AdminFactory;
import com.capgemini.librarymanagementsystemjdbc.factory.StudentFactory;
import com.capgemini.librarymanagementsystemjdbc.factory.UserFactory;
import com.capgemini.librarymanagementsystemjdbc.service.AdminService;
import com.capgemini.librarymanagementsystemjdbc.service.StudentService;
import com.capgemini.librarymanagementsystemjdbc.service.UserService;
import com.capgemini.librarymanagementsystemjdbc.validation.ValidationAdminStudent;

public class User {
	public static void main(String[] args) {
		UserJdbc.doReg();
	}
}
