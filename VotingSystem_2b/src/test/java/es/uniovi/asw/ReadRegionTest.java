package es.uniovi.asw;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.electionday.parser.RRegionExcel;
import es.uniovi.asw.model.Region;

public class ReadRegionTest {

	@Test
	public void test() {
		List<Region> regiones = new RRegionExcel().readFile("src/test/resources/testRegion.xlsx");
		assertEquals("Asturias",regiones.get(0).getName());
		assertEquals("Madrid",regiones.get(1).getName());
		assertEquals("Extremadura",regiones.get(2).getName());

	}

}
