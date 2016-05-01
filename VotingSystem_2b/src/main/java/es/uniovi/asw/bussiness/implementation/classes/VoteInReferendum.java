package es.uniovi.asw.bussiness.implementation.classes;

import es.uniovi.asw.dbupdate.Repository;
import es.uniovi.asw.dbupdate.VotingR;
import es.uniovi.asw.model.Referendum;
import es.uniovi.asw.model.VoteReferendum;
import es.uniovi.asw.model.Voter;

/**
 * Created by ignaciofernandezalvarez on 16/4/16.
 */
public class VoteInReferendum {

    private Referendum referendum;

    private String selectedValue;

    private Voter v;

    public VoteInReferendum(Referendum referendum, String selectedValue, Voter v) {

        this.referendum = referendum;
        this.selectedValue = selectedValue;
        this.v = v;

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


        VoteReferendum vr = new VotingR().votingReferendum(voteReferendum, v, referendum);

        if (vr == null) {
            return false;
        }

        return true;

    }


}
