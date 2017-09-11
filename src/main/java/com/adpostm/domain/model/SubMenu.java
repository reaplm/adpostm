package com.adpostm.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sub_menu")
public class SubMenu {
	@Id
	@GeneratedValue
	@Column(name="pk_sub_menu_id")
	private int subMenuId;
	@Column(name="sub_menu_name")
	private String subMenuName;
	@Column(name="sub_menu_desc")
	private String subMenuDesc;
	private String url;
	
	@ManyToOne
	@JoinColumn(name="fk_menu_id", nullable=false)
	private Menu menu;
	
	public int getSubMenuId(){
		return this.subMenuId;
	}
	public void setSubMenuId(int subMenuId){
		this.subMenuId = subMenuId;
	}
	public String getSubMenuName(){
		return this.subMenuName;
	}
	public void setSubMenuName(String subMenuName){
		this.subMenuName = subMenuName;
	}
	public String getSubMenuDesc(){
		return this.subMenuDesc;
	}
	public void setSubMenuDesc(String subMenuDesc){
		this.subMenuDesc = subMenuDesc;
	}
	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public Menu getMenu(){
		return this.menu;
	}
	public void setMenu(Menu menu){
		this.menu = menu;
	}
}
