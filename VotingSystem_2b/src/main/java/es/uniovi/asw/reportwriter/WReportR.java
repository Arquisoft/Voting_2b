package es.uniovi.asw.reportwriter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class WReportR implements WriteReport {

	private static final Logger log = Logger.getLogger(WReportR.class.getName());
	private static FileHandler handler;
	
	public WReportR() {
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