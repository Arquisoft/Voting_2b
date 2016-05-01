package es.uniovi.asw;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.dbupdate.repositories.RepositoryConfiguration;
import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.personalLetters.PersonalLetterGenerator;
import es.uniovi.asw.reportGeneration.PasswordGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {LoadUsers.class, RepositoryConfiguration.class})
public class PdfLetterTest {

	private PersonalLetterGenerator letterGenerator;

	private Voter voter;
	private ArrayList<Voter> voters;

	@Before
	public void setUp() {
		
		PollingPlace pollingP = Repository.pollingPlaceR.findOne(350L);
		if (pollingP == null) {
				pollingP = new PollingPlace();
				pollingP.setId(350L);
		}
		
		Repository.pollingPlaceR.save(pollingP);

		voter = new Voter("Ignacio Fernandez Fernandez", "56378435A", "ignacio@uniovi.es", pollingP);
		voters = new ArrayList<Voter>();
		voters.add(voter);
		letterGenerator = new PersonalLetterGenerator(voters);
		letterGenerator.chooseWritters("p");
		PasswordGenerator.generatePasswords(voters);
		letterGenerator.writeAllLetters();

	}

	@Test
	public void testWritePlainLetter() {

		try {
			PdfReader reader = new PdfReader("letters/" + voter.getNif() + ".pdf");
			String page = PdfTextExtractor.getTextFromPage(reader, 1);
			String[] lines = page.split("\n");
			String email = lines[1].split(":\t")[1];
			String password = lines[2].split(":\t")[1];
			assertEquals(voter.getEmail(), email);
			assertEquals(voter.getPassword(), password);

		} catch (IOException e) {
			System.err.println("Archivo no encontrado");
		}

	}

	@Test(expected = IOException.class)
	public void fileNotFound() throws IOException {
		try {
			PdfReader reader = new PdfReader("noLetter");
			reader.close();
		} catch (IOException e) {
			throw e;
		}

	}

}
