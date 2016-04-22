package es.uniovi.asw.dbupdate;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.Election;


public interface CandidatureRepository extends CrudRepository<Candidature,Long> {

    Candidature findByName(String name);
    
    @Query("SELECT c FROM Candidature c inner join fetch c.elections e WHERE c.name = :name and e = :election")
    Candidature findByNameAndElection(@Param("name")String name, @Param("election")Election election);
    
	@Query("SELECT c FROM Candidature c inner join fetch c.elections e WHERE e = :election")
    List<Candidature> findByElection(@Param("election") Election election);
}
