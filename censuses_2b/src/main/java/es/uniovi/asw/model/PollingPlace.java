package es.uniovi.asw.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PollingPlace {
	
	@Id
	private Long id;
	
	@ManyToOne
	private Constituency constituency;
	@OneToMany(mappedBy="pollingPlace", fetch = FetchType.EAGER)
	private Set<Vote> votes = new HashSet<>();
	@OneToMany(mappedBy="pollingPlace", fetch = FetchType.EAGER)
	private Set<Voter> voters = new HashSet<>();
	
	public PollingPlace() {}

	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		if (this.constituency != null)
			this.constituency._getPollingPlaces().remove(this);
			this.constituency = constituency;
		if (this.constituency != null)
			this.constituency._getPollingPlaces().add(this);
	}

	public Set<Vote> getVotes() {
		return new HashSet<>(votes);
	}
	
	Set<Vote> _getVotes() {
		return votes;
	}
	
	public Set<Voter> getVoters() {
		return new HashSet<>(voters);
	}
	
	Set<Voter> _getVoters() {
		return voters;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PollingPlace [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PollingPlace other = (PollingPlace) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
