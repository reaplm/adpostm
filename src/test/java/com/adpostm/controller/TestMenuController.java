package com.adpostm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.adpostm.domain.enumerated.MenuType;
import com.adpostm.domain.model.Menu;
import com.adpostm.service.MenuService;
import com.adpostm.controller.MenuController;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-security.xml"})
public class TestMenuController{
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private MenuService menuService;
	@Mock
	private WebApplicationContext weApp;

	private MockMvc mockMvc;
	
	@InjectMocks
	private MenuController menuController;
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		//initialize the mockMvc object in the @Before annotated method, 
		//so that we don't need to initialize it inside every test.
		//this.mockMvc = MockMvcBuilders.webAppContextSetup(this.weApp).build();
		mockMvc = MockMvcBuilders.standaloneSetup(menuController).build();
	}
	@Test
	public void TestGetMenuDetailWithStringParam() throws Exception {
		List<Menu> menus = new ArrayList<Menu>();
		menus.add(new Menu.MenuBuilder()
					.setMenuId(1L)
					.setMenuName("menu1")
					.setMenuType(MenuType.HOME)
					.build());
		menus.add(new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("menu2")
				.setMenuType(MenuType.SIDEBAR)
				.build());
		
		when(menuService.getMenuList()).thenReturn(menus);
		
		MvcResult expected = mockMvc.perform(MockMvcRequestBuilders
							.get("/menus")
							.param("type", "home"))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
							.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
							.andExpect(MockMvcResultMatchers.jsonPath("$.[0].menuId").value(1))
							.andExpect(MockMvcResultMatchers.jsonPath("$.[0].menuName").value("menu1"))
							.andExpect(MockMvcResultMatchers.jsonPath("$.[0].menuType").value("HOME"))
							.andReturn();
							
		assertNotNull(expected);

	}
	@Test
	public void testGetMenuDetailWithNullStringParam() throws Exception {
		List<Menu> menus = new ArrayList<Menu>();
		menus.add(new Menu.MenuBuilder()
						.setMenuId(1L)
						.setMenuName("menu1")
						.setMenuType(MenuType.HOME)
						.build());
		menus.add(new Menu.MenuBuilder()
				.setMenuId(2L)
				.setMenuName("menu2")
				.setMenuType(MenuType.SIDEBAR)
				.build());
		when(menuService.getMenuList()).thenReturn(menus);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.get("/menus"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].menuId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].menuName").value("menu1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].menuType").value("HOME"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].menuId").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].menuName").value("menu2"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].menuType").value("SIDEBAR"))
				.andReturn();
				
		assertNotNull(result);
		
	}
	@Test
	public void testGetMenuDetailWithIntParam() throws Exception {
		List<Menu> menus = new ArrayList<Menu>();
		menus.add(new Menu.MenuBuilder()
						.setMenuId(1L)
						.setMenuName("menu1")
						.setMenuType(MenuType.HOME)
						.build());
		menus.add(new Menu.MenuBuilder()
				.setMenuId(2L)
				.setMenuName("menu2")
				.setMenuType(MenuType.SIDEBAR)
				.build());
		
		Long menuId = 1L;
		when(menuService.read(menuId)).thenReturn(menus.get(0));
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.get("/menus/detail")
				.param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.menuId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.menuName").value("menu1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.menuType").value("HOME"))
				//.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	@Test
	public void testSubmitEditMenu() throws Exception {
		Menu menu = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("menu1")
				.setMenuStatus(1)
				.setAdminMenu(1)
				.build();

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/menus/update")
				.param("menuId", "1")
				.param("menuName", "menu1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
				.andExpect(MockMvcResultMatchers.content().string("success"))
				.andReturn();
		
		//String content = result.getResponse().getContentAsString();
		//Mockito.verify(menuService, Mockito.times(1)).update(Mockito.refEq(menu1));
				
		assertNotNull(result);
	}
	@Test
	public void testSubmitAddMenu() throws Exception {
		Menu menu = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("parentMenu")
				.build();
		
		when(menuService.create(Mockito.any(Menu.class))).thenReturn(menu);
		when(menuService.read(Mockito.anyLong())).thenReturn(menu);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/menus/add")
									.param("parentId",	"1")
									.param("menuName",	"menu1"))
									.andExpect(MockMvcResultMatchers.status().isOk())
									.andExpect(MockMvcResultMatchers.content().string("success"))
									.andReturn();
	}
	/**
	 * Test if submitAddMenu catches NumberFormatException when invalid parentId is supplied
	 * @throws Exception
	 */
	@Test
	public void testNumberFormatExceptionCaught() throws Exception {
		Menu menu = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("parentMenu")
				.build();
		
		//when(menuService.create(Mockito.any(Menu.class))).thenReturn(1L);
		when(menuService.read(Mockito.anyLong())).thenReturn(menu);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/menus/add")
									.param("parentId",	"error")
									.param("menuName",	"menu1"))
									.andExpect(MockMvcResultMatchers.status().isOk())
									.andExpect(MockMvcResultMatchers.content().string("Parent id is invalid"))
									.andDo(MockMvcResultHandlers.print())
									.andReturn();
	}
	@Test
	public void testUpdateMenuStatusIf() throws Exception {
		Menu menu = new Menu.MenuBuilder()
						.setMenuId(1L)
						.setMenuName("menu1")
						.build();
		
		Mockito.when(menuService.read(1L)).thenReturn(menu);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/menu/edit/status")
									.param("id", "1")
									.param("checked", "true"))
									.andExpect(MockMvcResultMatchers.status().isOk())
									.andExpect(MockMvcResultMatchers.content()
											.contentType("text/plain;charset=ISO-8859-1"))
									.andExpect(MockMvcResultMatchers.content().string("success"))
									.andDo(MockMvcResultHandlers.print())
									.andReturn();
	}
	@Test
	public void testUpdateMenuStatusElse() throws Exception {
		List<Menu> subMenus = new ArrayList<Menu>();
		subMenus.add(
				new Menu.MenuBuilder()
					.setMenuId(2L)
					.setMenuName("submenu1")
					.setMenuType(MenuType.SUBMENU)
					.build()
				);
		subMenus.add(
				new Menu.MenuBuilder()
					.setMenuId(3L)
					.setMenuName("submenu2")
					.setMenuType(MenuType.SUBMENU)
					.build()
				);
		
		Menu menu = new Menu.MenuBuilder()
						.setMenuId(1L)
						.setMenuName("menu1")
						.setSubMenu(subMenus)
						.build();
		
		Mockito.when(menuService.read(1L)).thenReturn(menu);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/menu/edit/status")
									.param("id", "1")
									.param("checked", "false"))
									.andExpect(MockMvcResultMatchers.status().isOk())
									.andExpect(MockMvcResultMatchers.content()
											.contentType("text/plain;charset=ISO-8859-1"))
									.andExpect(MockMvcResultMatchers.content().string("success"))
									.andDo(MockMvcResultHandlers.print())
									.andReturn();
		
	}
}
