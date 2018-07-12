package com.adpostm.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="address")
public class Address {
	@Id
	@GeneratedValue
	@Column(name="pk_address_id")
	private int addressId;

	@Column(name = "post_addr1")
	private String postAddress1;
	@Column(name = "post_addr2")
	private String postAddress2;
	private String street;
	private String surbub;
	private String state;
	@Column(name = "post_code")
	private String postCode;
	
	@OneToOne
	@JoinColumn(name="fk_detail_id")
	@JsonBackReference
	private UserDetail userDetail;
	
	public String getPostAddress1(){
		return this.postAddress1;
	}
	public void setPostAddress1(String postAddress1){
		this.postAddress1 = postAddress1;
	}
	public String getPostAddress2(){
		return this.postAddress2;
	}
	public void setPostAddress2(String postAddress2){
		this.postAddress2 = postAddress2;
	}
	public String getStreet(){
		return this.street;
	}
	public void setStreet(String street){
		this.street = street;
	}
	public String getSurbub(){
		return this.surbub;
	}
	public void setSurbub(String surbub){
		this.surbub = surbub;
	}
	public String getState(){
		return this.state;
	}
	public void setState(String state){
		this.state = state;
	}
	public String getPostCode(){
		return this.postCode;
	}
	public void setPostcode(String postcode){
		this.postCode = postcode;
	}
	public void setUserDetail(UserDetail userDetail){
		this.userDetail = userDetail;
	}
	public UserDetail getUserDetail(){
		return this.userDetail;
	}

}
