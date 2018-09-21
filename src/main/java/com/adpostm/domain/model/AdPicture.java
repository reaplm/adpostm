package com.adpostm.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="ad_picture")
public class AdPicture {
	@Id
	@GeneratedValue
	@Column(name="ad_pic_id")
	private int adPictureId;
	//individual file information
	@Column(name="cdn_url")
	private String cdnUrl;
	private String uuid;
	private String name;
	private Long size;
	
	
	@ManyToOne
	@JoinColumn(name="fk_ad_detail")
	@JsonBackReference
	private AdvertDetail advertDetail;

	public void setAdPictureId(int adPictureId) {
		this.adPictureId = adPictureId;
	}
	public int getAdPictureId() {
		return this.adPictureId;
	}
	public void setAdvertDetail(AdvertDetail advertDetail){
		this.advertDetail = advertDetail;
	}
	public AdvertDetail getAdvertDetail(){
		return this.advertDetail;
	}
	public void setCdnUrl(String cdnUrl) {
		this.cdnUrl = cdnUrl;
	}
	public String getCdnUrl() {
		return this.cdnUrl;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUui() {
		return this.uuid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Long getSize() {
		return this.size;
	}
	
	public AdPicture() {}
	public AdPicture(AdPictureBuilder adPictureBuilder) {
		this.adPictureId = adPictureBuilder.adPictureId;
		this.cdnUrl = adPictureBuilder.cdnUrl;
		this.uuid = adPictureBuilder.uuid;
		this.name = adPictureBuilder.name;
		this.size = adPictureBuilder.size;
		this.advertDetail = adPictureBuilder.advertDetail;
	}
	public static class AdPictureBuilder{
		private int adPictureId;
		private String cdnUrl;
		private String uuid;
		private String name;
		private Long size;
		private AdvertDetail advertDetail;

		public AdPictureBuilder setAdPictureId(int adPictureId) {
			this.adPictureId = adPictureId;
			return this;
		}
		public AdPictureBuilder setAdvertDetail(AdvertDetail advertDetail){
			this.advertDetail = advertDetail;
			return this;
		}
		public AdPictureBuilder setCdnUrl(String cdnUrl) {
			this.cdnUrl = cdnUrl;
			return this;
		}
		public AdPictureBuilder setUuid(String uuid) {
			this.uuid = uuid;
			return this;
		}
		public AdPictureBuilder setName(String name) {
			this.name = name;
			return this;
		}
		public AdPictureBuilder setSize(Long size) {
			this.size = size;
			return this;
		}
		public AdPicture build() {
			return new AdPicture(this);
		}
	}
}
