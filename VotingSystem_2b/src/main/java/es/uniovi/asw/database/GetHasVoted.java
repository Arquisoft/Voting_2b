package es.uniovi.asw.database;

import es.uniovi.asw.model.Turnout;
import es.uniovi.asw.model.Voter;

public interface GetHasVoted {

	Turnout get(Voter voter);
}
