package com.capgemini.librarymanagementsystemjdbc.dao;

import com.capgemini.librarymanagementsystemjdbc.dto.AdminBean;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;

public interface UserDAO {

	int register(UserBean bean);
	String auth(String email, String password);

}
