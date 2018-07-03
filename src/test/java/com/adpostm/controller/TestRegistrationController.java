package com.adpostm.controller;

import static org.mockito.Mockito.when;


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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.adpostm.domain.model.AppUser;
import com.adpostm.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:spring-security.xml")
public class TestRegistrationController {
	

	private MockMvc mockMvc;

	@Mock
	private UserService iUserService;
	@InjectMocks
	private RegistrationController registrationController;
	
	@Before
	public void setup() throws Exception{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".jsp");
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(registrationController)
					.setViewResolvers(viewResolver)
					.build();
		
		
	}
	@Test
	public void testRegistration() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/register"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("register"))
				.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void testUserNameExistsTrue() throws Exception {
		when(iUserService.usernameValid(Mockito.any(String.class))).thenReturn(true);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/usernameValid")
						.param("email","userEmail"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.content().string("true"))
						.andDo(MockMvcResultHandlers.print());	
		
	}
	@Test
	public void testUserNameExistsFalse() throws Exception {
		when(iUserService.usernameValid("userEmail")).thenReturn(false);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/usernameValid")
						.param("email","userEmail"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.content().string("false"))
						.andDo(MockMvcResultHandlers.print());
						
		
	}
	/*@Test
	public void testSubmitRegistrationValidUser() throws Exception {
		when(iUserService.createUser(Mockito.any(AppUser.class))).thenReturn(1);
		when(iUserService.getUserById(Mockito.anyInt())).thenReturn(Mockito.any(AppUser.class));
		//when(iUserService.updateUser(Mockito.any(AppUser.class))).thenReturn();
		
		mockMvc.perform(MockMvcRequestBuilders.post("/register")
					.param("fname", "pearl")
					.param("lname", "molefe")
					.param("email", "pearl@email")
					.param("password", "pearl"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.view().name("register"))
					.andExpect(MockMvcResultMatchers.model()
							.attribute("msg", "<p  class='bg-info'>You have "
									+ "successfully registered. You will be "
									+ "redirected to the login page.</p>"))
					.andExpect(MockMvcResultMatchers.header()
							.string("refresh", "5;url=/adpostm/login"))
					.andDo(MockMvcResultHandlers.print());
					
		
	}*/
}
