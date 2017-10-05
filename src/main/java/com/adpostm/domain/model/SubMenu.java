package com.adpostm.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="sub_menu")
public class SubMenu implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="pk_menu_id")
	private int menuId;
	@Column(name="menu_name")
	private String menuName;
	@Column(name="menu_desc")
	private String menuDesc;
	private String url;
	private String label;
	
	@ManyToOne
	@JoinColumn(name="fk_menu_id", nullable=false)
	@JsonBackReference
	private Menu menu;
	
	public int getMenuId(){
		return this.menuId;
	}
	public void setMenuId(int subMenuId){
		this.menuId = subMenuId;
	}
	public String getMenuName(){
		return this.menuName;
	}
	public void setMenuName(String subMenuName){
		this.menuName = subMenuName;
	}
	public String getMenuDesc(){
		return this.menuDesc;
	}
	public void setMenuDesc(String subMenuDesc){
		this.menuDesc = subMenuDesc;
	}
	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getLabel(){
		return this.label;
	}
	public void setLabel(String label){
		this.label = label;
	}
	public Menu getMenu(){
		return this.menu;
	}
	public void setMenu(Menu menu){
		this.menu = menu;
	}
}
