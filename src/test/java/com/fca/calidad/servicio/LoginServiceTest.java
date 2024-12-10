package com.fca.calidad.servicio;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.model.User.User;
import com.fca.calidad.servicio.LoginService;
import com.fca.calidad.servicio.UserService;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
//Import hamcrest
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginServiceTest {

	IDAOUser dao;
	User user;
	LoginService service;
	
	@BeforeEach
	void setUp() throws Exception {
		dao = mock(IDAOUser.class);
		user = mock(User.class);
		service = new LoginService(dao);
		
	}

	@Test
	void loginSuccess_whenPasswordCorrect() {
		when(user.getPassword()).thenReturn("123");
		when(dao.findByUserName("name")).thenReturn(user);
	
		
		//exercise
		boolean res = service.login("name","123");
		
		//assertion
		assertThat(res,is(true));
		
		
	}

}