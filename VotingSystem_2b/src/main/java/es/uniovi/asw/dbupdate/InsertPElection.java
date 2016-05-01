package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.OpenList;
import es.uniovi.asw.model.Referendum;

/**
 * Created by ignaciofernandezalvarez on 1/5/16.
 */
public class InsertPElection implements InsertElection {
    @Override
    public Referendum insertReferendum(Referendum referendum) {
        return new InsertPElection().insertReferendum(referendum);
    }

    @Override
    public OpenList insertOpenList(OpenList openList) {
        return new InsertPElection().insertOpenList(openList);
    }

    @Override
    public ClosedList insertClosedList(ClosedList closedList) {
        return new InsertPElection().insertClosedList(closedList);
    }
}
