package es.uniovi.asw.electionday.parser;

import java.util.List;

import es.uniovi.asw.model.Candidature;

public interface ReadCandidature {

	public List<Candidature> read(String path);
}
