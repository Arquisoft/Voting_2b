package es.uniovi.asw.dbupdate.repositories;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.model.Constituency;

public interface ConstituencyRepository extends CrudRepository<Constituency, Long> {

	Constituency findByName(String name);
}
