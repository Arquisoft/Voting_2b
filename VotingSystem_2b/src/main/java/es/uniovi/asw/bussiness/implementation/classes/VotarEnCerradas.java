package es.uniovi.asw.bussiness.implementation.classes;

import es.uniovi.asw.dbupdate.Repository;
import es.uniovi.asw.model.*;

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

    private void saveTurnout(Voter v, ClosedList closedList) {

        Turnout turnout = new Turnout();
        turnout.setElection(closedList);
        turnout.setVoter(v);
        Repository.turnoutR.merge(turnout);
    }


    private boolean hasAlreadyVoted(Voter v, Election election) {


        Turnout t = Repository.turnoutR.findByVoterAndElection(v, election);

        if (t == null) {

            return false;
        }
        return true;

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

            Repository.voteR.save(voteClosedList);


            saveTurnout(v, closedList);


            return true;
        } catch (Exception e) {

            return false;
        }
    }
}
