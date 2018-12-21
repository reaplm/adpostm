package com.adpostm.domain.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
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
	public List<Menu> getSubMenus(Long parentId) {
		return (List<Menu>)em
				.createQuery("from Menu where menu.menuId = :parentId")
				.setParameter("parentId", parentId)
				.getResultList();
	}

	@Transactional
	@Override
	public boolean checkMenuNameValid(String menuName, Long parentId) {
		Long count = 0L;
		try {
			if(parentId > 0) {
				count = (Long)em.createQuery("select count(*) from Menu where "
							+ "menu.menuId = :parentId and menuName = :menuName")
						.setParameter("parentId", parentId)
						.setParameter("menuName", menuName)
						.getSingleResult();
				
			}
			else {
				count = (Long)em.createQuery("select count(*) from Menu where "
							+ "menuName = :menuName")
						.setParameter("menuName", menuName)
						.getSingleResult();
			}
		}
		catch(NonUniqueResultException ex) {
			ex.printStackTrace();
		}
		if(count == 0) {return true;}
		else {return false;}
	}
	@Override
	public List<Menu> findAllByMenuTypeIn(List<MenuType> type) {
		
		@SuppressWarnings("unchecked")
		List<Menu> menus = em.createQuery("from Menu where menuType in (:type)")
							.setParameter("type", type)
							.getResultList();

	
		return menus;
	}
}
