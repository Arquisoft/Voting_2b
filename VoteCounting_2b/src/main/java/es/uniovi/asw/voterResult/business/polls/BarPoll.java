package es.uniovi.asw.voterResult.business.polls;

import java.util.Map;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

public class BarPoll implements Poll {

	private BarChartModel barModel = new BarChartModel();

	@Override
	public void actualizar(String tipo, Map<String, Integer> votos) {
		if (tipo.compareTo("Referendum") == 0) {

			ChartSeries yesS = new ChartSeries();
			yesS.setLabel("Yes");
			yesS.set("Yes", votos.get("yes"));

			ChartSeries noS = new ChartSeries();
			noS.setLabel("No");
			noS.set("No", votos.get("no"));

			barModel.clear();
			barModel.addSeries(yesS);
			barModel.addSeries(noS);

			// barModel.setTitle(brv.getNombreEleccion());
			barModel.setLegendPosition("ne");
			Axis xAxis = barModel.getAxis(AxisType.X);
			xAxis.setLabel("Options");

			Axis yAxis = barModel.getAxis(AxisType.Y);
			yAxis.setLabel("Num Votes");
			yAxis.setMin(0);
			yAxis.setMax(10);
		} else if (tipo.compareTo("ListaCerrada") == 0) {

			int max = 0;

			barModel.clear();

			for (String key : votos.keySet()) {
				ChartSeries serie = new ChartSeries();
				serie.setLabel(key);
				serie.set(" ", votos.get(key));
				barModel.addSeries(serie);
				max += votos.get(key);
			}

			// barModel.setTitle(brv.getNombreEleccion());
			barModel.setLegendPosition("ne");
			Axis xAxis = barModel.getAxis(AxisType.X);
			xAxis.setLabel("");
			Axis yAxis = barModel.getAxis(AxisType.Y);
			yAxis.setLabel("Num Votes");
			yAxis.setMin(0);
			yAxis.setMax(max);

			barModel.setShowDatatip(false);
		} else if (tipo.compareTo("ListaAbierta") == 0) {
			int max = 0;

			barModel.clear();

			for (String key : votos.keySet()) {
				ChartSeries serie = new ChartSeries();
				serie.setLabel(key);
				serie.set(" ", votos.get(key));
				barModel.addSeries(serie);
				max += votos.get(key);
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
	}

	public BarChartModel getPoll() {
		return barModel;
	}

}
