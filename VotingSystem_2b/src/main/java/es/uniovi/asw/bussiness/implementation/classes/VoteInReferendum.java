package es.uniovi.asw.bussiness.implementation.classes;

import es.uniovi.asw.dbupdate.Repository;
import es.uniovi.asw.model.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by ignaciofernandezalvarez on 16/4/16.
 */
public class VoteInReferendum {

    private Referendum referendum;

    private String selectedValue;

    private Voter v;

    public VoteInReferendum(Referendum referendum, String selectedValue, Voter v) {

        this.referendum=referendum;
        this.selectedValue=selectedValue;
        this.v=v;

    }


    public boolean vote() {

        VoteReferendum voteReferendum = Repository.voteR.findVoteReferendumByElectionAndPollingPlace(referendum, v.getPollingPlace());

        System.out.println(voteReferendum);

        if (voteReferendum == null) {


            voteReferendum = new VoteReferendum();
            voteReferendum.setElection(referendum);
            voteReferendum.setPollingPlace(v.getPollingPlace());


        }

        if ("Si".equals(selectedValue)) {


            voteReferendum.setYeses(voteReferendum.getYeses() + 1);

        } else {
            voteReferendum.setNoes(voteReferendum.getNoes() + 1);

        }

        if (hasAlreadyVoted(v, referendum)) {

            return false;
        }
        System.out.println(v);


        Repository.voteR.save(voteReferendum);


        saveTurnout(v,referendum);


        return true;

    }


    private void saveTurnout(Voter v,Referendum referendum) {

        Turnout turnout = new Turnout();
        turnout.setElection(referendum);
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

}
