package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.VoteClosedList;
import es.uniovi.asw.model.VoteOpenList;
import es.uniovi.asw.model.VoteReferendum;

/**
 * Created by ignaciofernandezalvarez on 1/5/16.
 */
public class VotingP implements  Voting{
    @Override
    public VoteClosedList votingClosedList(VoteClosedList closedList) {
        return Repository.voteR.save(closedList);
    }

    @Override
    public VoteOpenList votingOpenList(VoteOpenList openList) {
        return Repository.voteR.save(openList);
    }

    @Override
    public VoteReferendum votingReferendum(VoteReferendum voteReferendum) {
        return Repository.voteR.save(voteReferendum);
    }
}

