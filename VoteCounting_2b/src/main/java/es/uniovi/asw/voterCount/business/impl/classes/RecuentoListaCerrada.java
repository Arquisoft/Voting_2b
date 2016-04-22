package es.uniovi.asw.voterCount.business.impl.classes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.model.PollingPlace;
import es.uniovi.asw.dbManagement.model.VoteClosedList;
import es.uniovi.asw.dbManagement.persistence.Repository;

public class RecuentoListaCerrada {
	
	public static int escaños=8;

	public HashMap<String,Integer> recuento(Election e){
		
		HashMap<String, List<Double>> votosCerradas = new HashMap<>();
		HashMap<String, Integer> escañosPartidos = new HashMap<>();
		
		List<PollingPlace> colegios = (List<PollingPlace>) Repository.pollingPlaceR.findAll();
		
		for(PollingPlace pp : colegios){
			List<VoteClosedList> votosColegio = Repository.voteR.findByElectionAndPollingPlace(e, pp);
			for(VoteClosedList votoPartido:votosColegio){	
				
				double votosPartido = votoPartido.getNumVotes();
				List<Double> lista = new LinkedList<>();
				for(int i=1;i<=escaños;i++){
					lista.add(votosPartido/i);
				}

				votosCerradas.put(votoPartido.getCandidature().getName(), lista);
				escañosPartidos.put(votoPartido.getCandidature().getName(), 0);
			}
		}
		
		//Repartir los escaños
		for(int i=1;i<=escaños;i++){
			String partidoMax="";
			double max=0;
			for(String key: votosCerradas.keySet()){
				List<Double> listaHont = votosCerradas.get(key);
				double maxHont = listaHont.get(0);
				if(maxHont>max){
					max=maxHont;
					partidoMax=key;
				}
			}
			List<Double> listaMax = votosCerradas.get(partidoMax);
			listaMax.remove(max);
			votosCerradas.replace(partidoMax, listaMax);
			
			escañosPartidos.replace(partidoMax, escañosPartidos.get(partidoMax)+1);
		}
	
		return escañosPartidos;
	}
}
