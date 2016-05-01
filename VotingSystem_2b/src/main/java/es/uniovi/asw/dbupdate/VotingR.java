package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.*;

/**
 * Created by ignaciofernandezalvarez on 1/5/16.
 */
public class VotingR implements  Voting {

    @Override
    public VoteClosedList votingClosedList(VoteClosedList voteClosedList, Voter v, ClosedList closedList) {
        return new VotingP().votingClosedList(voteClosedList,v,closedList);
    }

    @Override
    public VoteOpenList votingOpenList(VoteOpenList voteOpenList, Voter c, OpenList openList) {
        return new VotingP().votingOpenList(voteOpenList,c,openList);
    }

    @Override
    public VoteReferendum votingReferendum(VoteReferendum voteReferendum, Voter v, Referendum referendum) {
        return new VotingP().votingReferendum(voteReferendum,v,referendum);
    }
}
