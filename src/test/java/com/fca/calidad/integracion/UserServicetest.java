


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
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
	import org.dbunit.operation.DatabaseOperation;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;

	import com.fca.calidad.dao.DAOUserSQLite;
	import com.fca.calidad.model.User.User;
	import com.fca.calidad.servicio.UserService;



	class UserServiceTest extends DBTestCase {
		private static final String String = null;
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
			
			
			@Test
			void createUser2Test() {
				User usuario = userService.createUser("nombre", "email", "password");
				
				IDatabaseConnection connection;
				try {
					connection = getConnection();
					IDataSet databaseDataSet = connection.createDataSet();
					ITable tablaReal = databaseDataSet.getTable("users");
					String nombreReal = (String) tablaReal.getValue(0, "name");
					String nombreEsperado = "nombre";
					System.out.println("Real =" + nombreReal);
					assertEquals(nombreReal, nombreEsperado);
					System.out.println("E=" + (String) tablaReal.getValue(0, "email"));
					System.out.println("P=" + (String) tablaReal.getValue(0, "password"));
					assertEquals((String), tablaReal.getValue(0, "email"),"email");
					assertEquals((String), tablaReal.getValue(0, "password"),"password");
				}catch (Exception e) {	
					e.printStackTrace();
					fail("Fallo");
				}
			}
			
			
				@Test
				void createUser3Test() {
					User usuario = userService.createUser("nombre2", "email2", "password2");
					IDatabaseConnection connection;
					try {
						connection = getConnection();
						IDataSet databaseDataSet = connection.createDataSet();
						ITable tablaReal = databaseDataSet.getTable("users");
						IDataSet exceptedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("C:\\Users\\beatr\\git\\BettySastreCalidad2024\\src\\resources\\createUser.xml"));
						ITable exceptedTable = exceptedDataSet.getTable("users");
						
						ITable filteredTable = DefaultColumnFilter.includedColumnsTable(tablaReal, 
								exceptedTable.getTableMetaData().getColumns());
						
						Assertion.assertEquals(filteredTable, exceptedTable);
					}catch (Exception e) {
						
						e.printStackTrace();
						fail("Fallo");
					}
				
					
				}
				
					
					
					
					
					
					
							
			
		}

	

