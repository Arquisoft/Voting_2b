package es.uniovi.asw.reportwriter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		sb.append(" - ").append(formatMessage(record)).append("\n");
		
		return sb.toString();
	}

}
