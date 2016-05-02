package es.uniovi.asw.electionday.parser;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.model.Candidate;
import es.uniovi.asw.model.Candidature;


public class RCandidateExcel extends RCandidate implements ReadCandidate{

	public List<Candidate> readFile(String path) {
		XSSFWorkbook wb;
		XSSFSheet sheet;
		Iterator<Row> rows;
		Row row = null;
		Candidate candidate;
		
		List<Candidate> candidatos = new ArrayList<Candidate>();
		
		try {

			wb = new XSSFWorkbook(new File(path));
			System.out.println("Leyendo fichero " + path);
			sheet = wb.getSheetAt(0);
			rows = sheet.iterator();
				
			//First line (headers in excel file)
			rows.next();
				
			while (rows.hasNext()) {
				row = rows.next();
					
				candidate = new Candidate();
				candidate.setName(row.getCell(0)!=null ? row.getCell(0).toString():null);
				candidate.setSurname(row.getCell(1)!=null ? row.getCell(1).toString():null);
				
				Candidature candidature = new Candidature();
				candidature.setName(row.getCell(2)!=null ? row.getCell(2).toString():null);
				
				candidate.setDNI(row.getCell(3)!=null ?	row.getCell(3).toString():null);
				
				//Row empty, without cells
				if (row.getCell(0)!=null || row.getCell(1)!=null || row.getCell(2)!=null || row.getCell(3)!=null){
					candidate.setCandidature(candidature!=null ? candidature:null);
					candidatos.add(candidate);
				}
					
			}
								
		} catch (InvalidFormatException e) {
			System.out.println("El fichero no es un .xlsx");
		} catch (Exception e) {
			String[] fileName = path.split("/");
			System.out.println("El fichero " + fileName[fileName.length - 1] + " no existe");
		}
		
		return candidatos;
	}


}
