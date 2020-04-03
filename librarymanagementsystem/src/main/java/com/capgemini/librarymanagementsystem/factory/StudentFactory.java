package com.capgemini.librarymanagementsystem.factory;


	import com.capgemini.librarymanagementsystem.dao.StudentDAO;
	import com.capgemini.librarymanagementsystem.dao.StudentDAOImp;
	import com.capgemini.librarymanagementsystem.service.StudentService;
	import com.capgemini.librarymanagementsystem.service.StudentServiceImp;


	public class StudentFactory {
		public static StudentDAO getStudentDAO() {
			
			return new StudentDAOImp();
		}
		public static StudentService getStudentService() {
			return new StudentServiceImp();
		}
	}

