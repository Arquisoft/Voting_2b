package es.uniovi.asw.electionday;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.OpenList;
import es.uniovi.asw.model.Referendum;


/**
 * Created by Ignacio Fernandez on 11/04/2016.
 */
@Component("beanConfigElection")
@Scope("application")

public class BeanConfigElection implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String selectedValue;
    private String electionName;
    private String initialDate;
    private String expireDate;
    private String instructions;
    private String question;
    private static boolean excelUploaded = false;
    private int numChoices = 1;


    @PostConstruct
    void init() {

        //selectedValue="abierta";

    }


    public void println() {

        excelUploaded = false;


    }

    public boolean listasAbiertas() {

        return "Listas Abiertas".equals(selectedValue);
    }

    public boolean referendum() {

        return "Referéndum".equals(selectedValue);
    }

    public boolean listasCerradas() {

        return "Listas Cerradas".equals(selectedValue);
    }


    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public List<String> getValues() {
        return Arrays.asList("Listas Abiertas", "Listas Cerradas", "Referéndum");
    }

    public void setValues(String[] values) {
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
                Object created = new InsertElectoralInfoR().insertClosedList(closedList);

                if (created!=null) {
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
                Object created = new InsertElectoralInfoR().insertOpenList(openList);

                if (created!=null) {
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

            Object created = new InsertElectoralInfoR().insertReferendum(referendum);

            if (created!=null) {

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
