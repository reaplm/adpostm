package com.adpostm.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;

import com.adpostm.domain.model.AdPicture.AdPictureBuilder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="advert_detail")
public class AdvertDetail {
	@Id
	@GeneratedValue
	@Column(name="pk_detail_id")
	private Long detailId;
	
	//@Field //defaults: index=Index.YES, analyze=Analyze.YES and store=Store.NO 
	private String title;
	
	@Field
	private String body;
	
	@Column(name="contact_email")
	private String contactEmail;
	
	@Column(name="contact_phone")
	private String contactPhone;
	
	@Column(name="location")
	private String location;
	
	//@ContainedIn //To make sure Lucene document is updated when its advert changes
	@OneToOne
	@PrimaryKeyJoinColumn
	@JsonBackReference
	private Advert advert;
	
	@OneToMany(mappedBy="advertDetail", cascade= {CascadeType.ALL})
	 @JsonManagedReference
	private List<AdPicture> adPictures = new ArrayList<AdPicture>();
	
	//image group information
	@Column(name="group_cdn")
	private String groupCdnUrl;
	@Column(name="group_uuid")
	private String groupUuid;
	@Column(name="group_count")
	private int groupCount;
	@Column(name="group_size")
	private Long groupSize;

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
	public void addAdPicture(AdPicture adPicture) {
		adPictures.add(adPicture);
		adPicture.setAdvertDetail(this);
	}
	public void removeAdPicture(AdPicture adPicture) {
		adPictures.remove(adPicture);
		adPicture.setAdvertDetail(null);
	}
	public void setAdPicture(List<AdPicture> adPicture) {
		this.adPictures = adPicture;
	}
	public List<AdPicture> getAdPicture() {
		return this.adPictures;
	}
	public void setGroupCdnUrl(String groupCdnUrl) {
		this.groupCdnUrl = groupCdnUrl;
	}
	public String getGroupCdnUrl() {
		return this.groupCdnUrl;
	}
	public void setGroupUuid(String groupUuid) {
		this.groupUuid = groupUuid;
	}
	public String getGroupUuid() {
		return this.groupUuid;
	}
	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}
	public int getGroupCount() {
		return this.groupCount;
	}
	public void setGroupSize(Long groupSize) {
		this.groupSize = groupSize;
	}
	public Long getGroupSize() {
		return this.groupSize;
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
		this.adPictures = advertDetailBuilder.adPictures;
		this.groupCdnUrl = advertDetailBuilder.groupCdnUrl;
		this.groupUuid = advertDetailBuilder.groupUuid;
		this.groupCount = advertDetailBuilder.groupCount;
		this.groupSize = advertDetailBuilder.groupSize;
	}
	public static class AdvertDetailBuilder{
		private Long detailId;
		private String title;
		private String body;
		private String contactEmail;
		private String contactPhone;
		private String location;
		private Advert advert;
		private List<AdPicture> adPictures = new ArrayList<AdPicture>();
		private String groupCdnUrl;
		private String groupUuid;
		private int groupCount;
		private Long groupSize;
		
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
		public AdvertDetailBuilder setAdPicture(List<AdPicture> adPicture) {
			this.adPictures = adPicture;
			return this;
		}
		public AdvertDetailBuilder setGroupCdnUrl(String groupCdnUrl) {
			this.groupCdnUrl = groupCdnUrl;
			return this;
		}
		public AdvertDetailBuilder setGroupUuid(String groupUuid) {
			this.groupUuid = groupUuid;
			return this;
		}
		public AdvertDetailBuilder setGroupCount(int groupCount) {
			this.groupCount = groupCount;
			return this;
		}
		public AdvertDetailBuilder setGroupSize(Long groupSize) {
			this.groupSize = groupSize;
			return this;
		}
		public AdvertDetail build() {
			return new AdvertDetail(this);
		}
	}
}
