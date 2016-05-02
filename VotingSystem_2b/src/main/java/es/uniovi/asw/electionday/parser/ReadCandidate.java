package es.uniovi.asw.electionday.parser;

import java.util.List;

import es.uniovi.asw.model.Candidate;

public interface ReadCandidate {

	public List<Candidate> read(String path);
}
