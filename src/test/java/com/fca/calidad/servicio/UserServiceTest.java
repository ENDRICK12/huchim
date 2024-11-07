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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
		
	}

	@Test
	void updateTest() {
		User usuarioViejo=new User ("nombre1","email","password");
		usuarioViejo.setId(1);
		db.put(usuarioViejo.getId(), usuarioViejo);
		
		User usuarioNuevo=new User ("NuevoNombre","email","nuevoPassword");
		usuarioNuevo.setId(1);
		
		when(dao.findById(1)).thenReturn(usuarioViejo);
		
		when(dao.updateUser(any(User.class))).thenAnswer(new Answer<User>() {
			
			 public User answer(InvocationOnMock invocation)throws Throwable{
				 
				 User arg=(User) invocation.getArguments() [0];
				 
				 return db.get(arg.getId());
				
			 } 
			 
		});
		
		
		User result = servicio.updateUser(usuarioNuevo);
		
		assertThat("nuevoPassword" , is(result.getPassword()));
		assertThat("NuevoNombre", is(result.getName()));
	}
	
	public User CreateUser(String name, String email, String password) {
		User user= null;
		
		if (password.length()>=8 && password.length()<=16) {
			user=dao.findUserByEmail(email);
			if (user==null) {
				user=new User(name,email,password);
				int id =dao.save(user);
				user.setId(id);
			}
			
		}
		return user;
	}

	public List<User>findAllUsers(){
		List<User>users=new ArrayList<User>();
		users=dao.findAll();
		return users;
	}
	
	
	public User findUserByEmail(String email) {
		return dao.findUserByEmail(email);
		
	}
	
	public User findUserById(int id) {
		return dao.findById(id);
	}
	
	
	@Test
	void GuardarUserTest() {
		
	    int sizeBefore = db.size();
	    System.out.println("sizeBefore = " + sizeBefore);
	    when(dao.findUserByEmail("existinguser@example.com")).thenReturn(db.get(1));
	    when(dao.save(any(User.class))).thenAnswer(new Answer<Integer>() {
	        public Integer answer(InvocationOnMock invocation) throws Throwable {
	            User arg = (User) invocation.getArguments()[0];
	            if (db.values().stream().anyMatch(user -> user.getEmail().equals(arg.getEmail()))) {
	                System.out.println("El usuario ya existe y no se guarda nuevamente.");
	            } else {
	            	
	                db.put(db.size() + 1, arg);
	                System.out.println("Usuario guardado: " + arg.getEmail());
	            }
 
	            return db.size();
	        }
	    });
	   
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
	 	




