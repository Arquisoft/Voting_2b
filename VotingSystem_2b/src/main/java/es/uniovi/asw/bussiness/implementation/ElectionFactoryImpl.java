package es.uniovi.asw.bussiness.implementation;

import es.uniovi.asw.bussiness.ElectionFactory;
import es.uniovi.asw.bussiness.implementation.classes.CreateAbiertas;
import es.uniovi.asw.bussiness.implementation.classes.CreateCerradas;
import es.uniovi.asw.bussiness.implementation.classes.CreateReferendum;
import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.OpenList;
import es.uniovi.asw.model.Referendum;

/**
 * Created by ignaciofernandezalvarez on 16/4/16.
 */
public class ElectionFactoryImpl implements ElectionFactory {


    @Override
    public boolean createReferendum(Referendum referendum) {
    	referendum.setNumChoices(1);

        return new CreateReferendum(referendum).create();


    }

    @Override
    public boolean createCerradas(ClosedList closedList,boolean isTest) {


        return new CreateCerradas(closedList,isTest).create();
    }

    @Override
    public boolean createAbiertas(OpenList openList,boolean isTest) {


        return new CreateAbiertas(openList,isTest).create();
    }
}
