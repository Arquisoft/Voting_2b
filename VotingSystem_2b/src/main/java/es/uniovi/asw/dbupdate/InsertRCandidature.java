package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.model.Candidature;

public class InsertRCandidature implements InsertCandidature{

	@Override
	public List<Candidature> insert(List<Candidature> candidaturas) {
		return new InsertPCandidature().insert(candidaturas);
	}

}
