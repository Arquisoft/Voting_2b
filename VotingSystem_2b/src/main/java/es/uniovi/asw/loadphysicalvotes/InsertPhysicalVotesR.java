package es.uniovi.asw.loadphysicalvotes;

import java.util.List;

import es.uniovi.asw.dbupdate.InsertPhysicalVotes;
import es.uniovi.asw.dbupdate.InsertPhysicalVotesP;
import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Vote;

public class InsertPhysicalVotesR implements InsertPhysicalVotes {

	@Override
	public void insert(List<Vote> votes, PollingPlace pollingPlace) {
		new InsertPhysicalVotesP().insert(votes, pollingPlace);
	}

}
