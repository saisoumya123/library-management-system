package com.capgemini.librarymanagementsystemspring.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data

public class lmsResponse {
	private boolean error;
	private String message;
	private UserBean user;
	private List<UserBean> userBean;
	private BookBean book;
	private List<Integer> bookBeanId;
	private List<BookBean> bookBeanList;
	private BookIssueDetailsBean bookIssue;
	
}
