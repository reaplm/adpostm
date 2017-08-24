package com.adpostm.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="user_detail")
public class UserDetail {
	@Id
	@GeneratedValue
	@Column(name="pk_detail_id")
	private int userDetailId;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private AppUser appUser;
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "post_addr1")
	private String postAddress1;
	@Column(name = "post_addr2")
	private String postAddress2;
	private String street;
	private String surbub;
	private String state;
	@Column(name = "post_code")
	private String postCode;
	@Column(name = "mobile_no")
	private String mobileNo;
	
	public String getFirstName(){
		return this.firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getLastName(){
		return this.lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
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
	public String getMobileNo(){
		return this.mobileNo;
	}
	public void setMobileNo(String mobileNo){
		this.mobileNo = mobileNo;
	}
	public AppUser getAppUser(){
		return this.appUser;
	}
	public void setAppUser(AppUser appUser){
		this.appUser = appUser;
	}
}
