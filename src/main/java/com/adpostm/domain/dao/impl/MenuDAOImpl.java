package com.adpostm.domain.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.adpostm.domain.dao.MenuDao;
import com.adpostm.domain.enumerated.MenuType;
import com.adpostm.domain.model.Menu;

public class MenuDaoImpl extends GenericDaoImpl<Menu, Long> implements MenuDao{

	private EntityManager em;

	public MenuDaoImpl() {
		super(Menu.class);
		em = super.getEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Menu> getMenusWithoutSub() {

		return em.createQuery("from Menu"
				+ " where menu is null")
				.getResultList();
	
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Menu> getMenuByType(String type) {
		MenuType menuType = MenuType.valueOf(type.toUpperCase());
		
		return (List<Menu>)em.createQuery("from Menu where "
								+ "menuType = :menuType")
								.setParameter("menuType", menuType)
								.getResultList();

	}

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Menu> getMenuList(){		
		return (List<Menu>)em
				.createQuery("from Menu")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getSubMenus(Long parentId) {
		return (List<Menu>)em
				.createQuery("from Menu where menu.menuId = :parentId")
				.setParameter("parentId", parentId)
				.getResultList();
	}
}
