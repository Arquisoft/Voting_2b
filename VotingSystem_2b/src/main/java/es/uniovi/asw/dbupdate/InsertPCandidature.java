package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.parser.CheckFailsCandidature;

public class InsertPCandidature implements InsertCandidature{

	public List<Candidature> insert(List<Candidature> candidaturas) {
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

}
