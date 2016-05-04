package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.model.Candidate;
import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.Region;

public interface InsertElectoralLists {
    
    List<Candidate> insertCandidates(List<Candidate> candidatos);
    List<Candidature> insertCandidatures(List<Candidature> candidaturas);
    List<Region> insertRegions(List<Region> regiones);
    
}
