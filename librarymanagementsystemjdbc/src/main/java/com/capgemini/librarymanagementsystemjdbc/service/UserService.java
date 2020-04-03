package com.capgemini.librarymanagementsystemjdbc.service;

import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;

public interface UserService {
	int register(UserBean bean);
	String auth(String email, String password);
}
