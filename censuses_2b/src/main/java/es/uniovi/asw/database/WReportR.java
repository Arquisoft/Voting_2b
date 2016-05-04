package es.uniovi.asw.database;

import es.uniovi.asw.reportwritter.WReportP;
import es.uniovi.asw.reportwritter.WriteReport;

public class WReportR implements WriteReport {

	@Override
	public void report(String... reasons) {
		new WReportP().report(reasons);
	}

}
