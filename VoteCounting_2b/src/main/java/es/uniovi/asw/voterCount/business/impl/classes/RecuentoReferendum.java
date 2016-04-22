package es.uniovi.asw.voterCount.business.impl.classes;

import java.util.HashMap;
import java.util.HashSet;

import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.model.Vote;
import es.uniovi.asw.dbManagement.model.VoteReferendum;
import es.uniovi.asw.dbManagement.persistence.Repository;

public class RecuentoReferendum {

	public HashMap<String,Integer> recuento(Election e){
		HashMap<String,Integer> resultado = new HashMap<>();
		HashSet<Vote> votos = (HashSet<Vote>) e.getVotes();
		
		int yes = 0;
		int no = 0;

		for (Vote v : votos) {
			if(!v.isReadyToRecount()){
				v.setReadyToRecount(true);
				Repository.voteR.save(v);
			}
			VoteReferendum vr = (VoteReferendum) v;
			yes += vr.getYeses();
			no += vr.getNoes();
		}
		
		resultado.put("yes", yes);
		resultado.put("no", no);
		
		return resultado;
	}
}
