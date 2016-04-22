package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.Turnout;

public interface TurnoutRepositoryCustom {

	void merge(Turnout turnout);
}
