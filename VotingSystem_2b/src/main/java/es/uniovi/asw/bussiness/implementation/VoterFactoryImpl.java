package es.uniovi.asw.bussiness.implementation;

import es.uniovi.asw.bussiness.VoterFactory;
import es.uniovi.asw.bussiness.implementation.classes.FindVoterByNif;
import es.uniovi.asw.bussiness.implementation.classes.LogIn;
import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.Voter;

/**
 * Created by ignaciofernandezalvarez on 16/4/16.
 */
public class VoterFactoryImpl implements VoterFactory {
    @Override
    public Election login(String NIF, String password, Voter v) {
        return new LogIn(NIF,password,v).logIn();
    }

    @Override
    public Voter findByNif(String nif) {
        return new FindVoterByNif(nif).find();
    }
}
