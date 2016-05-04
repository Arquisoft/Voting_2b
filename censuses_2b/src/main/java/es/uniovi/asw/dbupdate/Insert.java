package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.model.Voter;
import es.uniovi.asw.parser.VoterInfo;

public interface Insert {
	
	public List<Voter> insert(List<VoterInfo> voterValues, String path);
	
}
