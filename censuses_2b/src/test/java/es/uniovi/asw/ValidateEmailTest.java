package es.uniovi.asw;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.dbupdate.repositories.RepositoryConfiguration;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.parser.RCensusExcel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {LoadUsers.class, RepositoryConfiguration.class})
public class ValidateEmailTest {

	@Test
	public void test() {
		List<Voter> chequeados = new RCensusExcel().read("src/test/resources/testCheck.xlsx");
		
		assertNull(Repository.voterR.findByEmail("wwuniovi.es"));
		assertNotNull(Repository.voterR.findByEmail("yy@uniovi.es"));
		assertNull(Repository.voterR.findByEmail("mm@uniovies."));
		
		for (Voter voter:chequeados) {
			assertNotNull(Repository.voterR.findByEmail(voter.getEmail()));
			Repository.voterR.delete(voter);
		}

	}

}
