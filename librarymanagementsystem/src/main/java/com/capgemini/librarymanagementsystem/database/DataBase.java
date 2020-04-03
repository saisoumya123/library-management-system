package com.capgemini.librarymanagementsystem.database;


	import java.util.LinkedList;

	import com.capgemini.librarymanagementsystem.dto.AdminBean;
	import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.StudentBean;

	public class DataBase {
		public static final LinkedList<AdminBean> admin = new LinkedList<AdminBean>();
		public static final LinkedList<StudentBean> student = new LinkedList<StudentBean>();
		public static final LinkedList<BookBean> book = new LinkedList<BookBean>();
		public static final LinkedList<RequestBean> request = new LinkedList<RequestBean>();
	}

