package es.uniovi.asw.model;

import javax.persistence.Entity;

@Entity
public class Referendum extends Election {
	
	private String question;
	
	public Referendum() {}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Referendum [toString()=" + super.toString() + ", question=" + question + "]";
	}
	
}
