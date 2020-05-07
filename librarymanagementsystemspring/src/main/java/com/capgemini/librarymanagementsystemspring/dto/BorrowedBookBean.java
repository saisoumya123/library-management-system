package com.capgemini.librarymanagementsystemspring.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;

@Data
@Entity
@Table(name="borrowbookspring")
public class BorrowedBookBean {
	@EmbeddedId
	private BorrowedBookBeanPK borrowBookPK;
	@Column
	private int id;
//	@Column(insertable = false,updatable = false)
//	private int bid;
//	@Column(insertable = false,updatable = false)
//	private String email;
//	

	
	  @Exclude //@MapsId
	  
	  @ManyToOne
	  
	  @JoinColumn(name="email" , insertable = false,updatable = false) 
	  private UserBean primary;
	 
}
