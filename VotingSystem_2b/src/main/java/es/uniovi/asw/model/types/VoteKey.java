package es.uniovi.asw.model.types;

import java.io.Serializable;

public class VoteKey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	Long election;
	Long pollingPlace;
	
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
		VoteKey other = (VoteKey) obj;
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
