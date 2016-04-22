package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.model.Candidate;

public interface ReadCandidate {

	public List<Candidate> read(String path);
}
