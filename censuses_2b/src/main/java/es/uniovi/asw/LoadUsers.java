package es.uniovi.asw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.uniovi.asw.dbupdate.VoterRepository;
import es.uniovi.asw.parser.Parser;

/**
 * Main application
 * 
 * @author censuses_2b
 *
 */
@SpringBootApplication
public class LoadUsers {
	
	public static void main(String... args) {
		SpringApplication.run(LoadUsers.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(VoterRepository repository) {
		return (args) -> {
			Parser.voterRepository = repository;
			Parser.run(args);
		};
	
	}

}