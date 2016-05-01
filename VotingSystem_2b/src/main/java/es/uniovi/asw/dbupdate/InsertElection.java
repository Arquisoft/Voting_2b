package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.OpenList;
import es.uniovi.asw.model.Referendum;

/**
 * Created by ignaciofernandezalvarez on 1/5/16.
 */
public interface InsertElection {

    Referendum insertReferendum(Referendum referendum);

    OpenList insertOpenList(OpenList openList);

    ClosedList insertClosedList(ClosedList closedList);
}
