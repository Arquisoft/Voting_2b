package es.uniovi.asw.dbupdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "es.uniovi.asw.model" })
@EnableJpaRepositories(basePackages = { "es.uniovi.asw.dbupdate" })
@EnableTransactionManagement
public class RepositoryConfiguration {

	@Autowired
	public void setCandidateR(CandidateRepository candidateR) {
		Repository.candidateR = candidateR;
	}

	@Autowired
	public void setCandidatureR(CandidatureRepository candidatureR) {
		Repository.candidatureR = candidatureR;
	}

	@Autowired
	public void setConstituencyR(ConstituencyRepository constituencyR) {
		Repository.constituencyR = constituencyR;
	}

	@Autowired
	public void setElectionR(ElectionRepository electionR) {
		Repository.electionR = electionR;
	}

	@Autowired
	public void setPollingPlaceR(PollingPlaceRepository pollingPlaceR) {
		Repository.pollingPlaceR = pollingPlaceR;
	}

	@Autowired
	public void setRegionR(RegionRepository regionR) {
		Repository.regionR = regionR;
	}

	@Autowired
	public void setTurnoutR(TurnoutRepository turnoutR) {
		Repository.turnoutR = turnoutR;
	}


	@Autowired
	public void setVoteR(VoteRepository voteR) {
		Repository.voteR = voteR;
	}

	@Autowired
	public void setVoterR(VoterRepository voterR) {
		Repository.voterR = voterR;
	}
}
