package es.uniovi.asw.dbManagement.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import es.uniovi.asw.dbManagement.model.Candidate;
import es.uniovi.asw.dbManagement.model.Candidature;
import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.model.PollingPlace;
import es.uniovi.asw.dbManagement.model.Vote;
import es.uniovi.asw.dbManagement.model.VoteClosedList;
import es.uniovi.asw.dbManagement.model.VoteOpenList;
import es.uniovi.asw.dbManagement.model.VoteReferendum;


public interface VoteRepository extends CrudRepository<Vote, Long>{
	
	@Query("SELECT v FROM VoteClosedList v WHERE v.election = :election and v.candidature = :candidature and v.pollingPlace = :pollingPlace")
	 VoteClosedList findByElectionAndCandidatureAndPollingPlace(@Param("election") Election election,
	 			@Param("candidature") Candidature candidature,
	 			@Param("pollingPlace") PollingPlace pollingPlace);
	
	@Query("SELECT v FROM VoteOpenList v WHERE v.election = :election and v.candidate = :candidate and v.pollingPlace = :pollingPlace")
	 VoteOpenList findByElectionAndCandidateAndPollingPlace(@Param("election") Election election,
	 			@Param("candidate") Candidate candidate, 
	 			@Param("pollingPlace") PollingPlace pollingPlace);

	@Query("SELECT v FROM VoteReferendum v WHERE v.election = :election and v.pollingPlace = :pollingPlace")
	VoteReferendum findVoteReferendumByElectionAndPollingPlace(@Param("election") Election election,
			@Param("pollingPlace") PollingPlace pollingPlace);
	
	@Query("SELECT v FROM VoteClosedList v WHERE v.election = :election and v.pollingPlace = :pollingPlace")
	 List<VoteClosedList> findByElectionAndPollingPlace(@Param("election") Election election,
	 			@Param("pollingPlace") PollingPlace pollingPlace);

}
