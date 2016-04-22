package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uniovi.asw.dbupdate.InsertRRegion;
import es.uniovi.asw.dbupdate.Repository;
import es.uniovi.asw.dbupdate.RepositoryConfiguration;
import es.uniovi.asw.model.Constituency;
import es.uniovi.asw.model.Region;
import es.uniovi.asw.parser.RRegionExcel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, RepositoryConfiguration.class})

public class InsertRegionTest {
	
	@Test
	public void test() {
		List<Region> regiones = new RRegionExcel().read("src/test/resources/testRegionInsercion.xlsx");
		assertEquals(5, regiones.size());
		new InsertRRegion().insert(regiones);
		for (Constituency c:Repository.regionR.findByName("Cataluña").getConstituencies()){
			assertEquals("C",c.getName());
		}		
		for (Constituency c:Repository.regionR.findByName("Galicia").getConstituencies()){
			assertEquals("G",c.getName());
		}
		assertNull(Repository.regionR.findByName("Navarra"));
		
		long id = 8;
		assertEquals("P",Repository.pollingPlaceR.findOne(id).getConstituency().getName());
		assertEquals("Pais Vasco",Repository.pollingPlaceR.findOne(id).getConstituency().getRegion().getName());
		
		new InsertRRegion().insert(regiones);
	}

}
