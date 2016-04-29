package es.uniovi.asw.voterResult.business.polls;

import java.util.Map;

public interface Poll {
	void actualizar(String tipo, Map<String, Integer> votos);
}
