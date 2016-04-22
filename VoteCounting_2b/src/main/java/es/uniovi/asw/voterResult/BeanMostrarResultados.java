package es.uniovi.asw.voterResult;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import es.uniovi.asw.voterCount.BeanRecuentoVotos;

@Component("BeanResultados")
@Scope("session")
public class BeanMostrarResultados {

	private PieChartModel pieModel1 = new PieChartModel();
	private BarChartModel barModel = new BarChartModel();

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public String mostrarGraficos() {
		

		BeanRecuentoVotos brv = (BeanRecuentoVotos) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("BeanRecuento");

		if (brv.getTipoEleccion().equals("Referendum")) {

			Map<String, Integer> votos = brv.votosReferendum;
			

			pieModel1.clear();
			pieModel1.set("Yes", votos.get("yes"));
			pieModel1.set("No", votos.get("no"));

			//pieModel1.setTitle(brv.getNombreEleccion());
			pieModel1.setLegendPosition("w");

			ChartSeries yesS = new ChartSeries();
			yesS.setLabel("Yes");
			yesS.set("Yes", votos.get("yes"));

			ChartSeries noS = new ChartSeries();
			noS.setLabel("No");
			noS.set("No", votos.get("no"));

			barModel.clear();
			barModel.addSeries(yesS);
			barModel.addSeries(noS);

			//barModel.setTitle(brv.getNombreEleccion());
			barModel.setLegendPosition("ne");
			Axis xAxis = barModel.getAxis(AxisType.X);
			xAxis.setLabel("Options");

			Axis yAxis = barModel.getAxis(AxisType.Y);
			yAxis.setLabel("Num Votes");
			yAxis.setMin(0);
			yAxis.setMax(10);
		}
		
		if(brv.getTipoEleccion().equals("ListaCerrada")){
			Map<String, Integer> votos = brv.votosListaCerrada;

			pieModel1.clear();
			for(String key:votos.keySet()){
				pieModel1.set(key,votos.get(key));
			}
			//pieModel1.setTitle(brv.getNombreEleccion());
			pieModel1.setLegendPosition("w");

			int max=0;
			
			barModel.clear();
			
			for(String key:votos.keySet()){
				ChartSeries serie = new ChartSeries();
				serie.setLabel(key);
				serie.set(" ", votos.get(key));
				barModel.addSeries(serie);
				max+=votos.get(key);
			}

			//barModel.setTitle(brv.getNombreEleccion());
			barModel.setLegendPosition("ne");
			Axis xAxis = barModel.getAxis(AxisType.X);
			xAxis.setLabel("");
			Axis yAxis = barModel.getAxis(AxisType.Y);
			yAxis.setLabel("Num Votes");
			yAxis.setMin(0);
			yAxis.setMax(max);
			
			barModel.setShowDatatip(false);
		}
		
		if(brv.getTipoEleccion().equals("ListaAbierta")){
			Map<String, Integer> votos = brv.votosListaAbierta;

			pieModel1.clear();
			for(String key:votos.keySet()){
				pieModel1.set(key,votos.get(key));
			}
			
			pieModel1.setLegendPosition("w");

			int max=0;
			
			barModel.clear();
			
			for(String key:votos.keySet()){
				ChartSeries serie = new ChartSeries();
				serie.setLabel(key);
				serie.set(" ", votos.get(key));
				barModel.addSeries(serie);
				max+=votos.get(key);
			}

			barModel.setLegendPosition("ne");
			
			Axis xAxis = barModel.getAxis(AxisType.X);
			Axis yAxis = barModel.getAxis(AxisType.Y);
			xAxis.setLabel("");
			yAxis.setLabel("Num Votes");
			yAxis.setMin(0);
			yAxis.setMax(max);
			
			barModel.setShowDatatip(false);
		}
		return "exito";
	}
}
