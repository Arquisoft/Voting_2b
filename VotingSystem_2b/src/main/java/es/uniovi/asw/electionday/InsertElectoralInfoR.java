package es.uniovi.asw.electionday;

import es.uniovi.asw.dbupdate.InsertElectionInfoP;
import es.uniovi.asw.dbupdate.InsertElectoralInfo;
import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.OpenList;
import es.uniovi.asw.model.Referendum;

public class InsertElectoralInfoR implements InsertElectoralInfo {

	@Override
	public Referendum insertReferendum(Referendum referendum) {
		return new InsertElectionInfoP().insertReferendum(referendum);
	}

	@Override
	public OpenList insertOpenList(OpenList openList) {
		return new InsertElectionInfoP().insertOpenList(openList);
	}

	@Override
	public ClosedList insertClosedList(ClosedList closedList) {
		return new InsertElectionInfoP().insertClosedList(closedList);
	}
}
