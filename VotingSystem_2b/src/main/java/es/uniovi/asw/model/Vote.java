package es.uniovi.asw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Vote {
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@ManyToOne
	private Election election;
	@ManyToOne
	private PollingPlace pollingPlace;
	
	private int numVotes;
	private boolean readyToRecount;
	
	public Vote() {}

	public int getNumVotes() {
		return numVotes;
	}

	public void setNumVotes(int numVotes) {
		this.numVotes = numVotes;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		if (this.election != null)
			this.election._getVotes().remove(this);
			this.election = election;
		if (this.election != null)
			this.election._getVotes().add(this);
	}

	public PollingPlace getPollingPlace() {
		return pollingPlace;
	}

	public void setPollingPlace(PollingPlace pollingPlace) {
		if (this.pollingPlace != null)
			this.pollingPlace._getVotes().remove(this);
			this.pollingPlace = pollingPlace;
		if (this.pollingPlace != null)
			this.pollingPlace._getVotes().add(this);
	}

	public boolean isReadyToRecount() {
		return readyToRecount;
	}

	public void setReadyToRecount(boolean readyToRecount) {
		this.readyToRecount = readyToRecount;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", election=" + election + ", pollingPlace=" + pollingPlace + ", numVotes=" + numVotes
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((election == null) ? 0 : election.hashCode());
		result = prime * result + ((pollingPlace == null) ? 0 : pollingPlace.hashCode());
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
		Vote other = (Vote) obj;
		if (election == null) {
			if (other.election != null)
				return false;
		} else if (!election.equals(other.election))
			return false;
		if (pollingPlace == null) {
			if (other.pollingPlace != null)
				return false;
		} else if (!pollingPlace.equals(other.pollingPlace))
			return false;
		return true;
	}

	
}
