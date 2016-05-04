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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.dbupdate.repositories.RepositoryConfiguration;
import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.parser.RCensusExcel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {LoadUsers.class, RepositoryConfiguration.class})
public class ReadCensusExcelTest {

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
		
		PollingPlace pollingP = Repository.pollingPlaceR.findOne(5000L);
		if (pollingP == null) {
				pollingP = new PollingPlace();
				pollingP.setId(5000L);
		}
		
		Repository.pollingPlaceR.save(pollingP);
		
		for (int i=1; i< letras.length(); i++) {
			voterToCheck = new Voter("nombreTestWrite" + i,
									"9999999" + i%10 + letras.charAt(i),
									"emailTestWrite" + i + "@test.com",
									pollingP);
			assertEquals(voterToCheck, voters.get(i-1));
			assertNotNull(Repository.voterR.findByEmail(voterToCheck.getEmail()));
			Repository.voterR.delete(Repository.voterR.findByEmail(voterToCheck.getEmail()));
		}
	
	}
	
	@Test
	public void testRight() {
		new RCensusExcel().read("src/test/resources/testRight.xlsx");
		
		PollingPlace pollingP1 = Repository.pollingPlaceR.findOne(350L);
		if (pollingP1 == null) {
				pollingP1 = new PollingPlace();
				pollingP1.setId(350L);
		}
		
		Repository.pollingPlaceR.save(pollingP1);
		
		PollingPlace pollingP2 = Repository.pollingPlaceR.findOne(440L);
		if (pollingP2 == null) {
				pollingP2 = new PollingPlace();
				pollingP2.setId(440L);
		}
		
		Repository.pollingPlaceR.save(pollingP2);
		
		PollingPlace pollingP3 = Repository.pollingPlaceR.findOne(220L);
		if (pollingP3 == null) {
				pollingP3 = new PollingPlace();
				pollingP3.setId(220L);
		}
		
		Repository.pollingPlaceR.save(pollingP3);
		
		List<Voter> votersToCheck = new ArrayList<Voter>();
		votersToCheck.add(new Voter("Ignacio Fernandez Fernandez", 
							"56378435A",
							"ignacio@uniovi.es",
							pollingP1));
		votersToCheck.add(new Voter("Nauce Lopez Gonzalez", 
				"53678541Z",
				"nauce@uniovi.es",
				pollingP2));
		votersToCheck.add(new Voter("Jorge Riopedre Vega", 
				"48976526C",
				"jorge@uniovi.es",
				pollingP3));
		
		for (Voter voter:votersToCheck) {
			Assert.assertNotNull(Repository.voterR.findByEmail(voter.getEmail()));
			Repository.voterR.delete(Repository.voterR.findByEmail(voter.getEmail()));
		}
	}

}
