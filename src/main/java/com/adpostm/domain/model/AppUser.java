package com.adpostm.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="app_user")
public class AppUser {
	@Id
	@GeneratedValue
	@Column(name="pk_user_id")
	private int appUserId;
	
	@OneToOne(mappedBy="appUser", cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	private UserDetail userDetail;
	
	@ElementCollection
	private List<Role> roles;
	
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	@Column(name="activation_code")
	private String activationCode;
	@Column(name="pass_expiry_dt")
	private Date passExpiryDate;
	@Column(name="reg_dt")
	private Date registrationDate;
	@Column(name="last_login_dt")
	private Date lastLoginDate;
	
	public int getAppUserId(){
		return this.appUserId;
	}
	public void setAppUserId(int appUserId){
		this.appUserId = appUserId;
	}
	public UserDetail getUserDetail(){
		return this.userDetail;
	}
	public void setUserDetail(UserDetail userDetail){
		this.userDetail = userDetail;
	}
	public List<Role> getRoles(){
		return this.roles;
	}
	public void setRoles(List<Role> roles){
		this.roles = roles;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public Date getPassExpiryDate(){
		return this.passExpiryDate;
	}
	public void setPassExpiryDate(Date passExpiryDate){
		this.passExpiryDate = passExpiryDate;
	}
	public Date getRegistrationDate(){
		return this.registrationDate;
	}
	public void setRegistrationDate(Date registrationDate){
		this.registrationDate = registrationDate;
	}
	public Date getLastLoginDate(){
		return this.lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate){
		this.lastLoginDate = lastLoginDate;
	}
}
