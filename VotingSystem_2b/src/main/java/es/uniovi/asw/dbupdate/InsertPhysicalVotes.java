package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Vote;

public interface InsertPhysicalVotes {

	void insert(List<Vote> votes, PollingPlace pollingPlace);
}
