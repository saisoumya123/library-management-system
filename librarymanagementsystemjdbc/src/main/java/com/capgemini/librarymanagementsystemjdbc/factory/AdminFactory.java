package com.capgemini.librarymanagementsystemjdbc.factory;

import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAOImp;
import com.capgemini.librarymanagementsystemjdbc.service.AdminService;
import com.capgemini.librarymanagementsystemjdbc.service.AdminServiceImp;

public class AdminFactory {
	public static AdminDAO getAdminDAO() {
		return new AdminDAOImp();
	}
	public static AdminService getAdminService() {
		return new AdminServiceImp();
	}
}
