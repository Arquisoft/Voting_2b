package es.uniovi.asw.dbupdate;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.OpenList;
import es.uniovi.asw.model.Referendum;

/**
 * Created by ignaciofernandezalvarez on 1/5/16.
 */
public class InsertRElection implements InsertElection {


    @Override
    public Referendum insertReferendum(Referendum referendum) {
        return Repository.electionR.save(referendum);
    }

    @Override
    public OpenList insertOpenList(OpenList openList) {
        return Repository.electionR.save(openList);
    }

    @Override
    public ClosedList insertClosedList(ClosedList closedList) {
        return Repository.electionR.save(closedList);
    }
}
