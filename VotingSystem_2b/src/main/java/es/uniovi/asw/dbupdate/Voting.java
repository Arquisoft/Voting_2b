package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.*;
import java.util.List;

/**
 * Created by ignaciofernandezalvarez on 1/5/16.
 */
public interface Voting {


    VoteClosedList votingClosedList(String partyVoted,Voter v,ClosedList closedList);

    VoteOpenList votingOpenList(List<Candidate> candidates,Voter c, OpenList openList);

    VoteReferendum votingReferendum(String selectedValue,Voter v, Referendum referendum);


}
