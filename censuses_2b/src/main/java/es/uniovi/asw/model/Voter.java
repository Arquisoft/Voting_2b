package es.uniovi.asw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Nauce Lopez
 *
 */
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
	private int pollingPlace;
	@Column(nullable = false)
	private String password;
	
	protected Voter() {}

	public Voter(String name, String nif, String email, int pollingPlace) {
		this.name = name;
		this.nif = nif;
		this.email = email;
		this.pollingPlace = pollingPlace;
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

	public int getPollingPlace() {
		return pollingPlace;
	}
	
	public String getPassword() {
		return password;
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
