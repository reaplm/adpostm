package com.adpostm.domain.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

//import org.hibernate.search.annotations.Indexed;

import com.adpostm.domain.enumerated.AdvertStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
//@Indexed
@Table(name="advert")
public class Advert {
	@Id
	@GeneratedValue
	@Column(name="pk_advert_id")
	private Long advertId;
	
	//@IndexedEmbedded
	@OneToOne(mappedBy="advert", cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	private AdvertDetail advertDetail;
	
	@ManyToOne
	@JoinColumn(name="fk_menu_id", nullable=false)
	@JsonBackReference
	private Menu menu;
	
	@ManyToOne
	@JoinColumn(name="fk_appuser_id", nullable=false)
	@JsonManagedReference
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
	
	public void setAdvertId(Long advertId) {
		this.advertId = advertId;
	}
	public Long getAdvertId() {
		return this.advertId;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Menu getMenu() {
		return this.menu;
	}
	public AppUser getAppUser() {
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
	public Advert() {}
	public Advert(AdvertBuilder advertBuilder) {
		this.advertId = advertBuilder.advertId;
		this.advertDetail = advertBuilder.advertDetail;
		this.menu = advertBuilder.menu;
		this.appUser = advertBuilder.appUser;
		this.postedDate = advertBuilder.postedDate;
		this.publishedDate = advertBuilder.publishedDate;
		this.approvedDate = advertBuilder.approvedDate;
		this.submittedDate = advertBuilder.submittedDate;
		this.rejectedDate = advertBuilder.rejectedDate;
		this.advertStatus = advertBuilder.advertStatus;
	}
	public static class AdvertBuilder{
		private Long advertId;
		private AdvertDetail advertDetail;
		private Menu menu;
		private AppUser appUser;
		private Date postedDate;
		private Date publishedDate;
		private Date approvedDate;
		private Date submittedDate;
		private Date rejectedDate;
		private AdvertStatus advertStatus;
		
		public AdvertBuilder setAdvertId(Long advertId) {
			this.advertId = advertId;
			return this;
		}
		public AdvertBuilder setMenu(Menu menu) {
			this.menu = menu;
			return this;
		}
		public AdvertBuilder setAppUser(AppUser appUser) {
			this.appUser = appUser;
			return this;
		}
		public AdvertBuilder setAdvertDetail(AdvertDetail advertDetail) {
			this.advertDetail = advertDetail;
			return this;
		}
		public AdvertBuilder setPostedDate(Date postedDate) {
			this.postedDate = postedDate;
			return this;
		}
		public AdvertBuilder setPublishedDate(Date publishedDate) {
			this.publishedDate = publishedDate;
			return this;
		}
		public AdvertBuilder setApprovedDate(Date approvedDate) {
			this.approvedDate = approvedDate;
			return this;
		}
		public AdvertBuilder setSubmittedDate(Date submittedDate) {
			this.submittedDate = submittedDate;
			return this;
		}
		public AdvertBuilder setRejectedDate(Date rejectedDate) {
			this.rejectedDate = rejectedDate;
			return this;
		}
		public AdvertBuilder setAdvertStatus(AdvertStatus advertStatus) {
			this.advertStatus = advertStatus;
			return this;
		}
		public Advert build() {
			return new Advert(this);
		}
	}
}
