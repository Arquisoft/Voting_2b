package es.uniovi.asw.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class ClosedList extends Election {
	
	public ClosedList() {}
	
	@ManyToMany(mappedBy="elections", fetch = FetchType.EAGER)
	private Set<Candidature> candidatures = new HashSet<>();

	public Set<Candidature> getCandidatures() {
		return new HashSet<>(candidatures);
	}
	
	Set<Candidature> _getCandidatures() {
		return candidatures;
	}
	
	public void addCandidature(Candidature candidature) {
		if (candidature != null) {
			candidature._getElections().add(this);
			candidatures.add(candidature);
		}
	}
	
	public void removeCandidature(Candidature candidature) {
		if (candidature != null) {
			candidature._getElections().remove(this);
			candidatures.remove(candidature);
		}
	}
	
}
