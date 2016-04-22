package es.uniovi.asw.dbManagement.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.integration.leader.Candidate;
import es.uniovi.asw.dbManagement.model.Candidature;
import es.uniovi.asw.dbManagement.model.Election;

public interface CandidatureRepository extends CrudRepository<Candidature, Long>{
	
	List<Candidate> findByElectionsId(Long long1);

	Candidature findByName(String string);

	@Query("SELECT c FROM Candidature c inner join fetch c.elections e WHERE e = :election")
	List<Candidature> findByElection(@Param("election") Election election);

	@Query("SELECT c FROM Candidature c inner join fetch c.elections e WHERE c.name = :name and e = :election")
	Object findByNameAndElection(@Param("name")String name, @Param("election")Election election);
}
