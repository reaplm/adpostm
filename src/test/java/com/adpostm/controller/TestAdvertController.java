package com.adpostm.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.equalTo;
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
import com.adpostm.domain.model.AdPicture;
import com.adpostm.domain.model.Advert;
import com.adpostm.domain.model.AdvertDetail;
import com.adpostm.domain.model.AdvertInfo;
import com.adpostm.domain.model.AppUser;
import com.adpostm.domain.model.Menu;
import com.adpostm.service.AdvertService;
import com.adpostm.service.MenuService;
import com.adpostm.service.UserService;

import net.minidev.json.parser.JSONParser;

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
	/**
	 * Testing if(search.isEmpty() && menuId == -1)
	 * @throws Exception
	 */
	@Test
	public void testGetAdvertsFirstIf() throws Exception {
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
	/**
	 * Testing else if(!search.isEmpty()  && menuId == -1)
	 * @throws Exception
	 */
	@Test
	public void testGetAdvertsThirdIf() throws Exception {
		List<Advert> advertList = new ArrayList<Advert>();
		AdvertDetail advertDetail1 = new AdvertDetail.AdvertDetailBuilder()
										.setTitle("Car for Sale")
										.setBody("Toyota corolla for sale in Gaborone")
										.build();
		AdvertDetail advertDetail2 = new AdvertDetail.AdvertDetailBuilder()
				.setTitle("Toyota Car for Sale")
				.setBody("Toyota corolla for sale in Mogoditshane")
				.build();
		AdvertDetail advertDetail3 = new AdvertDetail.AdvertDetailBuilder()
				.setTitle("House for rent")
				.setBody("Two bedroom house for rent in Gaborone")
				.build();
		
		Menu menu = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("vehicles")
				.build();
		
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(1L)
						.setAdvertDetail(advertDetail1)
						.setMenu(menu)
						.build()
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(2L)
						.setAdvertDetail(advertDetail2)
						.setMenu(menu)
						.build()
						
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(2L)
						.setAdvertDetail(advertDetail3)
						.build()
				);
		
		
		Mockito.when(advertService.search("car"))
			.thenReturn(advertList.stream()
					.filter(s -> s.getAdvertDetail().getTitle()
							.contains("Car"))
					.collect(Collectors.toList())
				);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/search")
								.param("search", "car")
								.param("s-category", "-1"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("adverts"))
								.andExpect(MockMvcResultMatchers.model().attribute("advertList", hasSize(2)))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("advertList", advertList.subList(0, 2)))
								.andDo(MockMvcResultHandlers.print())
								.andReturn();

	}
	/**
	 * Testing else if(!search.isEmpty() && menuId > 0)
	 * @throws Exception
	 */
	@Test
	public void testGetAdvertsFourthIf() throws Exception {
		List<Advert> advertList = new ArrayList<Advert>();
		AdvertDetail advertDetail1 = new AdvertDetail.AdvertDetailBuilder()
										.setTitle("Car for Sale")
										.setBody("Toyota for sale in Gaborone")
										.build();
		AdvertDetail advertDetail2 = new AdvertDetail.AdvertDetailBuilder()
				.setTitle("Toyota Car for Sale")
				.setBody("Toyota corolla for sale in Mogoditshane")
				.build();
		AdvertDetail advertDetail3 = new AdvertDetail.AdvertDetailBuilder()
				.setTitle("House for rent")
				.setBody("Two bedroom house for rent in Gaborone")
				.build();
		
		Menu menu1 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("vehicles")
				.build();
		Menu menu2 = new Menu.MenuBuilder()
				.setMenuId(2L)
				.setMenuName("property")
				.build();
		
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(1L)
						.setAdvertDetail(advertDetail1)
						.setMenu(menu1)
						.build()
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(2L)
						.setAdvertDetail(advertDetail2)
						.setMenu(menu1)
						.build()
						
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(2L)
						.setAdvertDetail(advertDetail3)
						.setMenu(menu2)
						.build()
				);
		
		
		Mockito.when(advertService.search("toyota",1L))
			.thenReturn(advertList.stream()
					.filter(s -> s.getMenu()
							.getMenuId()
							.equals(1L))
					.filter(s -> s.getAdvertDetail().getBody()
							.toLowerCase()
							.contains("corolla"))
					.collect(Collectors.toList())
				);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/search")
								.param("search", "toyota")
								.param("s-category", "1"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("adverts"))
								.andExpect(MockMvcResultMatchers.model().attribute("advertList", hasSize(1)))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("advertList", advertList.subList(1, 2)))
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
	@Test
	public void testEditAdvert() throws Exception {
		List<AdPicture> adPictures = new ArrayList<AdPicture>();
		adPictures.add(
				new AdPicture.AdPictureBuilder()
						.setAdPictureId(1)
						.setCdnUrl("ImageCdnUrl1")
						.setName("ImageName1")
						.setUuid("ImageUuid1")
						.setSize(1048L)
						.build()
				);
		adPictures.add(
				new AdPicture.AdPictureBuilder()
						.setAdPictureId(2)
						.setCdnUrl("ImageCdnUrl2")
						.setName("ImageName2")
						.setUuid("ImageUuid2")
						.setSize(336L)
						.build()
				);
		Menu menu = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("menu1")
				.build();
		Menu submenu = new Menu.MenuBuilder()
				.setMenuId(10L)
				.setMenuName("submenu1")
				.build();
		AdvertDetail advertDetail = new AdvertDetail
						.AdvertDetailBuilder()
						.setTitle("car for sale")
						.setBody("There is a car for sale")
						.setContactEmail("admin@email")
						.setContactPhone("123456")
						.setGroupCdnUrl("groupCdnUrl")
						.setGroupCount(2)
						.setGroupUuid("groupUuid")
						.setAdPicture(adPictures)
						.setLocation("gaborone")
						.build();
											
		Advert advert = new Advert.AdvertBuilder()
				.setAdvertId(1L)
				.setAdvertDetail(advertDetail)
				.setAdvertStatus(AdvertStatus.SUBMITTED)
				.setAdvertDetail(advertDetail)
				.setMenu(menu)
				.build();
		
		AdvertInfo advertInfo = new AdvertInfo();
		advertInfo.setSubject(advert.getAdvertDetail().getTitle());
		advertInfo.setBody(advert.getAdvertDetail().getBody());
		advertInfo.setContactEmail(advert.getAdvertDetail().getContactEmail());
		advertInfo.setContactNo(advert.getAdvertDetail().getContactPhone());
		advertInfo.setLocation(advert.getAdvertDetail().getLocation());
		advertInfo.setGroupCdnUrl(advert.getAdvertDetail().getGroupCdnUrl());
		advertInfo.setGroupCount(advert.getAdvertDetail().getGroupCount());
		advertInfo.setGroupSize(advert.getAdvertDetail().getGroupSize());
		advertInfo.setGroupUuid(advert.getAdvertDetail().getGroupUuid());
		advertInfo.setSubMenuId(advert.getMenu().getMenuId());
		
		List<AdPicture>  advertPictures = advert.getAdvertDetail().getAdPicture();
		List<String> cdnUrl = advertPictures
						.stream()
						.map(ad -> ad.getCdnUrl())
						.collect(Collectors.toList());
		
		advertInfo.setImageCdnUrl(cdnUrl);
		
		
		Mockito.when(advertService.read(Mockito.anyLong())).thenReturn(advert);
		Mockito.when(menuService.read(Mockito.anyLong())).thenReturn(submenu);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/edit")
								.param("id", "1"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("editAd"))
								.andDo(MockMvcResultHandlers.print())
								.andReturn();
		
		assert(mvcResult.getModelAndView().getModel().containsKey("advertInfo"));
		AdvertInfo modelAttribute = (AdvertInfo) mvcResult.getModelAndView()
				.getModel().get("advertInfo");
		
		assert(modelAttribute.getSubject().equals(advertInfo.getSubject()));
		assert(modelAttribute.getBody().equals(advertInfo.getBody()));
		assert(modelAttribute.getContactEmail().equals(advertInfo.getContactEmail()));
		assert(modelAttribute.getContactNo().equals(advertInfo.getContactNo()));
		assert(modelAttribute.getGroupCdnUrl().equals(advertInfo.getGroupCdnUrl()));
		
		
	}
	/**
	 * URL: /advert/edit/submit
	 * Test the condition: if(advert.getAdvertDetail().getGroupUuid() == null)
	 * AdvertInfo image arraylists are empty i.e advert does not contain images
	 * @throws Exception 
	 */
	@Test
	public void testSubmitEditAdvertNoImg() throws Exception{
		Menu menu = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("menu1")
				.build();
		AdvertDetail advertDetail = new AdvertDetail
				.AdvertDetailBuilder()
				.setGroupCount(0)
				.setGroupSize(null)
				.setGroupCdnUrl(null)
				.setGroupUuid(null)
				.build();
		Advert advert = new Advert.AdvertBuilder()
				.setAdvertId(1L)
				.setAdvertStatus(AdvertStatus.SUBMITTED)
				.setAdvertDetail(advertDetail)
				.setMenu(menu)
				.build();
		
		Mockito.when(advertService.read(1L)).thenReturn(advert);
		Mockito.when(menuService.read(Mockito.anyLong())).thenReturn(menu);
		
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/advert/edit/submit")
			.param("advertId", "1")
			.param("menuId", "1")
			.param("subMenuId", "2")
			.param("location", "gaborone")
			.param("subject", "car for sale")
			.param("body", "car for sale in gaborone") 
			.param("contactEmail", "admin@email")
			.param("contactNo", "71406569"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
			.andExpect(MockMvcResultMatchers.content().string("success"))
			.andDo(MockMvcResultHandlers.print())
			.andReturn();
		

	}
	/**
	 * URL: /advert/edit/submit
	 * Test the condition: if(advert.getAdvertDetail().getGroupUuid().equals(advertInfo.getGroupUuid()) == false)
	 * AdvertInfo contains images which will replace existing images
	 * @throws Exception 
	 */
	@Test
	public void testSubmitEditAdvertWithImg() throws Exception{
		List<AdPicture> adPictures = new ArrayList<AdPicture>();
		adPictures.add(
				new AdPicture.AdPictureBuilder()
						.setAdPictureId(1)
						.setName("about me sample 3.PNG")
						.setUuid("e45920a4-1d42-43b0-ac9b-a4e797f8edb4")
						.setSize(135083L)
						.build()
				);
		adPictures.add(
				new AdPicture.AdPictureBuilder()
						.setAdPictureId(2)
						.setName("IMAG1139_1")
						.setUuid("d32a0038-f9bd-4948-9ac5-4e23352e0f07")
						.setSize(779273L)
						.build()
				);
		Menu menu = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("menu1")
				.build();
		AdvertDetail advertDetail = new AdvertDetail
				.AdvertDetailBuilder()
				.setGroupCount(1)
				.setGroupSize(1024L)
				.setAdPicture(adPictures)
				.setGroupCdnUrl("groupCdnUrl1")
				.setGroupUuid("groupUuid1")
				.build();
		Advert advert = new Advert.AdvertBuilder()
				.setAdvertId(1L)
				.setAdvertStatus(AdvertStatus.SUBMITTED)
				.setAdvertDetail(advertDetail)
				.setMenu(menu)
				.build();
		
		Mockito.when(advertService.read(1L)).thenReturn(advert);
		Mockito.when(menuService.read(Mockito.anyLong())).thenReturn(menu);

				MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/advert/edit/submit")
					.param("advertId", "1")
					.param("menuId", "1")
					.param("subMenuId", "1")
					.param("location", "gaborone")
					.param("subject", "car for sale")
					.param("body", "car for sale in gaborone") 
					.param("contactEmail", "admin@email")
					.param("contactNo", "71406569")
					.param("imageCdnUrl", "cdnUrl1", "cdnUrl2")
					.param("imageName", "image1", "image2")
					.param("imageUuid", "uuid1", "uuid2")
					.param("imageSize", "1024","800")
					.param("groupUuid", "groupUuid2")
					.param("groupSize", "1824")
					.param("groupCount", "2")
					.param("groupCdnUrl", "groupCdnUrl2"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
					.andExpect(MockMvcResultMatchers.content().string("success"))
					.andDo(MockMvcResultHandlers.print())
					.andReturn();

	}
	@Test
	public void testDeleteAdvert() {
		Advert advert = new Advert.AdvertBuilder()
							.setAdvertId(1L)
							.build();
							
		
	
		Mockito.when(advertService.read(Mockito.anyLong())).thenReturn(advert);
		
		try {
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/advert/delete")
				.param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
				.andExpect(MockMvcResultMatchers.content().string("success"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setAuthentication() {
		authentication.setAuthenticated(true);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	
}
