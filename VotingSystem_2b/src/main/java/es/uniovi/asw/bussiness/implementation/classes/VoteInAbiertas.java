package es.uniovi.asw.bussiness.implementation.classes;

import es.uniovi.asw.dbupdate.Repository;
import es.uniovi.asw.dbupdate.VotingR;
import es.uniovi.asw.model.*;

import java.util.List;

/**
 * Created by Ignacio Fernandez on 21/04/2016.
 */
public class VoteInAbiertas {

    private OpenList openlist;
    private List<Candidate> candidates;
    private Voter voter;

    public VoteInAbiertas(OpenList openList, List<Candidate> candidates, Voter voter) {

        this.openlist = openList;
        this.candidates = candidates;
        this.voter = voter;
    }

    public boolean vote() {

        try {

            for (Candidate candidate: candidates) {


                VoteOpenList voteOpenList = Repository.voteR.findByElectionAndCandidateAndPollingPlace(openlist, candidate, voter.getPollingPlace());

                if (voteOpenList == null) {

                    voteOpenList = new VoteOpenList();
                    voteOpenList.setPollingPlace(voter.getPollingPlace());
                    voteOpenList.setNumVotes(1);
                    voteOpenList.setElection(openlist);
                    voteOpenList.setCandidate(candidate);

                } else {

                    voteOpenList.setNumVotes(voteOpenList.getNumVotes() + 1);

                }


                VoteOpenList v =  new VotingR().votingOpenList(voteOpenList,voter,openlist);
            }



            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
