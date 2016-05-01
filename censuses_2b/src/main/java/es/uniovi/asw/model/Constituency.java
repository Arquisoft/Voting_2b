package es.uniovi.asw.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Constituency {

	@Id @GeneratedValue
	private Long id;
	@Column(unique = true, nullable = false)
	private String name;
	
	@ManyToOne
	private Region region;
	@OneToMany(mappedBy="constituency", fetch = FetchType.EAGER)
	private Set<PollingPlace> pollingPlaces = new HashSet<>();
	
	public Constituency() { }
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		if (this.region != null)
			this.region._getConstituencies().remove(this);
			this.region = region;
		if (this.region != null)
			this.region._getConstituencies().add(this);
	}

	public Set<PollingPlace> getPollingPlaces() {
		return new HashSet<>(pollingPlaces);
	}
	
	Set<PollingPlace> _getPollingPlaces() {
		return pollingPlaces;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Constituency [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
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
		Constituency other = (Constituency) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		return true;
	}
	
}
