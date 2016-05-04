package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.VoteClosedList;
import es.uniovi.asw.model.VoteOpenList;
import es.uniovi.asw.model.VoteReferendum;

public class InsertPhysicalVotesP implements InsertPhysicalVotes {

	@Override
	public void insert(List<Vote> votes, PollingPlace pollingPlace) {
		insertOpenList(votes, pollingPlace);
		insertClosedList(votes, pollingPlace);
		insertReferendum(votes, pollingPlace);
	}

	private void insertOpenList(List<Vote> votes, PollingPlace pollingPlace) {
		if (!votes.isEmpty() && votes.get(0).getElection().isOpenList()) {
			VoteOpenList vote;
			for (Vote v:votes) {
				v.setPollingPlace(pollingPlace);
				vote = Repository.voteR.findByElectionAndCandidateAndPollingPlace(v.getElection(), ((VoteOpenList)v).getCandidate(), pollingPlace);
				if (vote == null)
					vote = (VoteOpenList) v;
				else
					vote.setNumVotes(vote.getNumVotes() + v.getNumVotes());
				vote.setReadyToRecount(true);
				Repository.voteR.save(vote);
			}
		}
	}
	
	private void insertClosedList(List<Vote> votes, PollingPlace pollingPlace) {
		if (!votes.isEmpty() && votes.get(0).getElection().isClosedList()) {
			VoteClosedList vote;
			for (Vote v:votes) {
				v.setPollingPlace(pollingPlace);
				vote = Repository.voteR.findByElectionAndCandidatureAndPollingPlace(v.getElection(), ((VoteClosedList)v).getCandidature(), pollingPlace);
				if (vote == null)
					vote = (VoteClosedList) v;
				else
					vote.setNumVotes(vote.getNumVotes() + v.getNumVotes());
				vote.setReadyToRecount(true);
				Repository.voteR.save(vote);
			}
		}
	}
	
	private void insertReferendum(List<Vote> votes, PollingPlace pollingPlace) {
		if (!votes.isEmpty() && votes.get(0).getElection().isReferendum()) {
			VoteReferendum vote = (VoteReferendum) votes.get(0);
			vote = Repository.voteR.findVoteReferendumByElectionAndPollingPlace(vote.getElection(), pollingPlace);
			if (vote != null) {
				vote.setYeses(((VoteReferendum) votes.get(0)).getYeses() + vote.getYeses());
				vote.setNoes(((VoteReferendum) votes.get(0)).getNoes() + vote.getNoes());
			}
			else
				vote = (VoteReferendum) votes.get(0);
			vote.setReadyToRecount(true);
			Repository.voteR.save(vote);
		}
	}
}
