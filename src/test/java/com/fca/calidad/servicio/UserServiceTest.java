package com.fca.calidad.servicio;

//import static org.junit.Assert.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.model.User.User;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {
	
	
	
	private User usuario;
	private UserService servicio;
	private IDAOUser dao;
	private  HashMap<Integer,User> db;

	
	@BeforeEach
	void setup(){
		dao= mock(IDAOUser.class);	
		servicio=new UserService(dao);
		db=new HashMap<Integer,User>();
		User user = new User("user", "existinguser@example.com", "user");
	    db.put(1, user);
	}

	@Test
	void guardarUsuarioTest() {
		  User user = new User("user", "existinguser@example.com", "user");
		    db.put(1, user);
		String nombre = "newUser";
	    String email = "newuser@example.com";
	    String password = "securePass"; 


	    when(servicio.findUserByEmail(email)).thenReturn(null);
	    when(dao.save(any(User.class))).thenAnswer(new Answer<Integer>() {
	        public Integer answer(InvocationOnMock invocation) throws Throwable {
	            User user = (User) invocation.getArguments()[0];
	            int newId = db.size() + 1;
	            user.setId(newId); 
	            db.put(newId, user);
	            return newId; 
			 } 
			 
		});
		
		
	    User result = servicio.createUser(nombre, email, password);


	    assertNotNull(result); 
	    assertEquals(nombre, result.getName());
	    assertEquals(email, result.getEmail()); 
	    assertEquals(password, result.getPassword()); 
	    System.out.println("Usuario guardado: " + db);
	}
	
	
	
	@Test
	void findUserByEmailTest() {

	    String email = "test@example.com";
	    User expectedUser = new User("Test User", email, "testPassword");
	    expectedUser.setId(1);
	    when(dao.findUserByEmail(email)).thenReturn(expectedUser);
	    User result = servicio.findUserByEmail(email);
	    assertEquals(email, result.getEmail());
	    assertEquals("Test User", result.getName());
	  }

	
		@Test
		void actualizarDataTest() {
			User oldUser = new User("oldUser", "oldEmail", "oldPassword");
			oldUser.setId(1);
			db.put(1, oldUser);
			System.out.println("Base de datos antes de la actualización: " + db);
			User newUser = new User("newUser", "oldEmail", "newPassword");
			newUser.setId(1);
			when(dao.findById(1)).thenReturn(oldUser);
			when(dao.updateUser(any(User.class))).thenAnswer(new Answer<User>() {
			    public User answer(InvocationOnMock invocation) throws Throwable {
			        User arg = (User) invocation.getArguments()[0];
			        db.replace(arg.getId(), arg);
			        System.out.println("Actualización realizada: " + db);
	 
			        return db.get(arg.getId());
			    }
			});
	 
			User result = servicio.updateUser(newUser);
			assertThat(result.getName(), is("newUser"));
			assertThat(result.getPassword(), is("newPassword"));
			System.out.println("Base de datos después de la actualización: " + db);
		}
		
		
		@Test
		void eliminarUserTest() {
		    User userToDelete = new User("deleteUser", "deleteEmail", "deletePassword");
		    userToDelete.setId(1);
		    db.put(1, userToDelete);
		    System.out.println("Base de datos antes de la eliminación: " + db);
		    when(dao.findById(1)).thenReturn(userToDelete);
		    when(dao.deleteById(anyInt())).thenAnswer(new Answer<Boolean>() {
		        public Boolean answer(InvocationOnMock invocation) throws Throwable {
		            int id = (Integer) invocation.getArguments()[0];
		            return db.remove(id) != null;
		        }
		    });
	 
		    boolean isDeleted = servicio.deleteUser(1);
	 
		    // Verificación
		    assertTrue(isDeleted);
		    assertNull(db.get(1));
		    System.out.println("Base de datos después de la eliminación: " + db);
		}
		
		
	}
	 	




