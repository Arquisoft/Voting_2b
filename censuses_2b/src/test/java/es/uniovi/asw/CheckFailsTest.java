package es.uniovi.asw;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.parser.CheckFails;
import es.uniovi.asw.parser.VoterInfo;

public class CheckFailsTest {
	
	@Test
	public void test() {
		VoterInfo v1 = new VoterInfo("","","","");		
		assertFalse(CheckFails.comprobarFallosNombre(v1));
		assertFalse(CheckFails.comprobarFallosNIF(v1));
		assertFalse(CheckFails.comprobarFallosEmail(v1));
		assertFalse(CheckFails.comprobarFallosPollingPlace(v1));
	}
	
	@Test
	public void test2() {
		VoterInfo v1 = new VoterInfo("Jonás","12345678Q","jonas@uniovi.es","3");		
		assertTrue(CheckFails.comprobarFallosNombre(v1));
		assertTrue(CheckFails.comprobarFallosNIF(v1));
		assertTrue(CheckFails.comprobarFallosEmail(v1));
		assertTrue(CheckFails.comprobarFallosPollingPlace(v1));
	}
	
	@Test
	public void test3() {
		VoterInfo v1 = new VoterInfo("Jonás","123456784Q","jonasuniovi.es","-1");		
		assertTrue(CheckFails.comprobarFallosNombre(v1));
		assertFalse(CheckFails.comprobarFallosNIF(v1));
		assertFalse(CheckFails.comprobarFallosEmail(v1));
		assertFalse(CheckFails.comprobarFallosPollingPlace(v1));
	}
	
	@Test
	public void test4() {
		VoterInfo v1 = new VoterInfo("","123456783","jonas@uniovies.","6");		
		assertFalse(CheckFails.comprobarFallosNombre(v1));
		assertFalse(CheckFails.comprobarFallosNIF(v1));
		assertFalse(CheckFails.comprobarFallosEmail(v1));
		assertTrue(CheckFails.comprobarFallosPollingPlace(v1));
	}

}
