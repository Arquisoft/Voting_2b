package es.uniovi.asw.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Candidate {

	@Id @GeneratedValue
	private Long id;
	@Column(unique = true)
	private String dni;
	private String name;
	private String surname;
	
	@ManyToMany (fetch = FetchType.EAGER)
	private Set<OpenList> elections = new HashSet<>();
	@ManyToOne
	private Candidature candidature;
	@OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER)
	private Set<VoteOpenList> votes = new HashSet<>();
	
	public Candidate() {}
	
	public String getDNI() {
		return dni;
	}

	public void setDNI(String dNI) {
		dni = dNI;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Set<OpenList> getElections() {
		return new HashSet<>(elections);
	}
	
	Set<OpenList> _getElections() {
		return elections;
	}
	
	public void addElection(OpenList election) {
		if (election != null) {
			election._getCandidates().add(this);
			elections.add(election);
		}
	}
	
	public void removeElection(OpenList election) {
		if (election != null) {
			election._getCandidates().remove(this);
			elections.remove(election);
		}
	}

	public Candidature getCandidature() {
		return candidature;
	}

	public void setCandidature(Candidature candidature) {
		if (this.candidature != null)
			this.candidature._getCandidates().remove(this);
			this.candidature = candidature;
		if (this.candidature != null)
			this.candidature._getCandidates().add(this);
	}

	public Set<VoteOpenList> getVotes() {
		return new HashSet<>(votes);
	}
	
	Set<VoteOpenList> _getVotes() {
		return votes;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Candidate other = (Candidate) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}

	
}
