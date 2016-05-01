package es.uniovi.asw.bussiness.implementation.classes;

import es.uniovi.asw.dbupdate.Repository;
import es.uniovi.asw.dbupdate.VotingR;
import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.VoteClosedList;
import es.uniovi.asw.model.Voter;

/**
 * Created by ignaciofernandezalvarez on 20/4/16.
 */
public class VotarEnCerradas {

    private ClosedList closedList;
    private String partyVoted;
    private Voter v;

    public VotarEnCerradas(ClosedList closedList, String partyVoted, Voter v) {
        this.closedList = closedList;
        this.partyVoted = partyVoted;
        this.v = v;
    }


    public boolean votar() {

        try {

            Candidature c = Repository.candidatureR.findByName(partyVoted);
            VoteClosedList voteClosedList = Repository.voteR.findByElectionAndCandidatureAndPollingPlace(closedList, c, v.getPollingPlace());

            if (voteClosedList == null) {

                voteClosedList = new VoteClosedList();
                voteClosedList.setPollingPlace(v.getPollingPlace());
                voteClosedList.setNumVotes(1);
                voteClosedList.setElection(closedList);
                voteClosedList.setCandidature(c);

            } else {

                voteClosedList.setNumVotes(voteClosedList.getNumVotes() + 1);

            }

            VoteClosedList vc = new VotingR().votingClosedList(voteClosedList, v, closedList);


            return true;
        } catch (Exception e) {

            return false;
        }
    }
}
