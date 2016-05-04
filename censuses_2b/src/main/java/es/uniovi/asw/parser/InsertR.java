package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.database.Insert;
import es.uniovi.asw.database.InsertP;
import es.uniovi.asw.model.Voter;

public class InsertR implements Insert {

	@Override
	public List<Voter> insert(List<VoterInfo> voterValues, String path) {
		return new InsertP().insert(voterValues, path);
	}

}
