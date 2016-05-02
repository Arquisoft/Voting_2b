package es.uniovi.asw.onlinevoting;

import java.util.List;

import es.uniovi.asw.dbupdate.InsertOnlineVotes;
import es.uniovi.asw.dbupdate.InsertOnlineVotesP;
import es.uniovi.asw.model.Candidate;
import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.OpenList;
import es.uniovi.asw.model.Referendum;
import es.uniovi.asw.model.VoteClosedList;
import es.uniovi.asw.model.VoteOpenList;
import es.uniovi.asw.model.VoteReferendum;
import es.uniovi.asw.model.Voter;

public class InsertOnlineVotesR implements  InsertOnlineVotes {

    @Override
    public VoteClosedList votingClosedList(String partyVoted, Voter v, ClosedList closedList) {
        return new InsertOnlineVotesP().votingClosedList(partyVoted,v,closedList);
    }

    @Override
    public VoteOpenList votingOpenList(List<Candidate> candidates, Voter c, OpenList openList) {
        return new InsertOnlineVotesP().votingOpenList(candidates,c,openList);
    }

    @Override
    public VoteReferendum votingReferendum(String selectedValue, Voter v, Referendum referendum) {
        return new InsertOnlineVotesP().votingReferendum(selectedValue,v,referendum);
    }
}
