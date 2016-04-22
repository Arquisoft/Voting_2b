package es.uniovi.asw.dbManagement.persistence;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.Region;

public interface RegionRepository extends CrudRepository<Region, Long>{

	Region findByName(String name);

}
