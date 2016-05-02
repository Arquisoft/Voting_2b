package es.uniovi.asw.bussiness.implementation.classes;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.electionday.parser.RRegionExcel;
import es.uniovi.asw.model.Constituency;
import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Region;

import java.util.List;

/**
 * Created by ignaciofernandezalvarez on 20/4/16.
 */
public class PollingPlaceConfig {
    public boolean create() {

        try {

            List<Region> pp = new RRegionExcel().readFile("src/main/test/resourceselecciones.xlsx");

            for (Region r : pp) {
                Repository.regionR.save(r);

                for (Constituency c : r.getConstituencies()) {

                    Repository.constituencyR.save(c);

                    for (PollingPlace ppl : c.getPollingPlaces()) {

                        Repository.pollingPlaceR.save(ppl);
                    }
                }

            }

            System.out.println(pp);


            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
