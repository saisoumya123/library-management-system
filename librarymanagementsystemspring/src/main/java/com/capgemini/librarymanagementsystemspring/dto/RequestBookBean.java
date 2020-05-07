package com.capgemini.librarymanagementsystemspring.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;

@Data
@Entity
@Table(name="requestbookspring")
//@IdClass(RequestBookPK.class)
public class RequestBookBean {
	@EmbeddedId
	private RequestBookPK reqBookPK;
	
	@Column
	private int id;
	@Column
	private String name;
	@Column
	private String book_title;

	@Column
	private String type;



	@Exclude 
	// @MapsId

	@ManyToOne(cascade=CascadeType.ALL)

	@JoinColumn(name="email" , insertable = false ,updatable = false) 
	private UserBean primary;


}
