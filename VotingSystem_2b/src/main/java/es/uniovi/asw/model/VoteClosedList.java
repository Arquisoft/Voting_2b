package es.uniovi.asw.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VotesClosedList")
public class VoteClosedList extends Vote {
	
	@ManyToOne
	private Candidature candidature;
	
	public VoteClosedList() {}

	public Candidature getCandidature() {
		return candidature;
	}

	public void setCandidature(Candidature candidature) {
		if (this.candidature != null)
			this.candidature._getVotes().remove(this);
			this.candidature = candidature;
		if (this.candidature != null)
			this.candidature._getVotes().add(this);
	}

	@Override
	public String toString() {
		return "VoteClosedList [candidature=" + candidature + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((candidature == null) ? 0 : candidature.hashCode());
		result = prime * result + ((getElection() == null) ? 0 : getElection().hashCode());
		result = prime * result + ((getPollingPlace() == null) ? 0 : getPollingPlace().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoteClosedList other = (VoteClosedList) obj;
		if (candidature == null) {
			if (other.candidature != null)
				return false;
		} else if (!candidature.equals(other.candidature))
			return false;
		if (getElection() == null) {
			if (other.getElection() != null)
				return false;
		} else if (!getElection().equals(other.getElection()))
			return false;
		if (getPollingPlace() == null) {
			if (other.getPollingPlace() != null)
				return false;
		} else if (!getPollingPlace().equals(other.getPollingPlace()))
			return false;
		return true;
	}
	
	
	
}
