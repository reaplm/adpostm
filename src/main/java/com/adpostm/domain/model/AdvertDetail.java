package com.adpostm.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//import org.hibernate.search.annotations.Field;
//import org.hibernate.search.annotations.Indexed;

@Entity

@Table(name="advert_detail")
public class AdvertDetail {
	@Id
	@GeneratedValue
	@Column(name="pk_detail_id")
	private Long detailId;
	private String title;
	
	private String body;
	@Column(name="contact_email")
	private String contactEmail;
	@Column(name="contact_phone")
	private String contactPhone;
	@Column(name="location")
	private String location;
	@OneToOne
	@JoinColumn(name="fk_advert_id")
	private Advert advert;

	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getBody() {
		return this.body;
	}
	public void setContactEmaily(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactEmail() {
		return this.contactEmail;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactPhone() {
		return this.contactPhone;
	}
	public void setLocation(String location) {
		this.location= location;
	}
	public String getLocation() {
		return this.location;
	}
	public void setAdvert(Advert advert) {
		this.advert = advert;
	}
	public Advert getAdvert() {
		return this.advert;
	}
	public AdvertDetail() {}
	public AdvertDetail(AdvertDetailBuilder advertDetailBuilder) {
		this.detailId = advertDetailBuilder.detailId;
		this.title = advertDetailBuilder.title;
		this.body = advertDetailBuilder.body;
		this.contactEmail = advertDetailBuilder.contactEmail;
		this.contactPhone = advertDetailBuilder.contactPhone;
		this.location = advertDetailBuilder.location;
		this.advert = advertDetailBuilder.advert;
	}
	public static class AdvertDetailBuilder{
		private Long detailId;
		private String title;
		private String body;
		private String contactEmail;
		private String contactPhone;
		private String location;
		private Advert advert;
		
		public AdvertDetailBuilder setTitle(String title) {
			this.title = title;
			return this;
		}
		public AdvertDetailBuilder setBody(String body) {
			this.body = body;
			return this;
		}
		public AdvertDetailBuilder setContactEmaily(String contactEmail) {
			this.contactEmail = contactEmail;
			return this;
		}
		public AdvertDetailBuilder setContactPhone(String contactPhone) {
			this.contactPhone = contactPhone;
			return this;
		}
		public AdvertDetailBuilder setLocation(String location) {
			this.location= location;
			return this;
		}
		public AdvertDetailBuilder setAdvert(Advert advert) {
			this.advert = advert;
			return this;
		}
		public AdvertDetail build() {
			return new AdvertDetail(this);
		}
	}
}
