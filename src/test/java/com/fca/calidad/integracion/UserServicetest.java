


	package com.fca.calidad.integracion;
//
	import java.io.File;
	import java.io.FileInputStream;
	import org.dbunit.Assertion;
	import org.dbunit.DBTestCase;
	import org.dbunit.PropertiesBasedJdbcDatabaseTester;
	import org.dbunit.database.DatabaseConfig;
	import org.dbunit.database.IDatabaseConnection;
	import org.dbunit.dataset.IDataSet;
	import org.dbunit.dataset.ITable;
	import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
	import org.dbunit.operation.DatabaseOperation;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;

	import com.fca.calidad.dao.DAOUserSQLite;
	import com.fca.calidad.model.User.User;
	import com.fca.calidad.servicio.UserService;



	class UserServiceTest extends DBTestCase {
		private DAOUserSQLite dao;
		private UserService userService;

		public UserServiceTest(){
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.sqlite.JDBC");
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:sqlite:/Users/fca/Desktop/users.db");
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "");
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
		}

		@BeforeEach
		public void setUp() {
			
			dao= new DAOUserSQLite();
			userService =new UserService(dao);
			
			org.dbunit.database.IDatabaseConnection connection;
			try {
				connection = getConnection();
				DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
				IDataSet databaseDataSet =connection.createDataSet();
				ITable actualTable = databaseDataSet.getTable("users");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail("fallo");
				e.printStackTrace();
			}
		}
			protected IDataSet getDataSet() throws Exception {
				return new FlatXmlDataSetBuilder().build(new FileInputStream("src/resources/initDB.xml"));
			}
			@Test
			void createUserTest() {
				
				
				User usuario = userService.createUser("nombre", "email", "password");
				
				
				int resultadoEsperado = 1;
				
				org.dbunit.database.IDatabaseConnection connection;
				
				
				
				try {
					connection = getConnection();
					IDataSet databaseDataSet =connection.createDataSet();
					ITable tablaReal = databaseDataSet.getTable("users");
					tablaReal = databaseDataSet.getTable("users");
					String nombreReal = (String) tablaReal.getValue(0, "name");
					String nombreEsperado = "nombre";
					assertEquals(nombreReal, nombreEsperado);
					
					String emailReal = (String) tablaReal.getValue(0, "email");
					String emailEsperado = "email";
					assertEquals(emailReal, emailEsperado);
					
					String passwordReal = (String) tablaReal.getValue(0, "password");
					String passwordEsperado = "password";
					assertEquals(passwordReal, passwordEsperado);
				
					
					int resultadoAcutual = tablaReal.getRowCount();
					assertEquals(resultadoEsperado, resultadoAcutual );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					fail("fallo");
					e.printStackTrace();
				}
				
			
		}

	}

