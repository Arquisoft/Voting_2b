package es.uniovi.asw;

import static org.junit.Assert.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Before;
import org.junit.Test;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.personalLetters.PersonalLetterGenerator;
import es.uniovi.asw.reportGeneration.PasswordGenerator;

public class WordLetterTest {

	private PersonalLetterGenerator letterGenerator;
	private Voter voter;
	private ArrayList<Voter> voters;

	@Before
	public void setUp() {

		voter = new Voter("Ignacio Fernandez Fernandez", "56378435A", "ignacio@uniovi.es", 350);
		voters = new ArrayList<Voter>();
		voters.add(voter);
		letterGenerator = new PersonalLetterGenerator(voters);
		letterGenerator.chooseWritters("w");
		PasswordGenerator.generatePasswords(voters);
		letterGenerator.writeAllLetters();

	}

	@Test
	public void testWritePlainLetter() {

		XWPFDocument docx;
		try {
			docx = new XWPFDocument(new FileInputStream("letters/" + voter.getNif() + ".docx"));
			XWPFWordExtractor we = new XWPFWordExtractor(docx);
			String[] lines = we.getText().split("\n");
			String email = lines[1].split(":\t")[1];
			String password = lines[2].split(":\t")[1];
			assertEquals(voter.getEmail(), email);
			assertEquals(voter.getPassword(), password);
			we.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("resource")
	@Test(expected = IOException.class)
	public void fileNotFound() throws IOException {

		@SuppressWarnings("unused")
		XWPFDocument docx;
		try {
			docx = new XWPFDocument(new FileInputStream("noLetter"));
		} catch (IOException e) {
			throw e;
		}

	}

}
