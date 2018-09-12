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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="app_user")
public class AppUser {
	@Id
	@GeneratedValue
	@Column(name="pk_user_id")
	private Long appUserId;
	
	@OneToOne(mappedBy="appUser", cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	@JsonManagedReference
	private UserDetail userDetail;
	
	@ElementCollection
	private List<Role> roles;
	
	@OneToMany(mappedBy="appUser")
	@JsonBackReference
	private List<Advert> adverts;
	
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	@Column(name="activation_code")
	private String activationCode;
	@Column(columnDefinition="int default 0")
	private int notified = 0;
	@Column(columnDefinition="int default 0")
	private int activated = 0;
	@Column(name="pass_expiry_dt")
	private Date passExpiryDate;
	@Column(name="reg_dt")
	private Date registrationDate;
	@Column(name="last_login_dt")
	private Date lastLoginDate;
	
	public Long getAppUserId(){
		return this.appUserId;
	}
	public void setAppUserId(Long appUserId){
		this.appUserId = appUserId;
	}
	public void setNotified(int notified) {
		this.notified = notified;
	}
	public int getNotified() {
		return this.notified;
	}
	public void setActivated(int activated) {
		this.activated = activated;
	}
	public int getActivated() {
		return this.activated;
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
	public List<Advert> getAdverts(){
		return this.adverts;
	}
	public void setAdverts(List<Advert> adverts){
		this.adverts = adverts;
	}
	public AppUser() {}
	private AppUser(AppUserBuilder appUserBuilder) {
		this.appUserId = appUserBuilder.appUserId;
		this.notified = appUserBuilder.notified;
		this.activated = appUserBuilder.activated;
		this.userDetail = appUserBuilder.userDetail;
		this.roles = appUserBuilder.roles;
		this.adverts = appUserBuilder.adverts;
		this.password = appUserBuilder.password;
		this.email =appUserBuilder. email;
		this.activationCode = appUserBuilder.activationCode;
		this.passExpiryDate = appUserBuilder.passExpiryDate;
		this.registrationDate = appUserBuilder.registrationDate;
		this.lastLoginDate = appUserBuilder.lastLoginDate;
	}
	
	public static class AppUserBuilder{
		private Long appUserId;
		private int notified;
		private int activated;
		private UserDetail userDetail;
		private List<Role> roles;
		private List<Advert> adverts;
		private String password;
		private String email;
		private String activationCode;
		private Date passExpiryDate;
		private Date registrationDate;
		private Date lastLoginDate;
		
		public AppUserBuilder setAppUserId(Long appUserId){
			this.appUserId = appUserId;
			return this;
		}
		public  AppUserBuilder setNotified(int notified) {
			this.notified = notified;
			return this;
		}
		public  AppUserBuilder setActivated(int activated) {
			this.activated = activated;
			return this;
		}

		public AppUserBuilder setUserDetail(UserDetail userDetail){
			this.userDetail = userDetail;
			return this;
		}
		public AppUserBuilder setRoles(List<Role> roles){
			this.roles = roles;
			return this;
		}
		public AppUserBuilder setPassword(String password){
			this.password = password;
			return this;
		}
		public AppUserBuilder setEmail(String email){
			this.email = email;
			return this;
		}
		public AppUserBuilder setPassExpiryDate(Date passExpiryDate){
			this.passExpiryDate = passExpiryDate;
			return this;
		}
		public AppUserBuilder setRegistrationDate(Date registrationDate){
			this.registrationDate = registrationDate;
			return this;
		}
		public AppUserBuilder setLastLoginDate(Date lastLoginDate){
			this.lastLoginDate = lastLoginDate;
			return this;
		}
		public AppUserBuilder setAdverts(List<Advert> adverts){
			this.adverts = adverts;
			return this;
		}
		public AppUser build() {
			return new AppUser(this);
		}
	}
}
