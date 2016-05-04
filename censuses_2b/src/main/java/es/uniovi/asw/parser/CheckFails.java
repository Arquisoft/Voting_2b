package es.uniovi.asw.parser;

import es.uniovi.asw.dbupdate.WReportR;
import es.uniovi.asw.reportwritter.WriteReport;

public class CheckFails {

	private static final WriteReport reporter = new WReportR();
	public static String file;
	
	public static boolean check(VoterInfo v) {
		return comprobarFallosEmail(v) && comprobarFallosNIF(v) 
				&& comprobarFallosNombre(v) && comprobarFallosPollingPlace(v);
	}

	public static boolean comprobarFallosEmail(VoterInfo v) {

		// Si el email no tiene "@" y punto, si acaba en punto o si es nulo
		if (v.getEmail() == null || v.getEmail().toString().equals("")) {
			reporter.report(file + " -> Línea " + v.getLine() +  ": Email vacío --- ---");
			return false;

		} else if ((!v.getEmail().contains("@")) || (!v.getEmail().contains("."))
				|| (v.getEmail().charAt(v.getEmail().length() - 1) == '.')) {
			reporter.report(file + " -> Línea " + v.getLine() +  ": Email no válido --- " + v.getEmail() + " ---");
			return false;
		}

		return true;
	}
	
	public static boolean comprobarFallosNombre(VoterInfo v) {

		if (v.getName() == null || v.getName().equals("")) {
			reporter.report(file + " -> Línea " + v.getLine() +  ": Nombre vacío --- ---");
			return false;
		}
		return true;

	}
	
	public static boolean comprobarFallosNIF(VoterInfo v) {

		if (v.getNIF() == null || v.getNIF().toString().equals("")) {
			reporter.report(file + " -> Línea " + v.getLine() +  ": NIF vacío --- ---");
			return false;
		} else if ((!Character.isLetter(v.getNIF().charAt(v.getNIF().length() - 1))) || (!(v.getNIF().length() == 9))) {
			reporter.report(file + " -> Línea " + v.getLine() +  ": NIF no válido --- " + v.getNIF() + " ---");
			return false;
		}
		return true;
	}
	
	public static boolean comprobarFallosPollingPlace(VoterInfo v) {

		if ((v.getPollingPlace() == null) || (v.getPollingPlace().toString().equals(""))) {
			reporter.report(file + " -> Línea " + v.getLine() +  ": PollingPlace vacío --- ---");
			return false;
		} else if (Double.parseDouble(v.getPollingPlace()) <= 0) {
			reporter.report(file + " -> Línea " + v.getLine() +  ": PollingPlace no válido --- " + v.getPollingPlace() + " ---");
			return false;
		}
		return true;
	}

}


