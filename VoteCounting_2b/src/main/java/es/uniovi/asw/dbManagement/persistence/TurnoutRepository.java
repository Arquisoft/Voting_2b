package es.uniovi.asw.dbManagement.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.model.Turnout;
import es.uniovi.asw.dbManagement.model.Voter;

public interface TurnoutRepository extends CrudRepository<Turnout, Long>, TurnoutRepositoryCustom {
	
	Turnout findByVoterAndElection(Voter voter, Election election);
	List<Turnout> findByElection(Election election);
}
