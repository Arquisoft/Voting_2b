package es.uniovi.asw.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Candidature {

	@Id @GeneratedValue
	private Long id;
	@Column(unique = true, nullable = false)
	private String name;
	private String initial;
	private String description;
	
	@OneToMany(mappedBy="candidature", fetch = FetchType.EAGER)
	private Set<Candidate> candidates = new HashSet<>();
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<ClosedList> elections = new HashSet<>();
	@OneToMany(mappedBy="candidature", fetch = FetchType.EAGER)
	private Set<VoteClosedList> votes = new HashSet<>();
	
	public Candidature() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Candidate> getCandidates() {
		return new HashSet<>(candidates);
	}
	
	Set<Candidate> _getCandidates() {
		return candidates;
	}

	public Set<ClosedList> getElections() {
		return new HashSet<>(elections);
	}
	
	Set<ClosedList> _getElections() {
		return elections;
	}
	
	public void addElection(ClosedList election) {
		if (election != null) {
			election._getCandidatures().add(this);
			elections.add(election);
		}
	}
	
	public void removeElection(ClosedList election) {
		if (election != null) {
			election._getCandidatures().remove(this);
			elections.remove(election);
		}
	}

	public Set<VoteClosedList> getVotes() {
		return new HashSet<>(votes);
	}
	
	Set<VoteClosedList> _getVotes() {
		return votes;
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Candidature [id=" + id + ", name=" + name + ", initial=" + initial + ", description=" + description
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Candidature other = (Candidature) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public boolean isEmpty() {
		return (getName() == null )
				&& (getInitial() == null )
				&& (getDescription() == null);
	}

}
