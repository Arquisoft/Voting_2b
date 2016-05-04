package es.uniovi.asw.electionday.parser;

import es.uniovi.asw.electionday.reportwritter.WReportR;
import es.uniovi.asw.electionday.reportwritter.WriteReport;
import es.uniovi.asw.model.Constituency;
import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Region;

public class CheckFailsRegion {

	private static final WriteReport reporter = new WReportR();
	public static String file;
	
	public static boolean check(Region r){
		return comprobarFallosNombre(r) && comprobarFallosConstituency(r) && comprobarFallosPollingPlace(r);
	}
	
	public static boolean comprobarFallosNombre(Region r){
		if (r.getName() == null || r.getName().equals("")) {
			reporter.report(file + " Nombre vacío --- ---");
			return false;
		}
		return true;
	}
	
	public static boolean comprobarFallosConstituency(Region r){
		for(Constituency c:r.getConstituencies()){
			if(c.getName()==null || c.getName().equals("")){
				reporter.report(file + " Nombre vacío --- ---");
				return false;
			}
		}
		return true;
	}

	public static boolean comprobarFallosPollingPlace(Region r){
		
		for(Constituency c:r.getConstituencies()){
			for(PollingPlace p:c.getPollingPlaces()){
				if (p.getId() == null)  {
					reporter.report(file + " PollingPlace vacío --- ---");
					return false;
				} else if (p.getId()<0) {
					reporter.report(file + " PollingPlace no válido --- " + p.getId() + " ---");
					return false;
				}
			}
		}

		return true;
	}
}
