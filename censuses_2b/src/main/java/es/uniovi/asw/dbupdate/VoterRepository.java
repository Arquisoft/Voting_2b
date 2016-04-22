package es.uniovi.asw.dbupdate;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.model.Voter;

/**
 * 
 * @author Nauce Lopez
 *
 */
public interface VoterRepository extends CrudRepository<Voter, Long> {

	Voter findByEmail(String email);
	Voter findByNif(String nif);
}
