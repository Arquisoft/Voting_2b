package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.parser.CheckFailsCandidature;
import es.uniovi.asw.parser.RCandidatureExcel;

public class CheckFailsCandidatureTest {

	@Test
	public void testNombre() {
		List<Candidature> candidatures = new RCandidatureExcel().readFile("src/test/resources/testCandidaturesNombre.xlsx");
		assertEquals(3, candidatures.size());
		assertTrue(CheckFailsCandidature.check(candidatures.get(0)));
		assertFalse(CheckFailsCandidature.check(candidatures.get(1)));
		assertTrue(CheckFailsCandidature.check(candidatures.get(2)));

	
	}
	
	@Test
	public void testDescripcion() {
		List<Candidature> candidatures = new RCandidatureExcel().readFile("src/test/resources/testCandidaturesDescripcion.xlsx");
		assertEquals(3, candidatures.size());
		assertTrue(CheckFailsCandidature.check(candidatures.get(0)));
		assertTrue(CheckFailsCandidature.check(candidatures.get(1)));
		assertFalse(CheckFailsCandidature.check(candidatures.get(2)));
	
	}
	
	@Test
	public void testInitial() {
		List<Candidature> candidatures = new RCandidatureExcel().readFile("src/test/resources/testCandidaturesInitial.xlsx");
		assertEquals(3, candidatures.size());
		assertFalse(CheckFailsCandidature.check(candidatures.get(0)));
		assertTrue(CheckFailsCandidature.check(candidatures.get(1)));
		assertFalse(CheckFailsCandidature.check(candidatures.get(2)));
	}
	
	

}
