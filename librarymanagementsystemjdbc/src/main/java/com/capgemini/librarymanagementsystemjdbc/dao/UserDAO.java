package com.capgemini.librarymanagementsystemjdbc.dao;

import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;

public interface UserDAO {

	boolean register(UserBean bean);
	UserBean auth(String email, String password);

}
