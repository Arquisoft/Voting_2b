package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.OpenList;
import es.uniovi.asw.model.Referendum;

public interface InsertElectoralInfo {

	Referendum insertReferendum(Referendum referendum);
    OpenList insertOpenList(OpenList openList);
    ClosedList insertClosedList(ClosedList closedList);
    
}
