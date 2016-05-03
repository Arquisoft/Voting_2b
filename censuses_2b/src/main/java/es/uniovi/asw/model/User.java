package es.uniovi.asw.model;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	
	protected User() {}

	public User(String name, String NIF, String email, int pollingPlace, String password) {
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}


	
	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	
}
