package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.model.Constituency;
import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Region;
import es.uniovi.asw.parser.CheckFailsRegion;

public class InsertPRegion implements InsertRegion {

	@Override
	public List<Region> insert(List<Region> regiones) {
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
