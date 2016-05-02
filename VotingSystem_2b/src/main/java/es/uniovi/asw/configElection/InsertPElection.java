package es.uniovi.asw.configElection;

import es.uniovi.asw.dbupdate.InsertElection;
import es.uniovi.asw.dbupdate.InsertRCandidate;
import es.uniovi.asw.dbupdate.InsertRCandidature;
import es.uniovi.asw.dbupdate.InsertRElection;
import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.model.*;
import es.uniovi.asw.parser.RCandidateExcel;
import es.uniovi.asw.parser.RCandidatureExcel;
import es.uniovi.asw.parser.ReadCandidature;

import java.util.List;

/**
 * Created by ignaciofernandezalvarez on 1/5/16.
 */
public class InsertPElection implements InsertElection {

    private boolean isTest;

    public InsertPElection(boolean isTest){
        this.isTest=isTest;
    }

   public InsertPElection(){
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
         OpenList openList1 =    Repository.electionR.save(openList);
            for (Candidate candidate : candidatos)
                candidate.addElection(openList);
            new InsertRCandidate().insert(candidatos);
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

            new InsertRCandidature().insert(candidatures);
            return closedList1;


        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }
}
