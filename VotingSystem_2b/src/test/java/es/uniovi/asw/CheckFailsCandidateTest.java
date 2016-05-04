package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.electionday.parser.CheckFailsCandidate;
import es.uniovi.asw.electionday.parser.RCandidateExcel;
import es.uniovi.asw.model.Candidate;

public class CheckFailsCandidateTest {

	@Test
	public void testNombre() {
		List<Candidate> candidates = new RCandidateExcel().readFile("src/test/resources/testCandidatosNombre.xlsx");
		assertEquals(3, candidates.size());
		Candidate c1 = candidates.get(0);
		Candidate c2 = candidates.get(1);
		Candidate c3 = candidates.get(2);
		assertFalse(CheckFailsCandidate.check(c1));
		assertTrue(CheckFailsCandidate.check(c2));
		assertTrue(CheckFailsCandidate.check(c3));
	}
	
	@Test
	public void testPartido() {
		List<Candidate> candidates = new RCandidateExcel().readFile("src/test/resources/testCandidatosPartido.xlsx");
		assertEquals(3, candidates.size());
		Candidate c1 = candidates.get(0);
		Candidate c2 = candidates.get(1);
		Candidate c3 = candidates.get(2);
		assertTrue(CheckFailsCandidate.check(c1));
		assertFalse(CheckFailsCandidate.check(c2));
		assertFalse(CheckFailsCandidate.check(c3));
	}
	
	@Test
	public void testDNI() {
		List<Candidate> candidates = new RCandidateExcel().readFile("src/test/resources/testCandidatosDNI.xlsx");
		assertEquals(4, candidates.size());
		Candidate c1 = candidates.get(0);
		Candidate c2 = candidates.get(1);
		Candidate c3 = candidates.get(2);
		Candidate c4 = candidates.get(3);
		assertFalse(CheckFailsCandidate.check(c1));
		assertFalse(CheckFailsCandidate.check(c2));
		assertFalse(CheckFailsCandidate.check(c3));
		assertTrue(CheckFailsCandidate.check(c4));
	}

}
