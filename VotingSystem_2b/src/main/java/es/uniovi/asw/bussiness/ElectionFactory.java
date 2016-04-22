package es.uniovi.asw.bussiness;

import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.OpenList;
import es.uniovi.asw.model.Referendum;

/**
 * Created by ignaciofernandezalvarez on 14/4/16.
 */
public interface ElectionFactory {


    boolean createReferendum(Referendum referendum);

    boolean createCerradas(ClosedList closedList,boolean isTest);

    boolean createAbiertas(OpenList openList,boolean isTest);




}
