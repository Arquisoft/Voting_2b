package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.*;

/**
 * Created by ignaciofernandezalvarez on 1/5/16.
 */
public interface Voting {


    VoteClosedList votingClosedList(VoteClosedList closedList);

    VoteOpenList votingOpenList(VoteOpenList openList);

    VoteReferendum votingReferendum(VoteReferendum voteReferendum);


}
