package com.adpostm.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.mock.MockCreationSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.adpostm.hibernate.dao.JPAUtil;
import com.adpostm.listener.EMF;
import com.adpostm.listener.HibernateContextListener;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring-security.xml"})
public class TestLoginController {

	private MockMvc mockMvc;
	
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
