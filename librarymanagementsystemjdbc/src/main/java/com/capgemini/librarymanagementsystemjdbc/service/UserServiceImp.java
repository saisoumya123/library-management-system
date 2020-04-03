package com.capgemini.librarymanagementsystemjdbc.service;

import com.capgemini.librarymanagementsystemjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;
import com.capgemini.librarymanagementsystemjdbc.factory.UserFactory;

public class UserServiceImp implements UserService{
	private UserDAO dao = UserFactory.getUser();

	@Override
	public int register(UserBean bean) {
		return dao.register(bean);
	}

	@Override
	public String auth(String email, String password) {
		return dao.auth(email, password);
	}
	


	

}
