package es.uniovi.asw.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VotesOpenList")
public class VoteOpenList extends Vote {
	
	@ManyToOne
	private Candidate candidate;
	
	public VoteOpenList() {}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		if (this.candidate != null)
			this.candidate._getVotes().remove(this);
			this.candidate = candidate;
		if (this.candidate != null)
			this.candidate._getVotes().add(this);
	}

	@Override
	public String toString() {
		return "VoteOpenList [candidate=" + candidate + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((candidate == null) ? 0 : candidate.hashCode());
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
		VoteOpenList other = (VoteOpenList) obj;
		if (candidate == null) {
			if (other.candidate != null)
				return false;
		} else if (!candidate.equals(other.candidate))
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
