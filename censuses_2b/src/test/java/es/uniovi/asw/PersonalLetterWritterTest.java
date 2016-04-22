package es.uniovi.asw;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.personalLetters.PersonalLetterGenerator;
import es.uniovi.asw.reportGeneration.PasswordGenerator;

public class PersonalLetterWritterTest {

	private BufferedReader reader;

	private PersonalLetterGenerator letterGenerator;

	private Voter voter;
	private ArrayList<Voter> voters;

	@Before
	public void setUp() {

		voter = new Voter("Ignacio Fernandez Fernandez", "56378435A", "ignacio@uniovi.es", 350);
		voters = new ArrayList<Voter>();
		voters.add(voter);
		letterGenerator = new PersonalLetterGenerator(voters);
		letterGenerator.chooseWritters("t");
		PasswordGenerator.generatePasswords(voters);
		letterGenerator.writeAllLetters();

		try {
			reader = new BufferedReader(new FileReader("letters/" +voter.getNif() + ".txt"));
		} catch (FileNotFoundException e) {

			System.err.println("Archivo no encontrado");

		}

	}

	@Test
	public void testWritePlainLetter() {

		try {


			reader.readLine();
			String email = reader.readLine().split(":\t")[1];
			assertEquals(voter.getEmail(), email);
			String password = reader.readLine().split(":\t")[1];
			assertEquals(voter.getPassword(), password);

		} catch (IOException e) {

			System.err.println("Archivo no encontrado");

		}

	}
	
	@Test(expected = FileNotFoundException.class)  
	public void fileNotFound() throws FileNotFoundException{
		
		
			try {
				reader = new BufferedReader(new FileReader("ninguno"));
			} catch (FileNotFoundException e) {
				
				throw e;
			}
		
		
	}

}
