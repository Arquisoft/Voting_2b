package es.uniovi.asw.dbManagement.persistence;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.Voter;

public interface VoterRepository extends CrudRepository<Voter, Long>{
	Voter findByEmailAndPassword(String email, String password);
	Voter findByEmail(String email);
}
