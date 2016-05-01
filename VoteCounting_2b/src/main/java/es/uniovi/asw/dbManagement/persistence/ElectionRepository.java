package es.uniovi.asw.dbManagement.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.Election;


public interface ElectionRepository extends CrudRepository<Election, Long>
{	
	Election findById(Long id);
	
	Election findByName(String name);
	
	@Query("SELECT e FROM Election e where e.startDate < CURRENT_TIMESTAMP and e.expiryDate > CURRENT_TIMESTAMP")
	Election findActual();
}