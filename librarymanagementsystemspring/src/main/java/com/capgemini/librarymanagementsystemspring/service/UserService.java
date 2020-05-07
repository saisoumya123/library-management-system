package com.capgemini.librarymanagementsystemspring.service;

import com.capgemini.librarymanagementsystemspring.dto.UserBean;

public interface UserService {
	boolean register(UserBean bean);
	UserBean auth(String email, String password);
}
