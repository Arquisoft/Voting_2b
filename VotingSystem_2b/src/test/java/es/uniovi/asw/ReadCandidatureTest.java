package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.electionday.parser.RCandidatureExcel;
import es.uniovi.asw.model.Candidature;

public class ReadCandidatureTest {

	@Test
	public void test() {
		List<Candidature> candidaturas = new RCandidatureExcel().readFile("src/test/resources/testCandidatures.xlsx");
		assertEquals("Podemos",candidaturas.get(0).getName());
		assertEquals("Podemos",candidaturas.get(0).getInitial());
		assertEquals("Partido politico",candidaturas.get(0).getDescription());
		
		assertEquals("Ciudadanos",candidaturas.get(1).getName());
		assertEquals("Cs",candidaturas.get(1).getInitial());
		assertEquals("Partido politico",candidaturas.get(1).getDescription());
		
		assertEquals("Partido Popular",candidaturas.get(2).getName());
		assertEquals("PP",candidaturas.get(2).getInitial());
		assertEquals("Partido politico",candidaturas.get(2).getDescription());
		
		assertEquals("Partido Socialista Obrero Español",candidaturas.get(3).getName());
		assertEquals("PSOE",candidaturas.get(3).getInitial());
		assertEquals("Partido politico",candidaturas.get(3).getDescription());
		
		assertEquals("Unión Progreso y Democracia",candidaturas.get(4).getName());
		assertEquals("UPyD",candidaturas.get(4).getInitial());
		assertEquals("Partido politico",candidaturas.get(4).getDescription());
		
		assertEquals("Izquierda Unida",candidaturas.get(5).getName());
		assertEquals("IU",candidaturas.get(5).getInitial());
		assertEquals("Partido politico",candidaturas.get(5).getDescription());
		
		assertEquals("Vox",candidaturas.get(6).getName());
		assertEquals("Vox",candidaturas.get(6).getInitial());
		assertEquals("Partido politico",candidaturas.get(6).getDescription());

	}

}
