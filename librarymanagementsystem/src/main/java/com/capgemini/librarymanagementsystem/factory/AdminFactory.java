package com.capgemini.librarymanagementsystem.factory;


	import com.capgemini.librarymanagementsystem.dao.AdminDAO;
	import com.capgemini.librarymanagementsystem.dao.AdminDAOImp;
	import com.capgemini.librarymanagementsystem.service.AdminService;
	import com.capgemini.librarymanagementsystem.service.AdminServiceImp;

	public class AdminFactory {
		public static AdminDAO getAdminDAO() {
			return new AdminDAOImp();
		}
		public static AdminService  getAdminService() {
			return new AdminServiceImp();
		}
	}

