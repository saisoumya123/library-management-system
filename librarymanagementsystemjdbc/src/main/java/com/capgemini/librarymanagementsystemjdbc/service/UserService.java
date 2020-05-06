package com.capgemini.librarymanagementsystemjdbc.service;

import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;

public interface UserService {
	boolean register(UserBean bean);
	UserBean auth(String email, String password);
}
