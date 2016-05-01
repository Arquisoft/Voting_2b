package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.*;

/**
 * Created by ignaciofernandezalvarez on 1/5/16.
 */
public interface Voting {


    VoteClosedList votingClosedList(VoteClosedList voteClosedList,Voter v,ClosedList closedList);

    VoteOpenList votingOpenList(VoteOpenList voteOpenList, Voter c, OpenList openList);

    VoteReferendum votingReferendum(VoteReferendum voteReferendum,Voter v, Referendum referendum);


}
