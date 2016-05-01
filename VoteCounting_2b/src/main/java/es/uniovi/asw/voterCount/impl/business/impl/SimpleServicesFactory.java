package es.uniovi.asw.voterCount.impl.business.impl;

import es.uniovi.asw.voterCount.impl.business.RecuentoService;
import es.uniovi.asw.voterCount.impl.business.ServiceFactory;

public class SimpleServicesFactory implements ServiceFactory
{
	@Override
	public RecuentoService createRecuentoService()
	{
		return new SimpleRecuentoService();
	}
}