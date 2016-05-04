package es.uniovi.asw.dbManagement.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "es.uniovi.asw.dbManagement.model" })
@EnableJpaRepositories(basePackages = { "es.uniovi.asw.dbManagement.persistence" })
@EnableTransactionManagement
public class RepositoryConfiguration {


	@Autowired
	public void setVoterR(VoterRepository voterR) {
		Repository.voterR = voterR;
	}
	@Autowired
	public void setPollingPlaceR(PollingPlaceRepository pollingPlaceR) {
		Repository.pollingPlaceR = pollingPlaceR;
	}
	
}