package com.adpostm.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.adpostm.domain.enumerated.AdvertStatus;
import com.adpostm.domain.enumerated.MenuType;
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
	 * Param f=false, s=null
	 * @throws Exception
	 */
	@Test
	public void testSearchSNull() throws Exception {
		List<Menu> menus = new ArrayList<Menu>();
		List<Advert> advertList = new ArrayList<Advert>();
		List<String> year = Arrays.asList(new String[] {"2002","2017"});
		List<String> location = Arrays.asList(new String[] {"gaborone","mogoditshane"});
		
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
		menus.add(new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("phone accessories")
				.setMenuType(MenuType.SUBMENU)
				.build()
				);
		
		menus.add(new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("vehicles")
				.setMenuType(MenuType.HOME)
				.build()
				);
		
		Mockito.when(advertService.findAll(Advert.class))
			.thenReturn(advertList);
		Mockito.when(advertService.findDistinctLocation()).thenReturn(location);
		Mockito.when(advertService.findDistinctYear()).thenReturn(year);
		Mockito.when(menuService.findAllByMenuTypeIn(Mockito.<List<MenuType>>any()))
			.thenReturn(menus.subList(1, 1));
				
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/search")
								.param("f", "false"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("search"))
								.andExpect(MockMvcResultMatchers.model().attribute("locations", hasSize(2)))
								.andExpect(MockMvcResultMatchers.model().attribute("years", hasSize(2)))
								.andExpect(MockMvcResultMatchers.model().attribute("years", year))
								.andExpect(MockMvcResultMatchers.model().attribute("locations", location))
								.andExpect(MockMvcResultMatchers.model().attribute("categories", menus.subList(1, 1)))
								.andDo(MockMvcResultHandlers.print())
								.andReturn();
		
		assert(mvcResult.getRequest().getAttribute("searchMsg").equals("Your search did not return any results"));
		assertNull(mvcResult.getRequest().getAttribute("advertList"));
	}
	/**
	 * Param f=false, s=Not Null
	 * @throws Exception
	 */
	@Test
	public void testSearchSNotNull() throws Exception {
		List<Advert> advertList = new ArrayList<Advert>();
		List<String> year = Arrays.asList(new String[] {"2002","2017"});
		List<String> location = Arrays.asList(new String[] {"gaborone","mogoditshane"});
		
		
		AdvertDetail advertDetail1 = new AdvertDetail
				.AdvertDetailBuilder()
				.setTitle("car for sale")
				.setBody("There is a car for sale")
				.setContactEmail("admin@email")
				.setContactPhone("123456")
				.setLocation("gaborone")
				.build();
		
		AdvertDetail advertDetail2 = new AdvertDetail
				.AdvertDetailBuilder()
				.setTitle("Pouches for sale")
				.setBody("Head sets 25 pula Pouches 50 pula")
				.setContactEmail("admin@email")
				.setContactPhone("123456")
				.setLocation("mogoditshane")
				.build();
		
		Menu menu1 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("phone accessories")
				.setMenuType(MenuType.HOME)
				.build();
		
		Menu menu2 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("vehicles")
				.setMenuType(MenuType.HOME)
				.build();
		
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(1L)
						.setSubmittedDate(new GregorianCalendar(2002,10,15).getTime())
						.setAdvertDetail(advertDetail1)
						.setMenu(menu1)
						.build()
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(2L)
						.setSubmittedDate(new GregorianCalendar(2015,2,15).getTime())
						.setAdvertDetail(advertDetail2)
						.setMenu(menu2)
						.build()
				);
		
		//Mock Service Layer
		Mockito.when(advertService.search(Mockito.anyString()))
			.thenReturn(Arrays.asList(advertList.get(1)));
		Mockito.when(advertService.findDistinctLocation())
		.thenReturn(location);
		Mockito.when(advertService.findDistinctYear())
		.thenReturn(year);
		Mockito.when(menuService.findAllByMenuTypeIn(Mockito.<MenuType>anyList()))
		.thenReturn(Arrays.asList(new Menu[] {menu1, menu2}));

		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/search")
								.param("f", "false")
								.param("s", "Pouches"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("search"))
								.andExpect(MockMvcResultMatchers.model().attribute("advertList", hasSize(1)))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("advertList", Arrays.asList(advertList.get(1))))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("locations", location))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("years", year))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("categories", Arrays.asList(menu1, menu2)))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("searchMsg", "Search result for <span style = "
												+ "'font-weight: bold'>Pouches</span>"))
								.andDo(MockMvcResultHandlers.print())
								.andReturn();

	}
	/**
	 * Param f=true, s=Not Null
	 * @throws Exception
	 */
	@Test
	public void testSearchWithCategoryFilter() throws Exception {
		List<Advert> advertList = new ArrayList<Advert>();
		List<String> year = Arrays.asList(new String[] {"2002","2017"});
		List<String> location = Arrays.asList(new String[] {"gaborone","mogoditshane"});

		
		AdvertDetail advertDetail1 = new AdvertDetail
				.AdvertDetailBuilder()
				.setTitle("car for sale")
				.setBody("There is a car for sale")
				.setContactEmail("admin@email")
				.setContactPhone("123456")
				.setLocation("gaborone")
				.build();
		
		AdvertDetail advertDetail2 = new AdvertDetail
				.AdvertDetailBuilder()
				.setTitle("Pouches for sale")
				.setBody("Head sets 25 pula Pouches 50 pula")
				.setContactEmail("admin@email")
				.setContactPhone("123456")
				.setLocation("mogoditshane")
				.build();
		
		Menu menu1 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("phone accessories")
				.setMenuType(MenuType.HOME)
				.build();
		
		Menu menu2 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("vehicles")
				.setMenuType(MenuType.HOME)
				.build();
		
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(1L)
						.setSubmittedDate(new GregorianCalendar(2002,10,15).getTime())
						.setAdvertDetail(advertDetail1)
						.setMenu(menu2)
						.build()
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(2L)
						.setSubmittedDate(new GregorianCalendar(2015,2,15).getTime())
						.setAdvertDetail(advertDetail2)
						.setMenu(menu1)
						.build()
				);
		
		//Mock Service Layer
		Mockito.when(advertService.search(Mockito.anyString()))
			.thenReturn(Arrays.asList(advertList.get(1)));
		Mockito.when(advertService.findDistinctLocation())
			.thenReturn(location);
		Mockito.when(advertService.findDistinctYear())
			.thenReturn(year);
		Mockito.when(menuService.findAllByMenuTypeIn(Mockito.<MenuType>anyList()))
			.thenReturn(Arrays.asList(new Menu[] {menu1, menu2}));
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/search")
								.param("f", "true")
								.param("s", "pouches")
								.param("category", "phone accessories")
								.param("category", "vehicles"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("search"))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("advertList", Arrays.asList(advertList.get(1))))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("locations", location))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("years", year))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("categories", Arrays.asList(menu1, menu2)))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("searchMsg", "Search result for <span style = "
												+ "'font-weight: bold'>pouches</span>"))
								.andDo(MockMvcResultHandlers.print())
								.andReturn();

	}
	/**
	 * Param f=true, s=Not Null
	 * @throws Exception
	 */
	@Test
	public void testSearchWithLocationFilter() throws Exception {
		List<Advert> advertList = new ArrayList<Advert>();
		List<String> year = Arrays.asList(new String[] {"2002","2017"});
		List<String> location = Arrays.asList(new String[] {"gaborone","mogoditshane"});
		
		
		AdvertDetail advertDetail1 = new AdvertDetail
				.AdvertDetailBuilder()
				.setTitle("car for sale")
				.setBody("There is a car for sale")
				.setContactEmail("admin@email")
				.setContactPhone("123456")
				.setLocation("gaborone")
				.build();
		
		AdvertDetail advertDetail2 = new AdvertDetail
				.AdvertDetailBuilder()
				.setTitle("Pouches for sale")
				.setBody("Head sets 25 pula Pouches 50 pula")
				.setContactEmail("admin@email")
				.setContactPhone("123456")
				.setLocation("mogoditshane")
				.build();
		
		Menu menu1 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("phone accessories")
				.setMenuType(MenuType.HOME)
				.build();
		
		Menu menu2 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("vehicles")
				.setMenuType(MenuType.HOME)
				.build();
		
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(1L)
						.setSubmittedDate(new GregorianCalendar(2002,10,15).getTime())
						.setAdvertDetail(advertDetail1)
						.setMenu(menu1)
						.build()
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(2L)
						.setSubmittedDate(new GregorianCalendar(2015,2,15).getTime())
						.setAdvertDetail(advertDetail2)
						.setMenu(menu2)
						.build()
				);
		
		//Mock Service Layer
		Mockito.when(advertService.search(Mockito.anyString()))
			.thenReturn(advertList);
		Mockito.when(advertService.findDistinctLocation())
			.thenReturn(location);
		Mockito.when(advertService.findDistinctYear())
			.thenReturn(year);
		Mockito.when(menuService.findAllByMenuTypeIn(Mockito.<MenuType>anyList()))
			.thenReturn(Arrays.asList(new Menu[] {menu1, menu2}));
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/search")
								.param("f", "true")
								.param("s", "sale")
								.param("category", "phone accessories")
								.param("category", "vehicles")
								.param("year", "2015"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("search"))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("advertList", Arrays.asList(advertList.get(1))))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("locations", location))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("years", year))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("categories", Arrays.asList(menu1, menu2)))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("searchMsg", "Search result for <span style = "
												+ "'font-weight: bold'>sale</span>"))
								.andDo(MockMvcResultHandlers.print())
								.andReturn();

	}
	/**
	 * Param f=true, s=Not Null
	 * @throws Exception
	 */
	@Test
	public void testSearchWithYearFilter() throws Exception {
		List<AdPicture> adPictures = new ArrayList<AdPicture>();
		List<Advert> advertList = new ArrayList<Advert>();
		List<String> year = Arrays.asList(new String[] {"2002","2017"});
		List<String> location = Arrays.asList(new String[] {"gaborone","mogoditshane"});
		
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
		
		AdvertDetail advertDetail1 = new AdvertDetail
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
		
		AdvertDetail advertDetail2 = new AdvertDetail
				.AdvertDetailBuilder()
				.setTitle("Pouches for sale")
				.setBody("Head sets 25 pula Pouches 50 pula")
				.setContactEmail("admin@email")
				.setContactPhone("123456")
				.setGroupCount(0)
				.setLocation("mogoditshane")
				.build();
		
		Menu menu1 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("phone accessories")
				.setMenuType(MenuType.HOME)
				.build();
		
		Menu menu2 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("vehicles")
				.setMenuType(MenuType.HOME)
				.build();
		
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(1L)
						.setSubmittedDate(new GregorianCalendar(2002,10,15).getTime())
						.setAdvertDetail(advertDetail1)
						.setMenu(menu1)
						.build()
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(2L)
						.setSubmittedDate(new GregorianCalendar(2015,2,15).getTime())
						.setAdvertDetail(advertDetail2)
						.setMenu(menu2)
						.build()
				);
		
		//Mock Service Layer
		Mockito.when(advertService.search(Mockito.anyString()))
			.thenReturn(Arrays.asList(advertList.get(1)));
		Mockito.when(advertService.findDistinctLocation())
			.thenReturn(location);
		Mockito.when(advertService.findDistinctYear())
			.thenReturn(year);
		Mockito.when(menuService.findAllByMenuTypeIn(Mockito.<MenuType>anyList()))
			.thenReturn(Arrays.asList(new Menu[] {menu1, menu2}));
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/search")
								.param("f", "true")
								.param("s", "sale")
								.param("category", "phone accessories")
								.param("category", "vehicles"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("search"))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("advertList", Arrays.asList(advertList.get(1))))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("locations", location))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("years", year))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("categories", Arrays.asList(menu1, menu2)))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("searchMsg", "Search result for <span style = "
												+ "'font-weight: bold'>sale</span>"))
								.andDo(MockMvcResultHandlers.print())
								.andReturn();

	}
	/**
	 * Param f=true, s=Not Null
	 * @throws Exception
	 */
	@Test
	public void testSearchWithImageFilter() throws Exception {
		List<AdPicture> adPictures = new ArrayList<AdPicture>();
		List<Advert> advertList = new ArrayList<Advert>();
		List<String> year = Arrays.asList(new String[] {"2002","2017"});
		List<String> location = Arrays.asList(new String[] {"gaborone","mogoditshane"});
		
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
		
		AdvertDetail advertDetail1 = new AdvertDetail
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
		
		AdvertDetail advertDetail2 = new AdvertDetail
				.AdvertDetailBuilder()
				.setTitle("Pouches for sale")
				.setBody("Head sets 25 pula Pouches 50 pula")
				.setContactEmail("admin@email")
				.setContactPhone("123456")
				.setGroupCount(0)
				.setLocation("mogoditshane")
				.build();
		
		AdvertDetail advertDetail3 = new AdvertDetail
				.AdvertDetailBuilder()
				.setTitle("house for sale")
				.setBody("Two bedroom house for sale in tlokweng")
				.setContactEmail("admin@email")
				.setContactPhone("123456")
				.setGroupCount(0)
				.setLocation("tlokweng")
				.build();
		
		Menu menu1 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("phone accessories")
				.setMenuType(MenuType.HOME)
				.build();
		
		Menu menu2 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("vehicles")
				.setMenuType(MenuType.HOME)
				.build();
		
		Menu menu3 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("house for sale")
				.setMenuType(MenuType.HOME)
				.build();
		
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(1L)
						.setSubmittedDate(new GregorianCalendar(2002,10,15).getTime())
						.setAdvertDetail(advertDetail1)
						.setMenu(menu1)
						.build()
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(2L)
						.setSubmittedDate(new GregorianCalendar(2015,2,15).getTime())
						.setAdvertDetail(advertDetail2)
						.setMenu(menu2)
						.build()
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(3L)
						.setSubmittedDate(new GregorianCalendar(2015,2,15).getTime())
						.setAdvertDetail(advertDetail3)
						.setMenu(menu2)
						.build()
				);
		
		//Mock Service Layer
		Mockito.when(advertService.search(Mockito.anyString()))
			.thenReturn(advertList);
		Mockito.when(advertService.findDistinctLocation())
			.thenReturn(location);
		Mockito.when(advertService.findDistinctYear())
			.thenReturn(year);
		Mockito.when(menuService.findAllByMenuTypeIn(Mockito.<MenuType>anyList()))
			.thenReturn(Arrays.asList(new Menu[] {menu1, menu2}));
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/search")
								.param("f", "true")
								.param("s", "sale")
								.param("category", "vehicles")
								.param("category", "phone accessories")
								.param("location", "gaborone")
								.param("location", "mogoditshane")
								.param("image", "true"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("search"))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("advertList", Arrays.asList(advertList.get(0))))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("locations", location))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("years", year))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("categories", Arrays.asList(menu1, menu2)))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("searchMsg", "Search result for <span style = "
												+ "'font-weight: bold'>sale</span>"))
								.andDo(MockMvcResultHandlers.print())
								.andReturn();

	}
	/**
	 * Param f=true, s=Not Null
	 * @throws Exception
	 */
	@Test
	public void testSearchWithAllFilters() throws Exception {
		List<AdPicture> adPictures = new ArrayList<AdPicture>();
		List<Advert> advertList = new ArrayList<Advert>();
		List<String> year = Arrays.asList(new String[] {"2002","2017"});
		List<String> location = Arrays.asList(new String[] {"gaborone","mogoditshane"});
		
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
		
		AdvertDetail advertDetail1 = new AdvertDetail
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
		
		AdvertDetail advertDetail2 = new AdvertDetail
				.AdvertDetailBuilder()
				.setTitle("Pouches for sale")
				.setBody("Head sets 25 pula Pouches 50 pula")
				.setContactEmail("admin@email")
				.setContactPhone("123456")
				.setGroupCount(0)
				.setLocation("mogoditshane")
				.build();
		AdvertDetail advertDetail3 = new AdvertDetail
				.AdvertDetailBuilder()
				.setTitle("House for rent")
				.setBody("Compact 2 bedrooms in a multi res property")
				.setContactEmail("admin@email")
				.setContactPhone("123456")
				.setGroupCount(0)
				.setLocation("Tlokweng")
				.build();
		
		Menu menu1 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("phone accessories")
				.setMenuType(MenuType.HOME)
				.build();
		
		Menu menu2 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("vehicles")
				.setMenuType(MenuType.HOME)
				.build();
		
		Menu menu3 = new Menu.MenuBuilder()
				.setMenuId(1L)
				.setMenuName("house for rent")
				.setMenuType(MenuType.HOME)
				.build();
		
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(1L)
						.setSubmittedDate(new GregorianCalendar(2002,10,15).getTime())
						.setAdvertDetail(advertDetail1)
						.setMenu(menu1)
						.build()
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(2L)
						.setSubmittedDate(new GregorianCalendar(2015,2,15).getTime())
						.setAdvertDetail(advertDetail2)
						.setMenu(menu2)
						.build()
				);
		advertList.add(
				new Advert.AdvertBuilder()
						.setAdvertId(3L)
						.setSubmittedDate(new GregorianCalendar(2015,2,15).getTime())
						.setAdvertDetail(advertDetail3)
						.setMenu(menu3)
						.build()
				);
		
		//Mock Service Layer
		Mockito.when(advertService.search(Mockito.anyString()))
			.thenReturn(advertList);
		Mockito.when(advertService.findDistinctLocation())
			.thenReturn(location);
		Mockito.when(advertService.findDistinctYear())
			.thenReturn(year);
		Mockito.when(menuService.findAllByMenuTypeIn(Mockito.<MenuType>anyList()))
			.thenReturn(Arrays.asList(new Menu[] {menu1, menu2}));
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/advert/search")
								.param("f", "true")
								.param("s", "sale")
								.param("location", "gaborone")
								.param("location", "mogoditshane")
								.param("category", "phone accessories")
								.param("category", "vehicles")
								.param("category", "house for rent")
								.param("year", "2002")
								.param("year", "2015")
								.param("image", "true"))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("search"))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("advertList", Arrays.asList(advertList.get(0))))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("locations", location))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("years", year))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("categories", Arrays.asList(menu1, menu2)))
								.andExpect(MockMvcResultMatchers.model()
										.attribute("searchMsg", "Search result for <span style = "
												+ "'font-weight: bold'>sale</span>"))
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
	@Test
	public void testFindDistinctYear() throws Exception {
		String[] array = new String[] {"2019","2018","2017"};
		List<String> year = new ArrayList<String>(Arrays.asList(array));
		
		Mockito.when(advertService.findDistinctYear()).thenReturn(year);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/advert/year"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]").value("2019"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1]").value("2018"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[2]").value("2017"))
				.andReturn();
		
	}
	@Test
	public void testFindDistinctLocation() throws Exception {
		String[] array = new String[] {"Gaborone","Mogoditshane"};
		List<String> year = new ArrayList<String>(Arrays.asList(array));
		
		Mockito.when(advertService.findDistinctLocation()).thenReturn(year);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/advert/location"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]").value("Gaborone"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1]").value("Mogoditshane"))
				.andReturn();
	}
	@Test
	public void testFindDistinctMonth() throws Exception {
		String[] array = new String[] {"January","March","June", "July"};
		List<String> month = new ArrayList<String>(Arrays.asList(array));
		
		Mockito.when(advertService.findDistinctMonth()).thenReturn(month);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/advert/month"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(4))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]").value("January"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1]").value("March"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[2]").value("June"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[3]").value("July"))
				.andReturn();
	}
	
	private void setAuthentication() {
		authentication.setAuthenticated(true);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	
	
}
