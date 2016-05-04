package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.*;
import java.util.List;

public interface InsertOnlineVotes {

    VoteClosedList votingClosedList(String partyVoted, Voter v,ClosedList closedList);
    VoteOpenList votingOpenList(List<Candidate> candidates, Voter c, OpenList openList);
    VoteReferendum votingReferendum(String selectedValue, Voter v, Referendum referendum);

}
