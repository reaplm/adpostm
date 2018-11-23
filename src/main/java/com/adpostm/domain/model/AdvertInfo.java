package com.adpostm.domain.model;

import java.util.List;

public class AdvertInfo {
	private Long advertId;
	private Long menuId;
	private Long subMenuId;
	private String location;
	private String subject;
	private String body;
	private String contactNo;
	private String contactEmail;
	
	//Advert Pictures
	private List<String> imageUuid;
	private List<String> imageCdnUrl;
	private List<Long> imageSize;
	private List<String> imageName;
	
	//Group Information
	private String groupUuid;
	private String groupCdnUrl;
	private int groupCount;
	private Long groupSize;
	
	public void setAdvertId(Long advertId) {
		this.advertId = advertId;
	}
	public Long getAdvertId() {
		return this.advertId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public Long getMenuId() {
		return this.menuId;
	}
	public void setSubMenuId(Long subMenuId) {
		this.subMenuId = subMenuId;
	}
	public Long getSubMenuId() {
		return this.subMenuId;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocation() {
		return this.location;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubject() {
		return this.subject;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getBody() {
		return this.body;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getContactNo() {
		return this.contactNo;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactEmail() {
		return this.contactEmail;
	}
	public void setImageUuid(List<String> imageUuid) {
		this.imageUuid = imageUuid;
	}
	public List<String> getImageUuid() {
		return this.imageUuid;
	}
	public void setImageCdnUrl(List<String> imageCdnUrl) {
		this.imageCdnUrl = imageCdnUrl;
	}
	public List<String> getImageCdnUrl() {
		return this.imageCdnUrl;
	}
	public void setImageSize(List<Long> imageSize) {
		this.imageSize = imageSize;
	}
	public List<Long> getImageSize() {
		return this.imageSize;
	}
	public void setImageName(List<String> imageName) {
		this.imageName = imageName;
	}
	public List<String> getImageName() {
		return this.imageName;
	}
	public void setGroupUuid(String groupUuid) {
		this.groupUuid = groupUuid;
	}
	public String getGroupUuid() {
		return this.groupUuid;
	}
	public void setGroupCdnUrl(String groupCdnUrl) {
		this.groupCdnUrl = groupCdnUrl;
	}
	public String getGroupCdnUrl() {
		return this.groupCdnUrl;
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
}
