package es.uniovi.asw.electionday.parser;

import es.uniovi.asw.electionday.reportwritter.WReportR;
import es.uniovi.asw.electionday.reportwritter.WriteReport;
import es.uniovi.asw.model.Candidature;

public class CheckFailsCandidature {
	
	private static final WriteReport reporter = new WReportR();
	public static String file;

	public static boolean check(Candidature candidature){
		return comprobarFallosNombre(candidature) && comprobarFallosInitial(candidature)
				&& comprobarFallosDescripcion(candidature);
	}

	public static boolean comprobarFallosDescripcion(Candidature candidature) {
		if (candidature.getDescription() == null || candidature.getDescription().equals("")) {
			reporter.report(file + " Descripción vacía --- ---");
			return false;
		}
		return true;
	}

	public static boolean comprobarFallosInitial(Candidature candidature) {
		if (candidature.getInitial() == null || candidature.getInitial().equals("")) {
			reporter.report(file + " Inicio vacío --- ---");
			return false;
		}
		return true;
	}

	public static boolean comprobarFallosNombre(Candidature candidature) {
		if (candidature.getName() == null || candidature.getName().equals("")) {
			reporter.report(file + " Nombre de la candidatura vacío --- ---");
			return false;
		}
		return true;
	}
}
