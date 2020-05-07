package com.capgemini.librarymanagementsystemspring.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class BorrowedBookBeanPK implements Serializable{
	//@Column(insertable = false,updatable = false)
	private int bid;
	//@Column(insertable = false,updatable = false)
	private String email;
}
