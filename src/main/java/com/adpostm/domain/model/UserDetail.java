package com.adpostm.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="user_detail")
public class UserDetail {
	@Id
	@GeneratedValue
	@Column(name="pk_user_detail_id")
	private Long userDetailId;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	@JsonBackReference
	private AppUser appUser;
	
	@OneToOne(mappedBy="userDetail", 
			cascade= {CascadeType.ALL},
			fetch=FetchType.EAGER)
	@JsonManagedReference
	private Address address;
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
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
	public Address getAddress(){
		return this.address;
	}
	public void setAddress(Address address){
		this.address = address;
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
		this.address = userDetailBuilder.address;
		this.mobileNo = userDetailBuilder.mobileNo;
		this.imageCdn = userDetailBuilder.imageCdn;
		this.imageName = userDetailBuilder.imageName;
		this.imageUuid = userDetailBuilder.imageUuid;
	}
	public static class UserDetailBuilder{
		private Long userDetailId;
		private AppUser appUser;
		private String firstName;
		private String lastName;
		private Address address;
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
		public UserDetailBuilder setAddress(Address address){
			this.address = address;
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
