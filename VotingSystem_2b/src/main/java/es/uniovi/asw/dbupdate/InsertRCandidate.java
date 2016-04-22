package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.model.Candidate;


public class InsertRCandidate implements InsertCandidate {

	@Override
	public List<Candidate> insert(List<Candidate> candidatos) {
		return new InsertPCandidate().insert(candidatos);
	}

}
