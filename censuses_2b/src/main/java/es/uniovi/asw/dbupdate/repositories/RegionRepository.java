package es.uniovi.asw.dbupdate.repositories;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.model.Region;

public interface RegionRepository extends CrudRepository<Region, Long> {
	
	Region findByName(String name);

}
