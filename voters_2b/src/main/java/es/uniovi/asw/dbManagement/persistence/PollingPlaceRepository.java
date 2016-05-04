package es.uniovi.asw.dbManagement.persistence;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.PollingPlace;

public interface PollingPlaceRepository extends CrudRepository<PollingPlace, Long>{

}
