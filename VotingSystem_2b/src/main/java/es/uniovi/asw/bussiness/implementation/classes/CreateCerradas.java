package es.uniovi.asw.bussiness.implementation.classes;

import es.uniovi.asw.dbupdate.InsertRCandidature;
import es.uniovi.asw.dbupdate.Repository;
import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.parser.RCandidatureExcel;
import es.uniovi.asw.parser.ReadCandidature;

import java.util.List;

/**
 * Created by ignaciofernandezalvarez on 20/4/16.
 */
public class CreateCerradas {

    private ClosedList closedList;
    private boolean isTest;


    public CreateCerradas(ClosedList closedList, boolean isTest) {

        this.closedList = closedList;
        this.isTest = isTest;

    }


    public boolean create() {

        boolean result = true;

        try {

            ReadCandidature readCandidature = new RCandidatureExcel();
            List<Candidature> candidatures;
            if (!isTest) {
                candidatures = readCandidature.read("src/main/test/resourceselecciones.xlsx");
            } else {
                candidatures = new RCandidatureExcel().read("src/test/resources/testCandidatures.xlsx");

            }

            Repository.electionR.save(closedList);

            for (Candidature candidature : candidatures)
            	candidature.addElection(closedList);
            
            new InsertRCandidature().insert(candidatures);


        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }
        return true;

    }
}
