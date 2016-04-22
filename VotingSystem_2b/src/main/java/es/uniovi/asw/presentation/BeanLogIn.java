package es.uniovi.asw.presentation;

import es.uniovi.asw.bussiness.Factories;
import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.Referendum;
import es.uniovi.asw.model.Voter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created by Ignacio Fernandez on 11/04/2016.
 */
@Component("beanLogIn")
@Scope("request")
public class BeanLogIn {


    public String user;
    public String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String login() {


        if ("admin".equals(getUser()) && "admin".equals(getPassword())) {
            return "exito";
        } else {

            Voter voter = Factories.services.createVoterFactory().findByNif(user);

            if (voter == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                        "No existe el usuario o la contraseña es erronea"));
                return "fallo";

            }

            Election election = Factories.services.createVoterFactory().login(user, password, voter);


            if (election == null) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                        "Ya has votado en estas elecciones," +
                                " o no existen elecciones para las que votar en este plazo"));
                return "fallo";
            } else {
                putUserInSession(voter);
                return reditectToElectionType(election);


            }


        }

    }

    private void putUserInSession(Voter user) {
        Map<String, Object> session = FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap();
        session.put("LOGGEDIN_VOTER", user);
    }


    private String reditectToElectionType(Election type) {

        if (type == null) {
            return "fallo";
        } else {

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                    .put("eleccion", type);

            if (type instanceof Referendum) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                        .put("eleccion", type);
                return "referendum";
            } else if (type instanceof ClosedList) {

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                        .put("eleccion", type);
                return "cerradas";

            } else{
            } return "abiertas";
        }

    }

}
