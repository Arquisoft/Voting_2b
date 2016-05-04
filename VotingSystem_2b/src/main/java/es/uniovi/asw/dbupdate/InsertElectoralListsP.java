package es.uniovi.asw.dbupdate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.electionday.parser.CheckFailsCandidate;
import es.uniovi.asw.electionday.parser.CheckFailsCandidature;
import es.uniovi.asw.electionday.parser.CheckFailsRegion;
import es.uniovi.asw.model.Candidate;
import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.Constituency;
import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Region;

public class InsertElectoralListsP implements InsertElectoralLists {
	
	Logger log = LoggerFactory.getLogger(InsertElectoralListsP.class);
	
	@Override
	public List<Candidate> insertCandidates(List<Candidate> candidatos) {
		Candidature cand;
		Candidate candidato;
		int cont = 0;
		for(Candidate c:candidatos){
			if(CheckFailsCandidate.check(c)){
				cand = Repository.candidatureR.findByName(c.getCandidature().getName());
				candidato = Repository.candidateR.findByDni(c.getDNI());
				if (cand == null) {
					cand = new Candidature();
					cand.setName(c.getCandidature().getName());
				}
				if (candidato == null)
					candidato = c;
				candidato.setCandidature(cand);
				Repository.candidatureR.save(cand);
				Repository.candidateR.save(candidato);
				cont ++;
			}

		}
		
		System.out.println("Se han registrado " + cont + " candidatos");
		
		return candidatos;
	}

	public List<Candidature> insertCandidatures(List<Candidature> candidaturas) {
		int cont = 0;
		Candidature candidature;
		for(Candidature c:candidaturas){
			if(CheckFailsCandidature.check(c)){
				candidature = Repository.candidatureR.findByName(c.getName());
				if (candidature == null)
					candidature = c;
					
				Repository.candidatureR.save(candidature);
				cont ++;
			}
		}

		System.out.println("Se han registrado " + cont + " candidaturas");
		
		return candidaturas;
	}
	
	@Override
	public List<Region> insertRegions(List<Region> regiones) {
		Region region;
		Constituency constituency;
		PollingPlace pollingPlace;
		
		int cont = 0;
		
		for (Region r : regiones) {
			if (CheckFailsRegion.check(r)) {

				region = Repository.regionR.findByName(r.getName());
				if (region == null)
					region = r;
				
				Repository.regionR.save(region);
				cont ++;

				for (Constituency c : r.getConstituencies()) {
					constituency = Repository.constituencyR.findByName(c.getName());
					if (constituency == null)
						constituency = c;
					
					constituency.setRegion(region);
					Repository.constituencyR.save(constituency);

					for (PollingPlace p : c.getPollingPlaces()) {
						pollingPlace = Repository.pollingPlaceR.findOne(p.getId());
						if (pollingPlace == null)
							pollingPlace = p;
						
						
						pollingPlace.setConstituency(constituency);
						Repository.pollingPlaceR.save(pollingPlace);
					}
				}

			}
		}

		System.out.println("Se han registrado " + cont + " regiones");

		return regiones;
	}
	
}
