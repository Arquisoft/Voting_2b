package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.model.Region;
import es.uniovi.asw.parser.CheckFailsRegion;
import es.uniovi.asw.parser.RRegionExcel;

public class CheckFailsRegionTest {

	@Test
	public void testNombre() {
		List<Region> regiones = new RRegionExcel().readFile("src/test/resources/testRegionNombre.xlsx");
		assertEquals(3, regiones.size());
		assertTrue(CheckFailsRegion.check(regiones.get(0)));
		assertFalse(CheckFailsRegion.check(regiones.get(1)));
		assertTrue(CheckFailsRegion.check(regiones.get(2)));
	}
	
	@Test
	public void testCircunscripcion() {
		List<Region> regiones = new RRegionExcel().readFile("src/test/resources/testRegionCircunscripcion.xlsx");
		assertEquals(3, regiones.size());
		assertTrue(CheckFailsRegion.check(regiones.get(0)));
		assertTrue(CheckFailsRegion.check(regiones.get(1)));
		assertFalse(CheckFailsRegion.check(regiones.get(2)));
	}
	
	@Test
	public void testPollingPlace(){
		List<Region> regiones = new RRegionExcel().readFile("src/test/resources/testRegionColegio.xlsx");
		assertEquals(3, regiones.size());
		assertTrue(CheckFailsRegion.check(regiones.get(0)));
		assertFalse(CheckFailsRegion.check(regiones.get(1)));
		assertTrue(CheckFailsRegion.check(regiones.get(2)));
	}

}
