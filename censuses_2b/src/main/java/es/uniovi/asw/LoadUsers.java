package es.uniovi.asw;

import es.uniovi.asw.parser.Parser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application
 * 
 * @author censuses_2b
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"es.uniovi.asw"})
public class LoadUsers {
	
	public static void main(String... args) {
		SpringApplication.run(LoadUsers.class, args);
		Parser.run(args);
	}

}