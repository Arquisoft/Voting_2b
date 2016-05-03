package es.uniovi.asw.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Election {

	@Id @GeneratedValue
	private Long id;
	@Column(unique = true)
	private String name;
	private Date startDate;
	private Date expiryDate;
	private String instructions;
	private int numChoices = 1;
	
	@OneToMany(mappedBy = "election", fetch = FetchType.EAGER)
	private Set<Vote> votes = new HashSet<>();
	@OneToMany(mappedBy = "election", fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	private Set<Turnout> turnout = new HashSet<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	public int getNumChoices() {
		return numChoices;
	}

	public void setNumChoices(int numChoices) {
		this.numChoices = numChoices;
	}


	public Set<Vote> getVotes() {
		return new HashSet<>(votes);
	}
	
	Set<Vote> _getVotes() {
		return votes;
	}

	public Set<Turnout> getTurnout() {
		return new HashSet<>(turnout);
	}
	
	Set<Turnout> _getTurnout() {
		return turnout;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Election [id=" + id + ", name=" + name + ", startDate=" + startDate + ", expiryDate=" + expiryDate
				+ ", instructions=" + instructions + "]";
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
		Election other = (Election) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public boolean isReferendum() {
		return this instanceof Referendum;
	}
	
	public boolean isClosedList() {
		return this instanceof ClosedList;
	}
	
	public boolean isOpenList() {
		return this instanceof OpenList;
	}

	public int countVotes(){
		int count=0;
		for(Vote voto:votes){
			count+=voto.getNumVotes();
		}
		return count;
	}
}
