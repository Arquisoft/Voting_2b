package es.uniovi.asw.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(es.uniovi.asw.model.types.TurnoutKey.class)
public class Turnout {

	@Id
	@ManyToOne
	private Election election;
	@Id
	@ManyToOne
	private Voter voter;
	
	public Turnout() {}
	
	public Turnout(Election election, Voter voter) {
		this.election = election;
		this.voter = voter;
		
		if (this.election != null && this.voter != null) {
			this.election._getTurnout().add(this);
			this.voter._getTurnout().add(this);
		}
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		if (this.election != null)
			this.election._getTurnout().remove(this);
			this.election = election;
		if (this.election != null)
			this.election._getTurnout().add(this);
	}

	public Voter getVoter() {
		return voter;
	}

	public void setVoter(Voter voter) {
		if (this.voter != null)
			this.voter._getTurnout().remove(this);
			this.voter = voter;
		if (this.voter != null)
			this.voter._getTurnout().add(this);
	}

	@Override
	public String toString() {
		return "Turnout [election=" + election + ", voter=" + voter + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((election == null) ? 0 : election.hashCode());
		result = prime * result + ((voter == null) ? 0 : voter.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turnout other = (Turnout) obj;
		if (election == null) {
			if (other.election != null)
				return false;
		} else if (!election.equals(other.election))
			return false;
		if (voter == null) {
			if (other.voter != null)
				return false;
		} else if (!voter.equals(other.voter))
			return false;
		return true;
	}
	
}
