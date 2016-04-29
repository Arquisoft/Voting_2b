package es.uniovi.asw.voterResult;

import java.util.Map;

import javax.faces.context.FacesContext;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import es.uniovi.asw.voterCount.BeanRecuentoVotos;
import es.uniovi.asw.voterResult.business.observer.Observer;
import es.uniovi.asw.voterResult.business.polls.BarPoll;
import es.uniovi.asw.voterResult.business.polls.PiePoll;

@Component("BeanResultados")
@Scope("session")
public class BeanMostrarResultados {

	private Observer observer;
	
	private PiePoll pieModel1;
	private BarPoll barModel;

	public PieChartModel getPieModel1() {
		return pieModel1.getPoll();
	}

	public BarChartModel getBarModel() {
		return barModel.getPoll();
	}

	public String mostrarGraficos() {
		
		observer = new Observer();
		pieModel1=new PiePoll();
		barModel = new BarPoll();
		observer.adscribir(barModel);
		observer.adscribir(pieModel1);

		BeanRecuentoVotos brv = (BeanRecuentoVotos) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("BeanRecuento");

		if (brv.getTipoEleccion().equals("Referendum")) {

			Map<String, Integer> votos = brv.votosReferendum;
			
			observer.notificar("Referendum", votos);
	
		}
		
		if(brv.getTipoEleccion().equals("ListaCerrada")){
			Map<String, Integer> votos = brv.votosListaCerrada;

			observer.notificar("ListaCerrada", votos);

		}
		
		if(brv.getTipoEleccion().equals("ListaAbierta")){
			Map<String, Integer> votos = brv.votosListaAbierta;

			observer.notificar("ListaAbierta", votos);

		}
		return "exito";
	}
}
