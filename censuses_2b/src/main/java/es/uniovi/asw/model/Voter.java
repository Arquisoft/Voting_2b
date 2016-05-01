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
import javax.persistence.Table;

@Entity
@Table(name = "Census")
public class Voter {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String nif;
	@Column(unique = true, nullable = false)
	private String email;
	@ManyToOne
	private PollingPlace pollingPlace;
	@Column(nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "voter", fetch = FetchType.EAGER)
	private Set<Turnout> turnout = new HashSet<>();
	
	public Voter() {}

	public Voter(String name, String nif, String email) {
		this.name = name;
		this.nif = nif;
		this.email = email;
	}

	public Voter(String name, String nif, String email, PollingPlace pollingPlace) {
		super();
		this.name = name;
		this.nif = nif;
		this.email = email;
		this.pollingPlace = pollingPlace;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTurnout(Set<Turnout> turnout) {
        this.turnout = turnout;
    }

    public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getNif() {
		return nif;
	}

	public void setPollingPlace(PollingPlace pollingPlace) {
		if (this.pollingPlace != null)
			this.pollingPlace._getVoters().remove(this);
			this.pollingPlace = pollingPlace;
		if (this.pollingPlace != null)
			this.pollingPlace._getVoters().add(this);
	}

	public PollingPlace getPollingPlace() {
		return pollingPlace;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Set<Turnout> getTurnout() {
		return new HashSet<>(turnout);
	}
	
	Set<Turnout> _getTurnout() {
		return turnout;
	}

	@Override
	public String toString() {
		return "Voter [name=" + name + ", email=" + email + ", NIF=" + nif + ", pollingPlace=" + pollingPlace
				+ ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
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
		Voter other = (Voter) obj;
		if (nif == null) {
			if (other.nif != null)
				return false;
		} else if (!nif.equals(other.nif))
			return false;
		return true;
	}
	
}
