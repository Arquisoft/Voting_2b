package es.uniovi.asw.voterCount.business;

import java.util.HashMap;

import es.uniovi.asw.dbManagement.model.Election;

public interface RecuentoService {

	public HashMap<String, Integer> recuentoReferendum(Election e);
	
	public HashMap<String, Integer> recuentoListaCerrada(Election e);
	
	public HashMap<String, Integer> recuentoListaAbierta(Election e);
}
