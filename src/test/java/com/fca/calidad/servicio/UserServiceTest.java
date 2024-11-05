package com.fca.calidad.servicio;

//import static org.junit.Assert.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.model.User.User;
import static org.hamcrest.Matchers.is;

class UserServiceTest {
	
	
	
	private User usuario;
	private UserService servicio;
	private IDAOUser dao;
	private  HashMap<Integer,User> baseDatos;

	
	@BeforeEach
	void setup(){
		dao= mock(IDAOUser.class);	
		servicio=new UserService(dao);
		baseDatos=new HashMap<Integer,User>();
		
	}

	@Test
	void updateTest() {
		User usuarioViejo=new User ("nombre1","email","password");
		usuarioViejo.setId(1);
		baseDatos.put(usuarioViejo.getId(), usuarioViejo);
		
		User usuarioNuevo=new User ("nuevoNOmbre","email","nuevoPassword");
		usuarioNuevo.setId(1);
		
		when(dao.findById(1)).thenReturn(usuarioViejo);
		
		when(dao.updateUser(any(User.class))).thenAnswer(new Answer<User>() {
			
			 public User answer(InvocationOnMock invocation)throws Throwable{
				 
				 User arg=(User) invocation.getArguments() [0];
				 
				 return baseDatos.get(arg.getId());
				
			 } 
			 
		});
		
		
		User result = servicio.updateUser(usuarioNuevo);
		
		assertThat("nuevoPassword" , is(result.getPassword()));
		assertThat("NuevoNombre", is(result.getName()));
	}

}
