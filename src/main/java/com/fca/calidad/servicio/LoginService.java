package com.fca.calidad.servicio;
import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.model.User.*;

public class LoginService {
	IDAOUser dao;

	public LoginService(IDAOUser d) {
		dao = d;
	}
	
	public boolean login(String email, String pass) {
		
		User u = dao.findByUserName(email);
		if (u != null) {
			if (u.getPassword() == pass) {
				
				return true;
			}
			else {
				
				return false;
			}
		}
		else {
			return false;
		}
	}
}