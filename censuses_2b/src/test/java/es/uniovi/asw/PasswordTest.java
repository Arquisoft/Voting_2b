package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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
public class PasswordTest {

	private List<Voter> voters;

	@Before
	public void setUp() {

		this.voters = new ArrayList<Voter>();
		
		PollingPlace pollingP = Repository.pollingPlaceR.findOne(12L);
		if (pollingP == null) {
				pollingP = new PollingPlace();
				pollingP.setId(12L);
		}
		
		Repository.pollingPlaceR.save(pollingP);

		Voter voterA = new Voter("voterA", "a@a.com", "123456a", pollingP);
		Voter voterB = new Voter("voterB", "b@b.com", "asd", pollingP);
		Voter voterC = new Voter("voterC", "c@c.com", "asd", pollingP);
		Voter voterD = new Voter("voterD", "d@d.com", "asd", pollingP);

		voters.add(voterA);
		voters.add(voterB);
		voters.add(voterC);
		voters.add(voterD);

	}

	@Test
	public void testEveryVoterGetsAPassoword() {

		// Primero comprobamos que no tengan contraseñas asignadas
		for (Voter voter : voters) {

			assertNull(voter.getPassword());
		}
		// generamos las contraseñas

		PasswordGenerator.generatePasswords(voters);

		// comprobamos que las contraseñas han sido asignadas y que poseen la
		// longitud necesaria

		for (Voter voter : voters) {

			assertNotNull(voter.getPassword());
			assertEquals(PasswordGenerator.passwordLength, voter.getPassword().length());

		}

	}

}
