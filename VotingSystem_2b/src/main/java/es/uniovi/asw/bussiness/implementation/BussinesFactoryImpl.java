package es.uniovi.asw.bussiness.implementation;

import es.uniovi.asw.bussiness.*;

/**
 * Created by ignaciofernandezalvarez on 14/4/16.
 */
public class BussinesFactoryImpl implements BussinesFactory {





    @Override
    public VoterFactory createVoterFactory() {
        return new VoterFactoryImpl();
    }

    @Override
    public PollingPlaceFactory createPollingPlace() {
        return new PollinPlaceFactoryImpl();
    }
}
