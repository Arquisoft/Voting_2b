package es.uniovi.asw.voterCount.infrastructure;

import es.uniovi.asw.voterCount.business.ServiceFactory;
import es.uniovi.asw.voterCount.business.impl.SimpleServicesFactory;


public class Factories
{

	public static ServiceFactory services = new SimpleServicesFactory();
}