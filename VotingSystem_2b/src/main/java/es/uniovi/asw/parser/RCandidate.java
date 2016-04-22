package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.model.Candidate;

public abstract class RCandidate implements ReadCandidate{
	
	@Override
	public List<Candidate> read(String path) {
		
		List<Candidate> candidatos = readFile(path);
		
		return candidatos;
	}
	
	abstract List<Candidate> readFile(String path);
}
