package es.uniovi.asw.electionday;

import java.util.List;

import es.uniovi.asw.dbupdate.InsertElectoralLists;
import es.uniovi.asw.dbupdate.InsertElectoralListsP;
import es.uniovi.asw.model.Candidate;
import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.Region;

public class InsertElectoralListsR implements InsertElectoralLists {
    
    @Override
	public List<Candidate> insertCandidates(List<Candidate> candidatos) {
		return new InsertElectoralListsP().insertCandidates(candidatos);
	}

	@Override
	public List<Candidature> insertCandidatures(List<Candidature> candidaturas) {
		return new InsertElectoralListsP().insertCandidatures(candidaturas);
	}
	
	@Override
	public List<Region> insertRegions(List<Region> regiones) {
		return new InsertElectoralListsP().insertRegions(regiones);
	}
    
}
