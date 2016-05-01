package es.uniovi.asw;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.dbupdate.repositories.RepositoryConfiguration;
import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.personalLetters.PersonalLetterGenerator;
import es.uniovi.asw.reportGeneration.PasswordGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {LoadUsers.class, RepositoryConfiguration.class})
public class PersonalLetterWritterTest {

	private BufferedReader reader;

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
