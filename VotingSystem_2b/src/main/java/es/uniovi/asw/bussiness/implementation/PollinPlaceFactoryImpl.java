package es.uniovi.asw.bussiness.implementation;

import es.uniovi.asw.bussiness.PollingPlaceFactory;
import es.uniovi.asw.bussiness.implementation.classes.PollingPlaceConfig;

/**
 * Created by ignaciofernandezalvarez on 20/4/16.
 */
public class PollinPlaceFactoryImpl implements PollingPlaceFactory {
    @Override
    public boolean configurePolling() {
        return new PollingPlaceConfig().create();
    }
}
