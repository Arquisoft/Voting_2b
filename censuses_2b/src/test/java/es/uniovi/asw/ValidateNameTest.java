package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.asw.model.Voter;
import es.uniovi.asw.parser.Parser;
import es.uniovi.asw.parser.RCensusExcel;

public class ValidateNameTest {

	@BeforeClass
	public static void initialize() {
		LoadUsers.main("//test");
	}
	@Test
	public void test() {
		List<Voter> chequeados = new RCensusExcel().read("src/test/resources/test2.xlsx");
	
		assertNotNull(Parser.voterRepository.findByEmail("dd@uniovi.es"));
		assertNull(Parser.voterRepository.findByEmail("ee@uniovi.es"));
		assertNull(Parser.voterRepository.findByEmail("ff@uniovi.es"));
		
		for (Voter voter:chequeados) {
			assertNotNull(Parser.voterRepository.findByEmail(voter.getEmail()));
			Parser.voterRepository.delete(voter);
		}
	}
	
}
