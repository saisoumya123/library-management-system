package com.capgemini.librarymanagementsystemspring.dao;

import com.capgemini.librarymanagementsystemspring.dto.UserBean;

public interface UserDAO {

	boolean register(UserBean bean);
	UserBean auth(String email, String password);

}
