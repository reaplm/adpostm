package com.adpostm.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.adpostm.domain.enumerated.MenuType;


@Entity
@Table(name="menu")
@DynamicUpdate(value=true)
public class Menu {
	@Id
	@GeneratedValue
	@Column(name="pk_menu_id")
	private int menuId;
	@Column(name="menu_name")
	private String menuName;
	@Column(name="menu_desc")
	private String menuDesc;
	private String url;
	private String icon;
	
	@Enumerated(EnumType.STRING)
	@Column(name="menu_type")
	private MenuType menuType;
	
	@OneToMany(mappedBy="menu", 
			cascade=CascadeType.REMOVE)
	private List<SubMenu> subMenu;
	
	public int getMenuId(){
		return this.menuId;
	}
	public void setMenuId(int menuId){
		this.menuId = menuId;
	}
	public String getMenuName(){
		return this.menuName;
	}
	public void setMenuName(String menuName){
		this.menuName = menuName;
	}
	public String getMenuDesc(){
		return this.menuDesc;
	}
	public void setMenuDesc(String menuDesc){
		this.menuDesc = menuDesc;
	}
	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getIcon(){
		return this.icon;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
	public List<SubMenu> getSubMenu(){
		return this.subMenu;
	}
	public void setSubMenu(List<SubMenu> subMenu){
		this.subMenu = subMenu;
	}
	public MenuType getMenuType() {
		return menuType;
	}
	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}
}