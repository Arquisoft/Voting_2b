package es.uniovi.asw.voterCount;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import es.uniovi.asw.dbManagement.model.ClosedList;
import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.model.OpenList;
import es.uniovi.asw.dbManagement.model.Referendum;
import es.uniovi.asw.voterCount.business.RecuentoService;
import es.uniovi.asw.voterCount.infrastructure.Factories;

@Component("BeanRecuento")
@Scope("session")
public class BeanRecuentoVotos {
	
	private String tipoEleccion;
	private String nombreEleccion;
	public Map<String,Integer> votosReferendum;
	public Map<String,Integer> votosListaCerrada;
	public Map<String,Integer> votosListaAbierta;
	
	private Map<String,Integer> actual;
	
	public void recuentoVotos(Election e) {;
	
		//TODO
		//TimerBBDD.mantenimientoBBDD();

		RecuentoService recuento = Factories.services.createRecuentoService();

		if (e instanceof Referendum) {
			setTipoEleccion("Referendum");
			setNombreEleccion(e.getName());

			votosReferendum= recuento.recuentoReferendum(e);
			setActual(votosReferendum);
		}
		
		if (e instanceof ClosedList) {
			setTipoEleccion("ListaCerrada");
			setNombreEleccion(e.getName());
			
			votosListaCerrada= recuento.recuentoListaCerrada(e);
			setActual(votosListaCerrada);
		}
		
		if (e instanceof OpenList) {
			setTipoEleccion("ListaAbierta");
			setNombreEleccion(e.getName());
			
			votosListaAbierta= recuento.recuentoListaAbierta(e);
			setActual(votosListaAbierta);
		}
	}

	public String getNombreEleccion() {
		return nombreEleccion;
	}

	public void setNombreEleccion(String nombreEleccion) {
		this.nombreEleccion = nombreEleccion;
	}

	public String getTipoEleccion() {
		return tipoEleccion;
	}

	public void setTipoEleccion(String tipoEleccion) {
		this.tipoEleccion = tipoEleccion;
	}

	public Map<String,Integer> getActual() {
		return actual;
	}

	public void setActual(Map<String,Integer> actual) {
		this.actual = actual;
	}

}
