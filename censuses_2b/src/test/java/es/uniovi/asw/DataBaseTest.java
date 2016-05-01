package es.uniovi.asw;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.dbupdate.repositories.RepositoryConfiguration;
import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.reportGeneration.PasswordGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {LoadUsers.class, RepositoryConfiguration.class})
public class DataBaseTest {
	
	@Test
	public void test() {
		Voter voter;
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		PollingPlace pollingP = Repository.pollingPlaceR.findOne(5000L);
		if (pollingP == null) {
				pollingP = new PollingPlace();
				pollingP.setId(5000L);
		}
		
		Repository.pollingPlaceR.save(pollingP);
		
		for (int i=0; i < letras.length() ; i++) {
			voter = new Voter("nombreTestDataBase" + i,
					"8888888" + i%10 + letras.charAt(i),
					"emailTestDataBase" + i + "@test.com",
					pollingP);
			PasswordGenerator.generatePasswords(voter);
			
			Repository.voterR.save(voter);
			assertNotNull(Repository.voterR.findByEmail(voter.getEmail()));
			Repository.voterR.delete(voter);
		}
	}

}
