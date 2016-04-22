package es.uniovi.asw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.asw.model.Voter;
import es.uniovi.asw.parser.Parser;
import es.uniovi.asw.parser.RCensusExcel;

public class ReadCensusExcelTest {

	@BeforeClass
	public static void initialize() {
		LoadUsers.main("//test");
	}
	
	@Test
	public void testReadEmpty() {
		List<Voter> voters = new RCensusExcel().read("src/test/resources/testEmpty.xlsx");
		
		assertEquals(true, voters.isEmpty());
	}
	
	@Test
	public void testFileNotFound() {
		List<Voter> voters = new RCensusExcel().read("src/test/resources/notFound.xlsx");
		
		assertEquals(true, voters.isEmpty());
	}
	
	@Test
	public void testFileNotXlsx() {
		List<Voter> voters = new RCensusExcel().read("src/test/resources/test.txt");
		
		assertEquals(true, voters.isEmpty());
	}
	
	@Test
	public void testWriteAndRead() {
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		sheet.createRow(0);
		sheet.getRow(0).createCell(0).setCellValue("Nombre");
		sheet.getRow(0).createCell(1).setCellValue("NIF");
		sheet.getRow(0).createCell(2).setCellValue("Email");
		sheet.getRow(0).createCell(3).setCellValue("Colegio");
		
		for (int i=1; i < letras.length(); i++) {
			sheet.createRow(i);
			
			sheet.getRow(i).createCell(0).setCellValue("nombreTestWrite" + i);
			sheet.getRow(i).createCell(1).setCellValue("9999999" + i%10 + letras.charAt(i) );
			sheet.getRow(i).createCell(2).setCellValue("emailTestWrite" + i + "@test.com");
			sheet.getRow(i).createCell(3).setCellValue("5000");
		}
		
		try {
			FileOutputStream out = new FileOutputStream(new File("src/test/resources/testWriteAndRead.xlsx"));
			workbook.write(out);
		    out.close();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		
		List<Voter> voters = new RCensusExcel().read("src/test/resources/testWriteAndRead.xlsx");
		
		Voter voterToCheck;
		
		for (int i=1; i< letras.length(); i++) {
			voterToCheck = new Voter("nombreTestWrite" + i,
									"9999999" + i%10 + letras.charAt(i),
									"emailTestWrite" + i + "@test.com",
									5000);
			assertEquals(voterToCheck, voters.get(i-1));
			assertNotNull(Parser.voterRepository.findByEmail(voterToCheck.getEmail()));
			Parser.voterRepository.delete(Parser.voterRepository.findByEmail(voterToCheck.getEmail()));
		}
	
	}
	
	@Test
	public void testRight() {
		List<Voter> voters = new RCensusExcel().read("src/test/resources/testRight.xlsx");
		
		List<Voter> votersToCheck = new ArrayList<Voter>();
		votersToCheck.add(new Voter("Ignacio Fernandez Fernandez", 
							"56378435A",
							"ignacio@uniovi.es",
							350));
		votersToCheck.add(new Voter("Nauce Lopez Gonzalez", 
				"53678541Z",
				"nauce@uniovi.es",
				440));
		votersToCheck.add(new Voter("Jorge Riopedre Vega", 
				"48976526C",
				"jorge@uniovi.es",
				220));
		
		for (Voter voter:votersToCheck) {
			Assert.assertNotNull(Parser.voterRepository.findByEmail(voter.getEmail()));
			Parser.voterRepository.delete(Parser.voterRepository.findByEmail(voter.getEmail()));
		}
	}

}
