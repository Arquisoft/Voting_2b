package es.uniovi.asw.electionday.parser;

import java.util.List;

import es.uniovi.asw.model.Region;

public interface ReadRegion {
	
	public List<Region> read(String path);

}
