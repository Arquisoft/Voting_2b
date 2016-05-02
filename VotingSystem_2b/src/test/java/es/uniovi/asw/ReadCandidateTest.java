package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.electionday.parser.RCandidateExcel;
import es.uniovi.asw.model.Candidate;

public class ReadCandidateTest {

	@Test
	public void test() {
		List<Candidate> candidates = new RCandidateExcel().readFile("src/test/resources/testCandidatos.xlsx");
		assertEquals("Alberto",candidates.get(0).getName());
		assertEquals("Alvarez",candidates.get(0).getSurname());
		assertEquals("AA",candidates.get(0).getCandidature().getName());
		assertEquals("11111111A",candidates.get(0).getDNI());
		
		assertEquals("Borja",candidates.get(1).getName());
		assertEquals("Baston",candidates.get(1).getSurname());
		assertEquals("BB",candidates.get(1).getCandidature().getName());
		assertEquals("22222222B",candidates.get(1).getDNI());
		
		assertEquals("Carlos",candidates.get(2).getName());
		assertEquals("Cienfuegos",candidates.get(2).getSurname());
		assertEquals("CC",candidates.get(2).getCandidature().getName());
		assertEquals("33333333C",candidates.get(2).getDNI());
	}

}
