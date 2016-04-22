package es.uniovi.asw.voterCount.business.impl;

import java.util.HashMap;

import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.voterCount.business.RecuentoService;
import es.uniovi.asw.voterCount.business.impl.classes.RecuentoListaAbierta;
import es.uniovi.asw.voterCount.business.impl.classes.RecuentoListaCerrada;
import es.uniovi.asw.voterCount.business.impl.classes.RecuentoReferendum;


public class SimpleRecuentoService implements RecuentoService
{
	@Override
	public HashMap<String, Integer> recuentoReferendum(Election e)
	{
		return new RecuentoReferendum().recuento(e);
	}
	
	
	@Override
	public HashMap<String, Integer> recuentoListaCerrada(Election e)
	{
		return new RecuentoListaCerrada().recuento(e);
	}
	
	
	@Override
	public HashMap<String, Integer> recuentoListaAbierta(Election e)
	{
		return new RecuentoListaAbierta().recuento(e);
	}
}