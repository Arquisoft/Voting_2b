package es.uniovi.asw.dbManagement.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.model.Turnout;
import es.uniovi.asw.dbManagement.model.Voter;



@Repository
@Transactional
public class TurnoutRepositoryImpl implements TurnoutRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public void merge(Turnout turnout) {
		turnout.setElection(entityManager.find(Election.class, turnout.getElection().getId()));
		turnout.setVoter(entityManager.find(Voter.class, turnout.getVoter().getId()));
		entityManager.persist(turnout);
	}
	
}
