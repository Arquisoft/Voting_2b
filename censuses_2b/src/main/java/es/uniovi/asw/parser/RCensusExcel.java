package es.uniovi.asw.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RCensusExcel extends RCensus implements ReadCensus{
	
	public RCensusExcel(String... writterFormats) {
		super(writterFormats);
	}

	@Override
	public List<VoterInfo> readFile(String path) {
		XSSFWorkbook wb;
		XSSFSheet sheet;
		Iterator<Row> rows;
		Row row = null;
		VoterInfo voterInfo;
		
		List<VoterInfo> voterValues = new ArrayList<VoterInfo>();
		
		try {

			wb = new XSSFWorkbook(new File(path));
			System.out.println("Leyendo fichero " + path);
			sheet = wb.getSheetAt(0);
			rows = sheet.iterator();
				
			//First line (headers in excel file)
			rows.next();
				
			while (rows.hasNext()) {
				row = rows.next();
					
				voterInfo = new VoterInfo(row.getCell(0) != null ? row.getCell(0).toString() : null,
						row.getCell(1) != null ? row.getCell(1).toString() : null,
						row.getCell(2) != null ? row.getCell(2).toString() : null,
						row.getCell(3) != null ? row.getCell(3).toString() : null,
						row.getRowNum());
	
				//Row empty, without cells
				if (!voterInfo.isEmpty())
					voterValues.add(voterInfo);
					
			}
								
		} catch (InvalidFormatException e) {
			System.out.println("El fichero no es un .xlsx");
		} catch (Exception e) {
			String[] fileName = path.split("/");
			System.out.println("El fichero " + fileName[fileName.length - 1] + " no existe");
		}
		
		return voterValues;
	}

}
