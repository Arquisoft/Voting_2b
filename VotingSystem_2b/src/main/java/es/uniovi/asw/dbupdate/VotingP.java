package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.*;

/**
 * Created by ignaciofernandezalvarez on 1/5/16.
 */
public class VotingP implements  Voting{





    private void saveTurnout(Voter v, Election election) {

        Turnout turnout = new Turnout();
        turnout.setElection(election);
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

    @Override
    public VoteClosedList votingClosedList(VoteClosedList voteClosedList, Voter v, ClosedList closedList) {

        if(hasAlreadyVoted(v,closedList)) return  null;

        VoteClosedList voteClosedList1 = Repository.voteR.save(voteClosedList);
        saveTurnout(v, closedList);


        return voteClosedList1;
    }

    @Override
    public VoteOpenList votingOpenList(VoteOpenList voteOpenList, Voter v, OpenList openList) {

        if(hasAlreadyVoted(v,openList)) return  null;

        VoteOpenList voteOpenList1 = Repository.voteR.save(voteOpenList);
        saveTurnout(v,openList);



        return voteOpenList1;
    }

    @Override
    public VoteReferendum votingReferendum(VoteReferendum voteReferendum, Voter v, Referendum referendum) {

        if(hasAlreadyVoted(v,referendum)) return  null;
        VoteReferendum voteReferendum1= Repository.voteR.save(voteReferendum);

        saveTurnout(v,referendum);

        return voteReferendum1;
    }
}

