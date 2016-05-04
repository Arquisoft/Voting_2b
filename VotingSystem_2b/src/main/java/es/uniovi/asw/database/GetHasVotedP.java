package es.uniovi.asw.database;

import es.uniovi.asw.database.repositories.Repository;
import es.uniovi.asw.model.Turnout;
import es.uniovi.asw.model.Voter;

public class GetHasVotedP implements GetHasVoted {

	@Override
	public Turnout get(Voter voter) {
		return Repository.turnoutR.findByVoterAndElection(voter, Repository.electionR.findActual());
	}

}
