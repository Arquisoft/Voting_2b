package es.uniovi.asw.bussiness.implementation.classes;

import es.uniovi.asw.dbupdate.Repository;
import es.uniovi.asw.model.Referendum;

/**
 * Created by ignaciofernandezalvarez on 16/4/16.
 */
public class CreateReferendum {


    private Referendum referendum;

    public CreateReferendum(Referendum referendum) {
        this.referendum = referendum;
    }


    public boolean create() {

        try {
            Repository.electionR.save(referendum);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
