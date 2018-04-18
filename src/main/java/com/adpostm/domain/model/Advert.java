package com.adpostm.domain.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.adpostm.domain.enumerated.AdvertStatus;

@Entity
@Table(name="advert")
public class Advert {
	@Id
	@GeneratedValue
	@Column(name="pk_advert_id")
	private int advertId;
	@OneToOne(mappedBy="advert", cascade=CascadeType.REMOVE)
	private AdvertDetail advertDetail;
	@ManyToOne
	@JoinColumn(name="fk_menu_id", nullable=false)
	private Menu menu;
	@ManyToOne
	@JoinColumn(name="fk_appuser_id", nullable=false)
	private AppUser appUser;

	@Column(name="posted_date")
	private Date postedDate;
	@Column(name="published_date")
	private Date publishedDate;
	@Column(name="approved_date")
	private Date approvedDate;
	@Column(name="submitted_date")
	private Date submittedDate;
	@Column(name="rejected_date")
	private Date rejectedDate;
	
	@Column(name="advert_status")
	private AdvertStatus advertStatus = AdvertStatus.SUBMITTED;
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Menu getMenu() {
		return this.menu;
	}
	public AppUser getAppUser(AppUser appUser) {
		return this.appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	public AdvertDetail getAdvertDetail() {
		return this.advertDetail;
	}
	public void setAdvertDetail(AdvertDetail advertDetail) {
		this.advertDetail = advertDetail;
	}
	public Date getPostedDate() {
		return this.postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	public Date getPublishedDate() {
		return this.publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	public Date getApprovedDate() {
		return this.publishedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public Date getSubmittedDate() {
		return this.submittedDate;
	}
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}
	public Date getRejectedDate() {
		return this.rejectedDate;
	}
	public void setRejectedDate(Date rejectedDate) {
		this.rejectedDate = rejectedDate;
	}
	public AdvertStatus getAdvertStatus() {
		return this.advertStatus;
	}
	public void setAdvertStatus(AdvertStatus advertStatus) {
		this.advertStatus = advertStatus;
	}
}
