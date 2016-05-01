package es.uniovi.asw.voterResult.impl.business.observer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.voterResult.impl.business.polls.Poll;


public class Observer {

	private List<Poll> polls= new LinkedList<>();
	
	public void adscribir(Poll poll){
		this.polls.add(poll);
	}
	
	public void quitar(Poll poll){
		this.polls.remove(poll);
	}
	
	public void notificar(String tipo,Map<String,Integer> votos){
		for(Poll poll:polls){
			poll.actualizar(tipo,votos);
		}
	}
}
