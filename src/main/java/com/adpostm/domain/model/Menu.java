package com.adpostm.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;

import com.adpostm.domain.enumerated.MenuStatus;
import com.adpostm.domain.enumerated.MenuType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="menu")
public class Menu {
	@Id
	@GeneratedValue
	@Column(name="pk_menu_id")
	private Long menuId;
	
	@Field
	@Column(name="menu_name")
	private String menuName;
	
	@Column(name="menu_desc")
	private String menuDesc;
	private String label;
	private String url;
	private String icon;
	
	@Enumerated(EnumType.STRING)
	@Column(name="menu_type")
	private MenuType menuType;
	
	@Column(name="menu_status")
	private int menuStatus = 1;
	@Column(name="admin_menu")
	private int adminMenu = 1;
	
	@OneToMany(mappedBy="menu", 
			cascade=CascadeType.REMOVE)
	private List<Menu> subMenu;
	
	@ManyToOne
	@JoinColumn(name="fk_menu_id")
	@JsonBackReference
	private Menu menu;
	
	@OneToMany(mappedBy="menu")
	@JsonManagedReference
	private List<Advert> adverts;

	
	public Long getMenuId(){
		return this.menuId;
	}
	public void setMenuId(Long menuId){
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
	public String getLabel(){
		return this.label;
	}
	public void setLabel(String label){
		this.label = label;
	}
	public List<Menu> getSubMenu(){
		System.out.println(this.menuName+": " +this.subMenu);
		return this.subMenu;	
	}
	public void setSubMenu(List<Menu> subMenu){
		this.subMenu = subMenu;
	}
	public Menu getMenu(){
		return this.menu;	
	}
	public void setMenu(Menu menu){
		this.menu = menu;
	}
	public MenuType getMenuType() {
		return this.menuType;
	}
	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}
	public int getAdminMenu(){
		return this.adminMenu;
	}
	public void setAdminMenu(int adminMenu){
		this.adminMenu = adminMenu;
	}
	public int getMenuStatus(){
		return this.menuStatus;
	}
	public void setMenuStatus(int menuStatus){
		this.menuStatus = menuStatus;
	}
	public List<Advert> getAdverts() {
		return this.adverts;
	}
	public void setAdverts(List<Advert> adverts) {
		this.adverts = adverts;
	}
	public Menu() {}
	private Menu(MenuBuilder menuBuilder) {
		 this.menuId = menuBuilder.menuId;
		 this.menuName = menuBuilder.menuName;
		 this.menuDesc = menuBuilder.menuDesc;
		 this.label = menuBuilder.label;
		 this.url = menuBuilder.url;
		 this.icon = menuBuilder.icon;
		 this.menuType = menuBuilder.menuType;
		 this.subMenu = menuBuilder.subMenu;
		 this.menu = menuBuilder.menu;
		 this.adverts = menuBuilder.adverts;
		 this.adminMenu = menuBuilder.adminMenu;
		 this.menuStatus = menuBuilder.menuStatus;
		 
	 }
	
	//Builder Class
	public static class MenuBuilder{
		private Long menuId;
		private String menuName;
		private String menuDesc;
		private String label;
		private String url;
		private String icon;
		private int adminMenu = 1;
		private MenuType menuType;
		private int menuStatus;
		private List<Menu> subMenu;
		private Menu menu;
		private List<Advert> adverts;
		
		public MenuBuilder setMenuId(Long menuId){
			this.menuId = menuId;
			return this;
		}
		public MenuBuilder setMenuName(String menuName){
			this.menuName = menuName;
			return this;
		}
		public MenuBuilder setMenuDesc(String menuDesc){
			this.menuDesc = menuDesc;
			return this;
		}
		public MenuBuilder setUrl(String url){
			this.url = url;
			return this;
		}
		public MenuBuilder setIcon(String icon){
			this.icon = icon;
			return this;
		}
		public MenuBuilder setLabel(String label){
			this.label = label;
			return this;
		}
		public MenuBuilder setSubMenu(List<Menu> subMenu){
			this.subMenu = subMenu;
			return this;
		}
		public MenuBuilder setMenu(Menu menu){
			this.menu = menu;
			return this;
		}
		public MenuBuilder setMenuType(MenuType menuType) {
			this.menuType = menuType;
			return this;
		}
		public MenuBuilder setMenuStatus(int menuStatus) {
			this.menuStatus = menuStatus;
			return this;
		}
		public MenuBuilder setAdminMenu(int adminMenu){
			this.adminMenu = adminMenu;
			return this;
		}
		public MenuBuilder setAdverts(List<Advert> adverts) {
			this.adverts = adverts;
			return this;
		}
		public Menu build() {
			return new Menu(this);
		}
	}
}
