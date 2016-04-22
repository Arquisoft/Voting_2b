package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.model.Voter;

public interface ReadCensus {

	public List<Voter> read(String path);
}
