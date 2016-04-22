package es.uniovi.asw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uniovi.asw.dbupdate.InsertRCandidate;
import es.uniovi.asw.dbupdate.Repository;
import es.uniovi.asw.dbupdate.RepositoryConfiguration;
import es.uniovi.asw.model.Candidate;
import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.parser.RCandidateExcel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, RepositoryConfiguration.class})

public class InsertCandidateTest {

	@Test
	public void test() {
		List<Candidate> candidatos = new RCandidateExcel().read("src/test/resources/testCandidatosNombre.xlsx");
		assertEquals(3,candidatos.size());
		
	}
	
	@Test
	public void testPartido() {
		List<Candidate> candidatos = new RCandidateExcel().read("src/test/resources/testCandidatosPartido.xlsx");
		assertEquals(3,candidatos.size());
	}
	
	
	@Test
	public void testDeComprobacion() {
		List<Candidate> candidatos = new RCandidateExcel().read("src/test/resources/testCandidatosInsercion.xlsx");
		new InsertRCandidate().insert(candidatos);
		System.out.println(Repository.candidateR.count());
		Candidature c = new Candidature();
		c = Repository.candidatureR.findByName("EE");
		List<Candidate> candidato = Repository.candidateR.findByCandidature(c);
		for(Candidate cand:candidato){
			assertEquals("Edgar",cand.getName());
			assertEquals("55555555E",cand.getDNI());
			assertEquals("Estevez",cand.getSurname());
		}
		Candidature c2 = new Candidature();
		c2 = Repository.candidatureR.findByName("GG");
		List<Candidate> candidato2 = Repository.candidateR.findByCandidature(c2);
		for(Candidate cand2:candidato2){
			assertNull(cand2.getName());
			assertNull(cand2.getDNI());
			assertNull(cand2.getSurname());
		}
		new InsertRCandidate().insert(candidatos);
	}

}
