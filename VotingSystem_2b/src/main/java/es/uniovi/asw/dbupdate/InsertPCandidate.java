package es.uniovi.asw.dbupdate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.asw.model.Candidate;
import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.parser.CheckFailsCandidate;



public class InsertPCandidate implements InsertCandidate{
	Logger log = LoggerFactory.getLogger(InsertPCandidate.class);
	
	@Override
	public List<Candidate> insert(List<Candidate> candidatos) {
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

}
