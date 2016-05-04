package es.uniovi.asw.bussiness.implementation.classes;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.model.Voter;

/**
 * Created by ignaciofernandezalvarez on 16/4/16.
 */
public class FindVoterByNif {

    private String nif;

    public FindVoterByNif(String nif) {
        this.nif = nif;
    }

    public Voter find() {

        return Repository.voterR.findByNif(nif);
    }
}
