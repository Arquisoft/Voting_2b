package es.uniovi.asw.dbManagement.persistence;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.Constituency;

public interface ConstituencyRepository extends CrudRepository<Constituency, Long>{

	Constituency findByName(String name);

}
