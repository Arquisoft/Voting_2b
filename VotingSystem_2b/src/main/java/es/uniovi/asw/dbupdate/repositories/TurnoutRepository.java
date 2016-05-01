package es.uniovi.asw.dbupdate.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.Turnout;
import es.uniovi.asw.model.Voter;

public interface TurnoutRepository extends CrudRepository<Turnout, Long>, TurnoutRepositoryCustom {

	Turnout findByVoterAndElection(Voter voter, Election election);
	List<Turnout> findByElection(Election election);
	
}
