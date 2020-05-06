package com.capgemini.librarymanagementsystemjdbc.service;

import com.capgemini.librarymanagementsystemjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;
import com.capgemini.librarymanagementsystemjdbc.factory.UserFactory;

public class UserServiceImp implements UserService{
	private UserDAO dao = UserFactory.getUser();

	@Override
	public boolean register(UserBean bean) {
		return dao.register(bean);
	}

	@Override
	public UserBean auth(String email, String password) {
		return dao.auth(email, password);
	}
	


	

}
