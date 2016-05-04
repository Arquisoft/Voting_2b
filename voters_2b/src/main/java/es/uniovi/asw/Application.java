package es.uniovi.asw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import es.uniovi.asw.dbManagement.model.PollingPlace;
import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.Repository;


@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"es.uniovi.asw"})
@SpringBootApplication
public class Application
{
	
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
		PollingPlace col1= Repository.pollingPlaceR.findOne(1L);
		if (col1 == null) { 
				col1 = new PollingPlace();
				col1.setId(1L);
		}
		
		Repository.pollingPlaceR.save(col1);
		Repository.voterR.save(new Voter("Jack", "980151", "jk@gmail.com", col1, "1"));
		Repository.voterR.save(new Voter("Jose", "730438", "josep@hotmail.com", col1, "2"));
		Repository.voterR.save(new Voter("Paula", "210953", "PaGu@terra.com", col1, "3"));
		Repository.voterR.save(new Voter("Alfonso", "455846", "alf@hotmail.com", col1, "4"));
		
		col1= Repository.pollingPlaceR.findOne(2L);
		if (col1 == null) { 
				col1 = new PollingPlace();
				col1.setId(2L);
		}
		Repository.pollingPlaceR.save(col1);
		
		Repository.voterR.save(new Voter("Irene", "565861", "GlezIr@hotmail.com", col1, "1"));
		
		col1= Repository.pollingPlaceR.findOne(3L);
		if (col1 == null) { 
				col1 = new PollingPlace();
				col1.setId(3L);
		}
		Repository.pollingPlaceR.save(col1);
		
		Repository.voterR.save(new Voter("Fernando", "564512", "fcano@terra.com", col1, "2"));
		Repository.voterR.save(new Voter("Vinuesa", "514685", "vinu@hotmail.com", col1, "3"));
		Repository.voterR.save(new Voter("Sotorrío", "314896", "soto@hotmail.com", col1, "4"));
		
		col1= Repository.pollingPlaceR.findOne(4L);
		if (col1 == null) { 
				col1 = new PollingPlace();
				col1.setId(4L);
		}
		Repository.pollingPlaceR.save(col1);
		
		Repository.voterR.save(new Voter("Hugo", "214848", "hg@gmail.com", col1, "5"));
		Repository.voterR.save(new Voter("Diana", "197235", "diana23@hotmail.com", col1, "6"));
		Repository.voterR.save(new Voter("Javier", "156585", "javiG_6@gmail.com", col1, "7"));
		Repository.voterR.save(new Voter("Luis", "126945", "luisValdés@terra.com", col1, "8"));
		
	}
}