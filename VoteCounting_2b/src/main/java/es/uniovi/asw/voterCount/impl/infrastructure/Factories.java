package es.uniovi.asw.voterCount.impl.infrastructure;

import es.uniovi.asw.voterCount.impl.business.ServiceFactory;
import es.uniovi.asw.voterCount.impl.business.impl.SimpleServicesFactory;

public class Factories
{

	public static ServiceFactory services = new SimpleServicesFactory();
}