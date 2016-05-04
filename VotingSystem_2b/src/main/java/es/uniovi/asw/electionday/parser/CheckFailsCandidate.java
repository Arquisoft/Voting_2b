package es.uniovi.asw.electionday.parser;

import es.uniovi.asw.electionday.reportwritter.WReportR;
import es.uniovi.asw.electionday.reportwritter.WriteReport;
import es.uniovi.asw.model.Candidate;

public class CheckFailsCandidate {
	
	private static final WriteReport reporter = new WReportR();
	public static String file;

	public static boolean check(Candidate candidate){
		return comprobarFallosDni(candidate) && comprobarFallosNombre(candidate) 
				&& comprobarFallosApellido(candidate)
				&& comprobarFallosPartido(candidate);
	}
	
	public static boolean comprobarFallosNombre(Candidate candidate){
		if (candidate.getName() == null || candidate.getName().equals("")) {
			reporter.report(file + " Nombre vacío --- ---");
			return false;
		}
		return true;
	}
	
	public static boolean comprobarFallosApellido(Candidate candidate){
		if (candidate.getSurname() == null || candidate.getSurname().equals("")) {
			reporter.report(file + " Apellido vacío --- ---");
			return false;
		}
		return true;
	}
	
	public static boolean comprobarFallosPartido(Candidate candidate){
		if (candidate.getCandidature().getName() == null || candidate.getCandidature().getName().equals("")){
			reporter.report(file + " Partido vacío --- ---");
			return false;
		}
		return true;
	}
	
	public static boolean comprobarFallosDni(Candidate candidate){
		if (candidate.getDNI() == null || candidate.getDNI().equals("")) {
			reporter.report(file + " NIF vacío --- ---");
			return false;
		} else if ((!Character.isLetter(candidate.getDNI().charAt(candidate.getDNI().length() - 1))) 
				|| (!(candidate.getDNI().length() == 9))) {
			reporter.report(file + " NIF no válido --- " + candidate.getDNI() + " ---");
			return false;
		}
		return true;
	}
}
