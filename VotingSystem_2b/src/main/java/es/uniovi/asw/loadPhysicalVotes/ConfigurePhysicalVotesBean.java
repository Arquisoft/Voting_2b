package es.uniovi.asw.loadPhysicalVotes;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.model.Candidate;
import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.OpenList;
import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.VoteClosedList;
import es.uniovi.asw.model.VoteOpenList;
import es.uniovi.asw.model.VoteReferendum;

public class ConfigurePhysicalVotesBean {

	public static void configure(PhysicalVotesBean bean) {
		configureReferendum(bean);
		configureClosedList(bean);
		configureOpenList(bean);
	}

	private static void configureOpenList(PhysicalVotesBean bean) {
		if (bean.getElection().isOpenList()) {
			List<Vote> votes = new ArrayList<>();

			VoteOpenList vote;
			for (Candidate c : ((OpenList) bean.getElection()).getCandidates()) {
				vote = new VoteOpenList();
				vote.setElection(bean.getElection());
				vote.setCandidate(c);
				votes.add(vote);
			}
			bean.setVotes(votes);
		}
	}

	private static void configureClosedList(PhysicalVotesBean bean) {
		if (bean.getElection().isClosedList()) {
			List<Vote> votes = new ArrayList<>();

			VoteClosedList vote;
			for (Candidature c : ((ClosedList) bean.getElection()).getCandidatures()) {
				vote = new VoteClosedList();
				vote.setElection(bean.getElection());
				vote.setCandidature(c);
				votes.add(vote);
			}
			bean.setVotes(votes);
		}
	}

	private static void configureReferendum(PhysicalVotesBean bean) {
		if (bean.getElection().isReferendum()) {
			List<Vote> votes = new ArrayList<>();

			VoteReferendum vote = new VoteReferendum();
			vote.setElection(bean.getElection());
			if (!bean.getPollingPlaces().isEmpty())
				vote.setPollingPlace(bean.getPollingPlaces().get(0));
			votes.add(vote);
			bean.setVotes(votes);
		}

	}
}
