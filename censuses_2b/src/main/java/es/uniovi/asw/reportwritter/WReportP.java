package es.uniovi.asw.reportwritter;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class WReportP implements WriteReport {

	private static final Logger log = Logger.getLogger(WReportP.class.getName());
	private static FileHandler handler;
	
	public WReportP() {
		configureLogger(log);
	}
	
	@Override
	public void report(String... reasons) {
		for (String reason:reasons)
			log.info(reason);
	}
	
	private void configureLogger(Logger logger) {
		try {
			if (handler == null) {
				handler = new FileHandler("fails.log");
				handler.setFormatter(new MyFormatter());
			}
			logger.addHandler(handler);
			
		} catch (Exception e) { }
	}

}
