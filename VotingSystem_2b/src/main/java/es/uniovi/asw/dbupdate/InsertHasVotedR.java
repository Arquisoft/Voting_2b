package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.Voter;

public class InsertHasVotedR implements InsertHasVoted {

	@Override
	public void insert(Voter voter) {
		new InsertHasVotedP().insert(voter);
	}

}
