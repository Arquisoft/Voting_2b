package es.uniovi.asw.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Region {

	@Id @GeneratedValue
	private Long id;
	@Column(unique = true, nullable = false)
	private String name;
	
	@OneToMany(mappedBy="region", fetch = FetchType.EAGER)
	private Set<Constituency> constituencies = new HashSet<>();
	
	public Region() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Constituency> getConstituencies() {
		return new HashSet<>(constituencies);
	}
	
	Set<Constituency> _getConstituencies() {
		return constituencies;
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", name=" + name + "]";
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
		Region other = (Region) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public boolean isEmpty() {
		return (getName() == null );
	}
	
}
