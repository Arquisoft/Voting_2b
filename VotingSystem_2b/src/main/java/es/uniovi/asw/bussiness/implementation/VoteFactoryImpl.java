package es.uniovi.asw.bussiness.implementation;

import es.uniovi.asw.bussiness.VoteFactory;
import es.uniovi.asw.bussiness.implementation.classes.VotarEnCerradas;
import es.uniovi.asw.bussiness.implementation.classes.VoteInReferendum;
import es.uniovi.asw.bussiness.implementation.classes.VoteInAbiertas;
import es.uniovi.asw.model.*;

import java.util.List;

/**
 * Created by ignaciofernandezalvarez on 16/4/16.
 */
public class VoteFactoryImpl implements VoteFactory {


    @Override
    public boolean voteInReferendum(Referendum referendum,String selectedValue, Voter v) {
        return new VoteInReferendum(referendum,selectedValue,v).vote();
    }

    @Override
    public boolean voteInCerradas(ClosedList closedList, String partyVoted, Voter v) {
        return new VotarEnCerradas(closedList,partyVoted,v).votar();
    }

    @Override
    public boolean voteInAbiertas(OpenList openList, List<Candidate> candidates, Voter voter) {
        return new VoteInAbiertas(openList,candidates,voter).vote();
    }
}
