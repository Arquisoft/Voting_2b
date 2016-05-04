package es.uniovi.asw.electionday;

import es.uniovi.asw.bussiness.Factories;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by ignaciofernandezalvarez on 16/4/16.
 */

@Component("beanPolling")
@Scope("view")
public class BeanPollingPlaces {

    public static boolean excelUploaded;

    public String configurePollingPlaces() {


        boolean correcto = Factories.services.createPollingPlace().configurePolling();

        if (correcto) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                    "Colegios electorales configurados correctamente"));
            return "exito";

        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                "Revise el excel proporcionado"));
        return "error";


    }

    public static boolean isExcelUploaded() {
        return excelUploaded;
    }

    public static void setExcelUploaded(boolean excelUploaded) {
        BeanPollingPlaces.excelUploaded = excelUploaded;
    }
}
