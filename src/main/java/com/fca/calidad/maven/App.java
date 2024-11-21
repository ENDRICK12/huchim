package com.fca.calidad.maven;

import com.fca.calidad.dao.DAOUserSQLite;
import com.fca.calidad.model.User.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        DAOUserSQLite dao = new DAOUserSQLite();
        User usuario = new User ("nombre" , "correo","password");
        int id = dao.save (usuario);
        
        usuario.setId(id);
        dao.findById(id);
        
        System.out.println(dao.findById(id).toString());
        
        dao.deleteById(2);
        
    }
}
