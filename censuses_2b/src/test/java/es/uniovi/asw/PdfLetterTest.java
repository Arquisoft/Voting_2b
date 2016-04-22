package es.uniovi.asw;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.personalLetters.PersonalLetterGenerator;
import es.uniovi.asw.reportGeneration.PasswordGenerator;

public class PdfLetterTest {

	private PersonalLetterGenerator letterGenerator;

	private Voter voter;
	private ArrayList<Voter> voters;

	@Before
	public void setUp() {

		voter = new Voter("Ignacio Fernandez Fernandez", "56378435A", "ignacio@uniovi.es", 350);
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
