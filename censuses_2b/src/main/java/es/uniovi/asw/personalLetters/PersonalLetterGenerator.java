package es.uniovi.asw.personalLetters;

import java.util.HashSet;
import java.util.List;

import es.uniovi.asw.model.Voter;

public class PersonalLetterGenerator {
	
	private static PersonalLetterWriter pdfWriter = new PDFLetterWriter();
	private static PersonalLetterWriter plainWriter = new PlainTextLetter();
	private static PersonalLetterWriter wordWriter = new WordLetterWriter();
	
	private HashSet<PersonalLetterWriter> writers;

	private List<Voter> voters;

	public PersonalLetterGenerator(List<Voter> voters, String... types) {
		this.writers = new HashSet<PersonalLetterWriter>();
		this.voters = voters;
		chooseWritters(types);
	}
	
	public PersonalLetterGenerator(List<Voter> voters, HashSet<PersonalLetterWriter> writer) {
		this.writers = writer;
		this.voters = voters;
	}

	public PersonalLetterGenerator(List<Voter> voters) {
		this.writers = new HashSet<PersonalLetterWriter>();
		this.voters = voters;
	}

	public PersonalLetterGenerator(String... types) {
		this.writers = new HashSet<PersonalLetterWriter>();
		chooseWritters(types);
	}
	
	public void setVoters(List<Voter> voters) {
		this.voters = voters;
	}

	public void chooseWritters(String... types) {

		for (String type:types) {
			
			if (type.contains("t")) {
				writers.add(plainWriter);
			} else if (type.contains("w")) {
				writers.add(wordWriter);
			} else if (type.contains("p")) {
				writers.add(pdfWriter);
			}
		}

	}

	public void writeAllLetters() {
		for (Voter voter : voters) {
			for (PersonalLetterWriter writer:writers)
				writer.WriteLetter(voter);
		}
	}

}
