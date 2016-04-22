package es.uniovi.asw.bussiness.implementation.classes;

import es.uniovi.asw.dbupdate.InsertRCandidate;
import es.uniovi.asw.dbupdate.Repository;
import es.uniovi.asw.model.Candidate;
import es.uniovi.asw.model.OpenList;
import es.uniovi.asw.parser.RCandidateExcel;

import java.util.List;

/**
 * Created by ignaciofernandezalvarez on 20/4/16.
 */
public class CreateAbiertas {
    private OpenList openList;
    private boolean isTest;

    public CreateAbiertas(OpenList openList, boolean isTest) {
        this.openList = openList;
        this.isTest = isTest;
    }

    public boolean create() {
        List<Candidate> candidatos;
        if (!isTest) {
            candidatos = new RCandidateExcel().readFile("src/main/test/resourceselecciones.xlsx");
        } else {
            candidatos = new RCandidateExcel().read("src/test/resources/testCandidatos.xlsx");
        }

        try {


            System.out.println(openList);
            Repository.electionR.save(openList);

            for (Candidate candidate : candidatos)
                candidate.addElection(openList);

            new InsertRCandidate().insert(candidatos);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        return true;


    }
}


