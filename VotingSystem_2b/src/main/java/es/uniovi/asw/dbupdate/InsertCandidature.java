package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.model.Candidature;

public interface InsertCandidature {

	public List<Candidature> insert(List<Candidature> candidaturas);
}
