package es.uniovi.asw.physicalvoting;

import es.uniovi.asw.database.InsertHasVoted;
import es.uniovi.asw.database.InsertHasVotedP;
import es.uniovi.asw.model.Voter;

public class InsertHasVotedR implements InsertHasVoted {

	@Override
	public void insert(Voter voter) {
		new InsertHasVotedP().insert(voter);
	}

}
