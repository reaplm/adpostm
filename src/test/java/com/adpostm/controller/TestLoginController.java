package com.adpostm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-security.xml"})
public class TestLoginController {

	private MockMvc mockMvc;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpServletRequest request;
	@InjectMocks
	private LoginController loginController;
	
	@Before
	public void setUp() throws Exception{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".jsp");
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(loginController)
						.setViewResolvers(viewResolver)
						.build();
		
		
	}
	@Test
	public void testLoginWithNoParam() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/login"))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andExpect(MockMvcResultMatchers.view().name("login"))
							.andDo(MockMvcResultHandlers.print())
							.andReturn();
	}
	@Test
	public void testLoginWithErrorParam() throws Exception {

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/login")
							.param("error", "true"))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andExpect(MockMvcResultMatchers.view().name("login"))
							.andExpect(MockMvcResultMatchers.model()
									.attribute("msg", "<p class='errorMsg'>Invalid email or password</p>"))
							.andDo(MockMvcResultHandlers.print())
							.andReturn();
	}
	@Test
	public void testLoginWithLogoutParam() throws Exception {

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/login")
							.param("logout", "true"))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andExpect(MockMvcResultMatchers.view().name("login"))
							.andExpect(MockMvcResultMatchers.model()
									.attribute("msg", "<p class='text-success'>You have successfully logged out!</p>"))
							.andDo(MockMvcResultHandlers.print())
							.andReturn();
	}
}
