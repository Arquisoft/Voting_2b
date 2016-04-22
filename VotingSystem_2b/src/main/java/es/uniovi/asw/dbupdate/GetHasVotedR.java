package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.Turnout;
import es.uniovi.asw.model.Voter;

public class GetHasVotedR implements GetHasVoted {

	@Override
	public Turnout get(Voter voter) {
		return new GetHasVotedP().get(voter);
	}

}
