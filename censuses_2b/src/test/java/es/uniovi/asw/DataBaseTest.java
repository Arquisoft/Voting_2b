package es.uniovi.asw;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.asw.model.Voter;
import es.uniovi.asw.parser.Parser;
import es.uniovi.asw.reportGeneration.PasswordGenerator;

public class DataBaseTest {

	@BeforeClass
	public static void initialize() {
		LoadUsers.main("//test");
	}
	
	@Test
	public void test() {
		Voter voter;
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i=0; i < letras.length() ; i++) {
			voter = new Voter("nombreTestDataBase" + i,
					"8888888" + i%10 + letras.charAt(i),
					"emailTestDataBase" + i + "@test.com",
					5000);
			PasswordGenerator.generatePasswords(voter);
			
			Parser.voterRepository.save(voter);
			assertNotNull(Parser.voterRepository.findByEmail(voter.getEmail()));
			Parser.voterRepository.delete(voter);
		}
	}

}
