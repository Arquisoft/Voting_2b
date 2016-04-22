package es.uniovi.asw.bussiness.implementation;

import es.uniovi.asw.bussiness.*;

/**
 * Created by ignaciofernandezalvarez on 14/4/16.
 */
public class BussinesFactoryImpl implements BussinesFactory {


    @Override
    public ElectionFactory createElectionFactory() {
        return new ElectionFactoryImpl();
    }

    @Override
    public VoteFactory createVoteFactory() {
        return new VoteFactoryImpl();
    }

    @Override
    public VoterFactory createVoterFactory() {
        return new VoterFactoryImpl();
    }

    @Override
    public PollingPlaceFactory createPollingPlace() {
        return new PollinPlaceFactoryImpl();
    }
}
