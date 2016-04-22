package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.model.Candidature;

public abstract class RCandidature implements ReadCandidature{

	@Override
	public List<Candidature> read(String path) {
		
		List<Candidature> candidaturas = readFile(path);
		
		return candidaturas;
	}
	
	abstract List<Candidature> readFile(String path);
}
