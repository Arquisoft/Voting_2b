package es.uniovi.asw.presentation;

import es.uniovi.asw.bussiness.Factories;
import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.OpenList;
import es.uniovi.asw.model.Referendum;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by Ignacio Fernandez on 11/04/2016.
 */
@Component("beanConfigElection")
@Scope("application")

public class BeanConfigElection implements Serializable {


    private String selectedValue;
    private String[] values;


    private String electionName;
    private String initialDate;
    private String expireDate;
    private String instructions;
    private String question;
    private static boolean excelUploaded = false;
    private int numChoices;


    @PostConstruct
    void init() {

        System.out.println("BeanControllerElecion creado");
        //selectedValue="abierta";

    }


    public void println() {

        excelUploaded = false;


    }

    public boolean listasAbiertas() {

        return "abierta".equals(selectedValue);
    }

    public boolean referendum() {

        return "referendum".equals(selectedValue);
    }

    public boolean listasCerradas() {

        return "cerrada".equals(selectedValue);
    }


    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public List<String> getValues() {
        return Arrays.asList("abierta", "cerrada", "referendum");
    }

    public void setValues(String[] values) {
        this.values = values;
    }


    public String creaCerradas() {


        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

        try {

            Date initialDated = formatter.parse(initialDate);
            Date expireDated = formatter.parse(expireDate);

            if (initialDated.after(expireDated)) {


                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                        "La fecha de inicio ha de ser antes que la de fin"));
                return "fallo";

            } else {


                ClosedList closedList = new ClosedList();

                putParameters(initialDated, expireDated, closedList);
                boolean created = Factories.services.createElectionFactory().createCerradas(closedList, false);

                if (created) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                            "Elecciones creadas con exito"));
                    return "exito";
                }

            }
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                    "Error con los formatos"));
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                "Ya existen otras elecciones en esa fecha o con ese mismo ID"));

        return "fallo";
    }


    public String creaAbiertas() {


        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

        try {

            Date initialDated = formatter.parse(initialDate);
            Date expireDated = formatter.parse(expireDate);

            if (initialDated.after(expireDated)) {


                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                        "La fecha de inicio ha de ser antes que la de fin"));
                return "fallo";

            } else {
                OpenList openList = new OpenList();
                putParameters(initialDated, expireDated, openList);
                boolean created = Factories.services.createElectionFactory().createAbiertas(openList, false);

                if (created) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                            "Elecciones creadas con exito"));
                    return "exito";
                }

            }
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                    "Error con los formatos"));
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                "Ya existen otras elecciones en esa fecha o con ese mismo ID"));

        return "fallo";

    }

    private void putParameters(Date initialDated, Date expireDated, Election openList) {
        openList.setName(electionName);
        openList.setInstructions(instructions);
        openList.setExpiryDate(expireDated);
        openList.setStartDate(initialDated);
        openList.setNumChoices(numChoices);
    }


    public String creaReferendum() {

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

        try {

            Date initialDated = formatter.parse(initialDate);
            Date expireDated = formatter.parse(expireDate);

            if (initialDated.after(expireDated)) {


                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                        "La fecha de inicio ha de ser antes que la de fin"));
                return "fallo";

            }

            Referendum referendum = new Referendum();
            putParameters(initialDated, expireDated, referendum);
            referendum.setQuestion(question);

            boolean created = Factories.services.createElectionFactory().createReferendum(referendum);

            if (created) {

                System.out.println("creoo");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        "Elecciones creadas con exito"));
                return "exito";
            }


        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                    "Revise el formato de sus datos"));
            return "fallo";
            //MENSAJE DE LOG
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                "No se ha podido crear el referendum, existen elecciones en ese plazo"));
        return "fallo";
    }


    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public static boolean isExcelUploaded() {
        return excelUploaded;
    }

    public static void setExcelUploaded(boolean excelUploaded) {
        BeanConfigElection.excelUploaded = excelUploaded;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getNumChoices() {
        return numChoices;
    }

    public void setNumChoices(int numChoices) {
        this.numChoices = numChoices;
    }
}
