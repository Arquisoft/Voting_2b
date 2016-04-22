package es.uniovi.asw.reportGeneration;

import java.util.List;
import java.util.Random;

import es.uniovi.asw.model.Voter;

public class PasswordGenerator {

	public final static String pasworsPossibilities = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public final static int passwordLength = 8;

	private static String generatePassword() {

		String password = "";

		Random randGenerator = new Random();

		for (int i = 0; i < passwordLength; i++) {

			password += pasworsPossibilities.charAt(randGenerator.nextInt(pasworsPossibilities.length()));

		}
		return password;

	}

	public static void generatePasswords(List<Voter> voters) {

		for (Voter voter : voters) {
			voter.setPassword(generatePassword());
		}

	}
	
	public static void generatePasswords(Voter voter) {
		voter.setPassword(generatePassword());
	}

}
