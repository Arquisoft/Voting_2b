package es.uniovi.asw.database;

import es.uniovi.asw.database.repositories.Repository;
import es.uniovi.asw.model.Turnout;
import es.uniovi.asw.model.Voter;

public class InsertHasVotedP implements InsertHasVoted {

	@Override
	public void insert(Voter voter) {
		System.out.println(voter);
		Turnout turnout = new Turnout(Repository.electionR.findActual(), Repository.voterR.findByNif(voter.getNif()));
		Repository.turnoutR.merge(turnout);
	}

}
