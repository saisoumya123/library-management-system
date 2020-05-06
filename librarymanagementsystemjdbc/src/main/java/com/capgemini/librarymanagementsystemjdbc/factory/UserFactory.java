package com.capgemini.librarymanagementsystemjdbc.factory;

import com.capgemini.librarymanagementsystemjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.UserDAOImp;
import com.capgemini.librarymanagementsystemjdbc.service.UserService;
import com.capgemini.librarymanagementsystemjdbc.service.UserServiceImp;

public class UserFactory {
	public static UserDAO getUser() {
		return new UserDAOImp();
		
	}
	public static UserService getUserService() {

return new UserServiceImp();
	}
}
