package com.capgemini.librarymanagementsystemjdbc.factory;

import com.capgemini.librarymanagementsystemjdbc.dao.StudentDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.StudentDAOImp;
import com.capgemini.librarymanagementsystemjdbc.service.StudentService;
import com.capgemini.librarymanagementsystemjdbc.service.StudentServiceImp;

public class StudentFactory {
	public static StudentDAO getStudentDAO() {
		
		return new StudentDAOImp();
	}
	public static StudentService getStudentService() {
		return new StudentServiceImp();
	}
}
