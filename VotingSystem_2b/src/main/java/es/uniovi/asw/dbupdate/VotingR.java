package es.uniovi.asw.dbupdate;

import java.util.List;
import es.uniovi.asw.onlineVoting.VotingP;
import es.uniovi.asw.model.*;

/**
 * Created by ignaciofernandezalvarez on 1/5/16.
 */
public class VotingR implements  Voting {

    @Override
    public VoteClosedList votingClosedList(String partyVoted, Voter v, ClosedList closedList) {
        return new VotingP().votingClosedList(partyVoted,v,closedList);
    }

    @Override
    public VoteOpenList votingOpenList(List<Candidate> candidates, Voter c, OpenList openList) {
        return new VotingP().votingOpenList(candidates,c,openList);
    }

    @Override
    public VoteReferendum votingReferendum(String selectedValue, Voter v, Referendum referendum) {
        return new VotingP().votingReferendum(selectedValue,v,referendum);
    }
}
