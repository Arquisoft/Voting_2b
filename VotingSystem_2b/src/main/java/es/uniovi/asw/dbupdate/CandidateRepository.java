package es.uniovi.asw.dbupdate;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import es.uniovi.asw.model.Candidate;
import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.Election;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {

	@Query("SELECT c FROM Candidate c inner join fetch c.elections e where e = :election")
	List<Candidate> findByElection(@Param("election")Election election);
	List<Candidate> findByCandidature(Candidature candidature);
	Candidate findByDni(String dni);
	
}
