package es.uniovi.asw.parser;

public class VoterInfo {

	private String name;
	private String NIF;
	private String email;
	private String pollingPlace;
	private int line;
	
	public VoterInfo(String name, String NIF, String email, String pollingPlace) {
		this.name = name;
		this.NIF = NIF;
		this.email = email;
		this.pollingPlace = pollingPlace;
	}

	public VoterInfo(String name, String NIF, String email, String pollingPlace, int line) {
		this.name = name;
		this.NIF = NIF;
		this.email = email;
		this.pollingPlace = pollingPlace;
		this.line = line;
	}

	public String getName() {
		return name;
	}

	public String getNIF() {
		return NIF;
	}

	public String getEmail() {
		return email;
	}

	public String getPollingPlace() {
		return pollingPlace;
	}
	
	public int getLine() {
		return line;
	}

	public boolean isEmpty() {
		return (name == null || name.isEmpty())
				&& (NIF == null || NIF.isEmpty())
				&& (email == null || email.isEmpty())
				&& (pollingPlace == null || pollingPlace.isEmpty());
	}

	@Override
	public String toString() {
		return "VoterInfo [name=" + name + ", NIF=" + NIF + ", email=" + email + ", pollingPlace=" + pollingPlace
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NIF == null) ? 0 : NIF.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pollingPlace == null) ? 0 : pollingPlace.hashCode());
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
		VoterInfo other = (VoterInfo) obj;
		if (NIF == null) {
			if (other.NIF != null)
				return false;
		} else if (!NIF.equals(other.NIF))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pollingPlace == null) {
			if (other.pollingPlace != null)
				return false;
		} else if (!pollingPlace.equals(other.pollingPlace))
			return false;
		return true;
	}

}
