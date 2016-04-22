package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.model.Candidate;

public interface InsertCandidate {

	public List<Candidate> insert(List<Candidate> candidatos);
}
