package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Voter;
import es.uniovi.asw.reportGeneration.PasswordGenerator;

public class PasswordTest {

	private List<Voter> voters;

	@Before
	public void setUp() {

		this.voters = new ArrayList<Voter>();

		Voter voterA = new Voter("voterA", "a@a.com", "123456a", 12);
		Voter voterB = new Voter("voterB", "b@b.com", "asd", 12);
		Voter voterC = new Voter("voterC", "c@c.com", "asd", 12);
		Voter voterD = new Voter("voterD", "d@d.com", "asd", 12);

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
