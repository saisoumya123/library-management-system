package com.capgemini.librarymanagementsystemjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.capgemini.librarymanagementsystemjdbc.utility.JdbcUtility;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;

public class UserDAOImp implements UserDAO {
	PreparedStatement pstmt = null;
	Connection conn = null;
	ResultSet rs = null;

	@Override
	public boolean register(UserBean bean) {
		conn = JdbcUtility.getConnection();
		try {
			pstmt = conn.prepareStatement(QueryMapper.register_user);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getPassword());
			pstmt.setString(4, bean.getMobile());
			pstmt.setString(5, bean.getRole());
			pstmt.setInt(6, bean.getId());
			int count = pstmt.executeUpdate();
			if (bean.getEmail().isEmpty() && count == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			throw new LMSException("Email already exists");
		}

	}

	@Override
	public UserBean auth(String email, String password) {
		UserBean bean = new UserBean();
		conn = JdbcUtility.getConnection();
		try {
			
			pstmt = conn.prepareStatement(QueryMapper.login);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			 rs = pstmt.executeQuery();
				if (rs.next()) {
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("password"));
					bean.setId(rs.getInt("id"));
					bean.setMobile(rs.getString("mobile"));
					bean.setRole(rs.getString("role"));
					bean.setName(rs.getString("name"));
					return bean;
				}

		} catch (Exception e) {
			throw new LMSException("Invalid login credentials");
		}

		return null;

	}
}
