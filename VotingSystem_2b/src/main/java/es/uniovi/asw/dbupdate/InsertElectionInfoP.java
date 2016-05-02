package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.electionday.InsertElectoralListsR;
import es.uniovi.asw.electionday.parser.RCandidateExcel;
import es.uniovi.asw.electionday.parser.RCandidatureExcel;
import es.uniovi.asw.electionday.parser.ReadCandidature;
import es.uniovi.asw.model.Candidate;
import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.OpenList;
import es.uniovi.asw.model.Referendum;

public class InsertElectionInfoP implements InsertElectoralInfo {

	private boolean isTest;

	public InsertElectionInfoP(boolean isTest){
	        this.isTest=isTest;
	    }

	public InsertElectionInfoP(){
	       isTest=false;
	}

	@Override
	public Referendum insertReferendum(Referendum referendum) {

		try {
			return Repository.electionR.save(referendum);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public OpenList insertOpenList(OpenList openList) {

		List<Candidate> candidatos;
		if (!isTest) {
			candidatos = new RCandidateExcel().readFile("src/main/test/resourceselecciones.xlsx");
		} else {
			candidatos = new RCandidateExcel().read("src/test/resources/testCandidatos.xlsx");
		}

		try {
			OpenList openList1 = Repository.electionR.save(openList);
			for (Candidate candidate : candidatos)
				candidate.addElection(openList);
			new InsertElectoralListsR().insertCandidates(candidatos);
			return openList1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public ClosedList insertClosedList(ClosedList closedList) {

		try {

			ReadCandidature readCandidature = new RCandidatureExcel();
			List<Candidature> candidatures;
			if (!isTest) {
				candidatures = readCandidature.read("src/main/test/resourceselecciones.xlsx");
			} else {
				candidatures = new RCandidatureExcel().read("src/test/resources/testCandidatures.xlsx");

			}

			ClosedList closedList1 = Repository.electionR.save(closedList);
			for (Candidature candidature : candidatures)
				candidature.addElection(closedList);

			new InsertElectoralListsR().insertCandidatures(candidatures);
			return closedList1;

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		}

	}
}
