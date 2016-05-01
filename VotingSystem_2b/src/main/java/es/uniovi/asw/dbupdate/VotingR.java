package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.VoteClosedList;
import es.uniovi.asw.model.VoteOpenList;
import es.uniovi.asw.model.VoteReferendum;

/**
 * Created by ignaciofernandezalvarez on 1/5/16.
 */
public class VotingR implements  Voting {
    @Override
    public VoteClosedList votingClosedList(VoteClosedList closedList) {
        return new VotingP().votingClosedList(closedList);
    }

    @Override
    public VoteOpenList votingOpenList(VoteOpenList openList) {
        return new VotingP().votingOpenList(openList);
    }

    @Override
    public VoteReferendum votingReferendum(VoteReferendum voteReferendum) {
        return new VotingP().votingReferendum(voteReferendum);
    }
}
