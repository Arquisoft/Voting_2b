package es.uniovi.asw.model.types;

import java.io.Serializable;

public class TurnoutKey implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Long election;
	Long voter;
	
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
		TurnoutKey other = (TurnoutKey) obj;
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
