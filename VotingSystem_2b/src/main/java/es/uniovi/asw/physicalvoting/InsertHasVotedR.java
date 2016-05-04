package es.uniovi.asw.physicalvoting;

import es.uniovi.asw.dbupdate.InsertHasVoted;
import es.uniovi.asw.dbupdate.InsertHasVotedP;
import es.uniovi.asw.model.Voter;

public class InsertHasVotedR implements InsertHasVoted {

	@Override
	public void insert(Voter voter) {
		new InsertHasVotedP().insert(voter);
	}

}
