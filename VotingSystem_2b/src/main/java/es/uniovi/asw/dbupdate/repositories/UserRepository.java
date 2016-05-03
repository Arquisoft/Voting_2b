package es.uniovi.asw.dbupdate.repositories;

import es.uniovi.asw.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByEmailAndPassword(String name, String password);

}
