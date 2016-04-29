package es.uniovi.asw.voterResult.business.polls;

import java.util.Map;
import org.primefaces.model.chart.PieChartModel;

public class PiePoll implements Poll{
	private PieChartModel pieModel1 = new PieChartModel();

	@Override
	public void actualizar(String tipo, Map<String, Integer> votos) {
		
		if(tipo.compareTo("Referendum")==0){
			
			pieModel1.clear();
			pieModel1.set("Yes", votos.get("yes"));
			pieModel1.set("No", votos.get("no"));

			pieModel1.setLegendPosition("w");
		}
		else if(tipo.compareTo("ListaCerrada")==0){
			
			pieModel1.clear();
			for(String key:votos.keySet()){
				pieModel1.set(key,votos.get(key));
			}
			pieModel1.setLegendPosition("w");
		}
		else if(tipo.compareTo("ListaAbierta")==0){
			pieModel1.clear();
			for(String key:votos.keySet()){
				pieModel1.set(key,votos.get(key));
			}
			
			pieModel1.setLegendPosition("w");
		}
		
	}
	
	public PieChartModel getPoll(){
		return pieModel1;
	}

}
