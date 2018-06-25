package com.adpostm.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="user_detail")
public class UserDetail {
	@Id
	@GeneratedValue
	@Column(name="pk_user_detail_id")
	private int userDetailId;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	@JsonBackReference
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
	
	@Column(name="image_cdn")
	private String imageCdn;
	
	@Column(name="image_name")
	private String imageName;
	
	@Column(name="image_uuid")
	private String imageUuid;
	
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
	public String getImageCdn(){
		return this.imageCdn;
	}
	public void setImageCdn(String imageCdn){
		this.imageCdn = imageCdn;
	}
	public String getImageName(){
		return this.imageName;
	}
	public void setImageName(String imageName){
		this.imageName = imageName;
	}
	public String getImageUuid(){
		return this.imageUuid;
	}
	public void setImageUuid(String imageUuid){
		this.imageUuid= imageUuid;
	}
	public UserDetail() {}
	private UserDetail(UserDetailBuilder userDetailBuilder) {
		this.userDetailId = userDetailBuilder.userDetailId;
		this.appUser = userDetailBuilder.appUser;
		this.firstName = userDetailBuilder.firstName;
		this.lastName = userDetailBuilder.lastName;
		this.postAddress1 = userDetailBuilder.postAddress1;
		this.postAddress2 = userDetailBuilder.postAddress2;
		this.street = userDetailBuilder.street;
		this.surbub = userDetailBuilder.surbub;
		this.state = userDetailBuilder.state;
		this.postCode = userDetailBuilder.postCode;
		this.mobileNo = userDetailBuilder.mobileNo;
		this.imageCdn = userDetailBuilder.imageCdn;
		this.imageName = userDetailBuilder.imageName;
		this.imageUuid = userDetailBuilder.imageUuid;
	}
	public static class UserDetailBuilder{
		private int userDetailId;
		private AppUser appUser;
		private String firstName;
		private String lastName;
		private String postAddress1;
		private String postAddress2;
		private String street;
		private String surbub;
		private String state;
		private String postCode;
		private String mobileNo;
		private String imageCdn;
		private String imageName;
		private String imageUuid;
		
		public UserDetailBuilder setFirstName(String firstName){
			this.firstName = firstName;
			return this;
		}
		public UserDetailBuilder setLastName(String lastName){
			this.lastName = lastName;
			return this;
		}
		public UserDetailBuilder setPostAddress1(String postAddress1){
			this.postAddress1 = postAddress1;
			return this;
		}
		public UserDetailBuilder setPostAddress2(String postAddress2){
			this.postAddress2 = postAddress2;
			return this;
		}
		public UserDetailBuilder setStreet(String street){
			this.street = street;
			return this;
		}
		public UserDetailBuilder setSurbub(String surbub){
			this.surbub = surbub;
			return this;
		}
		public UserDetailBuilder setState(String state){
			this.state = state;
			return this;
		}
		public UserDetailBuilder setPostcode(String postcode){
			this.postCode = postcode;
			return this;
		}
		public UserDetailBuilder setMobileNo(String mobileNo){
			this.mobileNo = mobileNo;
			return this;
		}
		public UserDetailBuilder setAppUser(AppUser appUser){
			this.appUser = appUser;
			return this;
		}
		public UserDetailBuilder setImageCdn(String imageCdn){
			this.imageCdn = imageCdn;
			return this;
		}
		public UserDetailBuilder getImageName(String imageName){
			this.imageName = imageName;
			return this;
		}
		public UserDetailBuilder setImageName(String imageName){
			this.imageName = imageName;
			return this;
		}
		public UserDetailBuilder setImageUuid(String imageUuid){
			this.imageUuid= imageUuid;
			return this;
		}
		public UserDetail build() {
			return new UserDetail(this);
		}
	}
}
