package es.uniovi.asw.loadPhysicalVotes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.dbupdate.Repository;
import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.VoteReferendum;

@Component("physicalVotes")
@Scope("view")
public class PhysicalVotesBean {

	private Election election;
	private List<PollingPlace> pollingPlaces;
	private List<Vote> votes;

	private PollingPlace selectedPollingPlace;
	private int totalVotes;
	private int totalVotesPP;
	private int totalTurnoutPP;

	@PostConstruct
	public void init() {
		totalVotes = 0;
		totalVotesPP = 0;
		totalTurnoutPP = 0;
		election = Repository.electionR.findActual();
		pollingPlaces = ((List<PollingPlace>) Repository.pollingPlaceR.findAll()).stream()
				.filter(p -> p.getVotes().isEmpty() ? true
						: p.getVotes().stream().filter(Vote::isReadyToRecount).count() == 0)
				.filter(p -> p.getVoters().size() > 0).collect(Collectors.toList());
		ConfigurePhysicalVotesBean.configure(this);
		if (pollingPlaces.isEmpty())
			votes = new ArrayList<>();
		if (!pollingPlaces.isEmpty()) {
			selectedPollingPlace = pollingPlaces.get(0);
			calculateTotalVotesPP();
		}
	}

	public Election getElection() {
		return election;
	}

	public List<PollingPlace> getPollingPlaces() {
		return pollingPlaces;
	}

	public PollingPlace getSelectedPollingPlace() {
		return selectedPollingPlace;
	}

	public void setSelectedPollingPlace(PollingPlace selectedPollingPlace) {
		this.selectedPollingPlace = selectedPollingPlace;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public int getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}

	public int getTotalVotesPP() {
		return totalVotesPP;
	}

	public void setTotalVotesPP(int totalVotesPP) {
		this.totalVotesPP = totalVotesPP;
	}

	public int getTotalTurnoutPP() {
		return totalTurnoutPP;
	}

	public void setTotalTurnoutPP(int totalTurnoutPP) {
		this.totalTurnoutPP = totalTurnoutPP;
	}

	public void calculateTotalVotesPP() {
		if (selectedPollingPlace != null) {
			setTotalTurnoutPP((int) election.getTurnout().stream()
					.filter(t -> t.getVoter().getPollingPlace().equals(selectedPollingPlace)).count()
					* election.getNumChoices());

			setTotalVotesPP(selectedPollingPlace.getVotes().stream().mapToInt(Vote::getNumVotes).sum());
		}
	}

	public void resetVotes() {
		totalVotes = 0;
		if (election.isReferendum()) {
			votes.stream().forEach(v -> {
				((VoteReferendum) v).setYeses(0);
				((VoteReferendum) v).setNoes(0);
			});
		} else
			votes.stream().forEach(v -> v.setNumVotes(0));
	}

	public void addVote() {
		totalVotes = votes.stream().mapToInt(Vote::getNumVotes).sum();
	}

	public void load() {
		new InsertPhysicalVotesR().insert(votes, selectedPollingPlace);
		resetVotes();
		init();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Votos almacenados correctamente"));
	}

}
