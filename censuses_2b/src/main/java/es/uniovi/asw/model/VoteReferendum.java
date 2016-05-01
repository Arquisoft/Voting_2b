package es.uniovi.asw.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "VotesReferendum")
public class VoteReferendum extends Vote {

	private int yeses;
	private int noes;
	
	public VoteReferendum() { }
	
	@Override
	public int getNumVotes() {
		return yeses + noes;
	}
	
	public int increaseYeses() {
		return yeses++;
	}
	
	public int increaseNoes() {
		return noes++;
	}

	public int getYeses() {
		return yeses;
	}

	public void setYeses(int yeses) {
		this.yeses = yeses;
	}

	public int getNoes() {
		return noes;
	}

	public void setNoes(int noes) {
		this.noes = noes;
	}

	@Override
	public String toString() {
		return "VoteReferendum [yeses=" + yeses + ", noes=" + noes + "]";
	}
	
}
