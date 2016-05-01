package es.uniovi.asw.voterAdmin.impl.business.classes;

import es.uniovi.asw.dbManagement.model.User;
import es.uniovi.asw.dbManagement.persistence.Repository;
import es.uniovi.asw.voterAdmin.impl.business.ValidatorService;


public class SimpleValidatorService implements ValidatorService
{
	@Override
	public boolean validar(String email, String password)
	{
		User u = Repository.userR.findByEmailAndPassword(email,password);
		
		if(u!=null)
			return true;
		
		else
			return false;
	}
}