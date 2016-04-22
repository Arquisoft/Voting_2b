package es.uniovi.asw.dbupdate;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import es.uniovi.asw.model.Candidate;
import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.VoteClosedList;
import es.uniovi.asw.model.VoteOpenList;
import es.uniovi.asw.model.VoteReferendum;

public interface VoteRepository extends CrudRepository<Vote, Long> {

	List<Vote> findByElection(Election election);

	@Query("SELECT v FROM Vote v WHERE v.election = :election and v.pollingPlace = :pollingPlace")
	List<Vote> findByElectionAndPollingPlace(@Param("election") Election election,
			@Param("pollingPlace") PollingPlace pollingPlace);
	
	@Query("SELECT v FROM VoteReferendum v WHERE v.election = :election and v.pollingPlace = :pollingPlace")
	VoteReferendum findVoteReferendumByElectionAndPollingPlace(@Param("election") Election election,
			@Param("pollingPlace") PollingPlace pollingPlace);
	
	@Query("SELECT v FROM VoteClosedList v WHERE v.election = :election and v.candidature = :candidature and v.pollingPlace = :pollingPlace")
	VoteClosedList findByElectionAndCandidatureAndPollingPlace(@Param("election") Election election,
			@Param("candidature") Candidature candidature,
			@Param("pollingPlace") PollingPlace pollingPlace);
	
	@Query("SELECT v FROM VoteOpenList v WHERE v.election = :election and v.candidate = :candidate and v.pollingPlace = :pollingPlace")
	VoteOpenList findByElectionAndCandidateAndPollingPlace(@Param("election") Election election,
			@Param("candidate") Candidate candidate, 
			@Param("pollingPlace") PollingPlace pollingPlace);

}
