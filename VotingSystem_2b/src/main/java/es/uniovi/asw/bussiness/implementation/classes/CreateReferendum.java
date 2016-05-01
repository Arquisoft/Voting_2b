package es.uniovi.asw.bussiness.implementation.classes;

import es.uniovi.asw.dbupdate.InsertElection;
import es.uniovi.asw.dbupdate.InsertRElection;
import es.uniovi.asw.dbupdate.repositories.Repository;
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
            new InsertRElection().insertReferendum(referendum);
             return true;
        } catch (Exception e) {
            return false;
        }

    }
}
