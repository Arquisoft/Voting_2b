package es.uniovi.asw.dbManagement.persistence;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByEmailAndPassword(String name,String password);

}
