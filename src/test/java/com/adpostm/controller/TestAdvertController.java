package com.adpostm.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.adpostm.domain.enumerated.AdvertStatus;
import com.adpostm.domain.model.Advert;
import com.adpostm.domain.model.AdvertInfo;
import com.adpostm.domain.model.AppUser;
import com.adpostm.domain.model.Menu;
import com.adpostm.service.AdvertService;
import com.adpostm.service.MenuService;
import com.adpostm.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-security.xml"})
public class TestAdvertController {
	private MockMvc mockMvc;
	
	@Mock
	private AdvertService advertService;
	@Mock
	private MenuService menuService;
	@Mock
	private UserService userService;
	@Mock
	Authentication authentication;
	@InjectMocks
	private AdvertController advertController;
	
	@Before
	public void setUp() throws Exception{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".jsp");
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(advertController)
						.setViewResolvers(viewResolver)
						.build();
		
		setAuthentication();
		
	}
	@Test
	public void testCreateAdvert() throws Exception {
		Menu menu = new Menu.MenuBuilder()
						.setMenuId(1L)
						.setMenuName("menu1")
						.build();
		AppUser user = new AppUser.AppUserBuilder()
						.setAppUserId(1L)
						.setEmail("user@email")
						.build();
		Advert advert = new Advert.AdvertBuilder()
				.setMenu(menu)
				.setAppUser(user)
				.setAdvertStatus(AdvertStatus.SUBMITTED)
				.setSubmittedDate(new Date())
				.build();
		
		Mockito.when(menuService.read(1L)).thenReturn(menu);
		Mockito.when(authentication.getPrincipal()).thenReturn(new User("user@email", "user1",
				true, true, true, true, new ArrayList<GrantedAuthority>()));
		Mockito.when(userService.getUserByUsername(Mockito.anyString()))
			.thenReturn(user);
		Mockito.when(advertService.create(Mockito.any(Advert.class)))
			.thenReturn(advert);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/advert/add")
							.param("menuId", "1")
							.param("subMenuId", "2")
							.param("location", "gaborone")
							.param("subject", "car for sale")
							.param("body", "car for sale in gaborone")
							.param("contactEmail", "admin@email")
							.param("contactNo", "71406569")
							.param("imageCdnUrl", "cdnUrl1", "cdnUrl2")
							.param("imageName", "image1", "image2")
							.param("imageUuid", "uuid1", "uuid2")
							.param("imageSize", "1024","800")
							.param("groupUuid", "groupUuid")
							.param("groupSize", "1824")
							.param("groupCdnUrl", "groupCdnUrl")
							.param("groupCount", "2"))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
							.andExpect(MockMvcResultMatchers.content().string("success"))
							.andDo(MockMvcResultHandlers.print())
							.andReturn();
			
							
		
	}
	@Test
	public void testGetAdvertsIf() throws Exception {
		List<Advert> advertList = new ArrayList<Advert>();
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(1L)
						.build()
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(2L)
						.build()
				);
		
		
		Mockito.when(advertService.findAll(Advert.class,true,new String[]{"menu"}))
			.thenReturn(advertList);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/search")
								.param("search", "")
								.param("s-category", "-1"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("adverts"))
								.andExpect(MockMvcResultMatchers.model().attribute("advertList", hasSize(2)))
								.andExpect(MockMvcResultMatchers.model().attribute("advertList", advertList))
								.andDo(MockMvcResultHandlers.print())
								.andReturn();

	}
	@Test
	public void testGetAdvertsElse() throws Exception {
		List<Advert> advertList = new ArrayList<Advert>();
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(1L)
						.build()
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(2L)
						.build()
				);
		
		Mockito.when(advertService.search(Mockito.anyString(), Mockito.anyLong()))
			.thenReturn(advertList);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/search")
								.param("search", "vehicles")
								.param("s-category", "1"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("adverts"))
								.andExpect(MockMvcResultMatchers.model().attribute("advertList", hasSize(2)))
								.andExpect(MockMvcResultMatchers.model().attribute("advertList", advertList))
								.andDo(MockMvcResultHandlers.print())
								.andReturn();

	}
	@Test
	public void testGetAdvertsInterruptedExceptionThrown() throws Exception {
		List<Advert> advertList = new ArrayList<Advert>();
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(1L)
						.build()
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(2L)
						.build()
				);
		

		Mockito.doThrow(Mockito.mock(InterruptedException.class))
			.when(advertService).search(Mockito.anyString(), Mockito.anyLong());
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/search")
								.param("search", "all")
								.param("s-category", "1"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("adverts"))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("searchError", "Error occured during search"))
								.andDo(MockMvcResultHandlers.print())
								.andReturn();

	}
	@Test
	public void testGetAdvertsNumberFormatExceptionThrown() throws Exception {
		List<Advert> advertList = new ArrayList<Advert>();
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(1L)
						.build()
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(2L)
						.build()
				);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/search")
								.param("search", "all")
								.param("s-category", "ab"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("adverts"))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("searchError", "NumberFormatException caught during search"))
								.andDo(MockMvcResultHandlers.print())
								.andReturn();

	}
	@Test
	public void  testUpdateStatus() throws Exception {		
		Advert advert = new Advert.AdvertBuilder()
									.setAdvertId(1L)
									.setAdvertStatus(AdvertStatus.SUBMITTED)
									.build();
			
		Mockito.when(advertService.read(Mockito.any())).thenReturn(advert);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/edit/status")
								.param("checked", "true")
								.param("id", "1"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.content()
										.contentType("text/plain;charset=ISO-8859-1"))
								.andExpect(MockMvcResultMatchers.content().string("true"))
								.andDo(MockMvcResultHandlers.print())
								.andReturn();
	}
	private void setAuthentication() {
		authentication.setAuthenticated(true);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	
}
