package es.uniovi.asw.voterCount.business.impl;

import es.uniovi.asw.voterCount.business.RecuentoService;
import es.uniovi.asw.voterCount.business.ServiceFactory;


public class SimpleServicesFactory implements ServiceFactory
{
	@Override
	public RecuentoService createRecuentoService()
	{
		return new SimpleRecuentoService();
	}
}