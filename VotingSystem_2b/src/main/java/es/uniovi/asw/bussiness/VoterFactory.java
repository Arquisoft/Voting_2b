package es.uniovi.asw.bussiness;

import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.Voter;

/**
 * Created by ignaciofernandezalvarez on 16/4/16.
 */
public interface VoterFactory {

    Election login(String NIF, String password, Voter v);

    Voter findByNif(String nif);

}
