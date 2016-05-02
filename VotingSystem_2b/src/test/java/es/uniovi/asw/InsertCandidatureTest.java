package es.uniovi.asw;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.dbupdate.repositories.RepositoryConfiguration;
import es.uniovi.asw.electionday.InsertElectoralListsR;
import es.uniovi.asw.electionday.parser.RCandidatureExcel;
import es.uniovi.asw.model.Candidature;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, RepositoryConfiguration.class})

public class InsertCandidatureTest {


	@Test
	public void testTodoCorrecto() {
		List<Candidature> candidaturas = new RCandidatureExcel().read("src/test/resources/testCandidatures.xlsx");
		assertEquals(7,candidaturas.size());
	}
	
	@Test
	public void testInsercionDeCandidaturas() {
		List<Candidature> candidaturas = new RCandidatureExcel().read("src/test/resources/testCandidaturesInsercion.xlsx");
		assertEquals(4,candidaturas.size());
		new InsertElectoralListsR().insertCandidatures(candidaturas);
		assertEquals("Inicio4",Repository.candidatureR.findByName("Candidatura4").getInitial());
		assertEquals("Inicio5",Repository.candidatureR.findByName("Candidatura5").getInitial());
		assertEquals("Inicio7",Repository.candidatureR.findByName("Candidatura7").getInitial());
		new InsertElectoralListsR().insertCandidatures(candidaturas);
	}

}
