package es.uniovi.asw.dbupdate.repositories;

import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Voter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VoterRepository extends CrudRepository<Voter,Long>{

	Voter findByEmail(String email);
	Voter findByNif(String nif);

	List<Voter> findByPollingPlace(PollingPlace pollingPlace);
}
