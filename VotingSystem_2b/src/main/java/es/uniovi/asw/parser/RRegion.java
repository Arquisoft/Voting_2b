package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.model.Region;

public abstract class RRegion implements ReadRegion{
	
	@Override
	public List<Region> read(String path) {
		
		List<Region> regiones = readFile(path);
		
		return regiones;
	}
	
	abstract List<Region> readFile(String path);
}
