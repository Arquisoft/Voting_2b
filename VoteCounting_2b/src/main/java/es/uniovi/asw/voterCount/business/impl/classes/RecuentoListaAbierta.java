package es.uniovi.asw.voterCount.business.impl.classes;

import java.util.HashMap;
import java.util.HashSet;
import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.model.Vote;
import es.uniovi.asw.dbManagement.model.VoteOpenList;

public class RecuentoListaAbierta {

	public HashMap<String,Integer> recuento(Election e){
		
		HashMap<String,Integer> resultado = new HashMap<>();
		
		HashSet<Vote> votosAbiertas = (HashSet<Vote>) e.getVotes();
		for(Vote voto :votosAbiertas){
			VoteOpenList vo = (VoteOpenList) voto;
			resultado.put(vo.getCandidate().getCandidature().getName(), vo.getNumVotes());
		}
		
		return resultado;
	}
}
