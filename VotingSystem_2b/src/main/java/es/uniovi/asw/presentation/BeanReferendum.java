package es.uniovi.asw.presentation;

import es.uniovi.asw.bussiness.Factories;
import es.uniovi.asw.model.Referendum;
import es.uniovi.asw.model.Voter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by ignaciofernandezalvarez on 14/4/16.
 */
@Component("beanReferendum")
@Scope("view")
public class BeanReferendum {


    private Referendum referendum;
    private String selectedValue;


    private boolean voted;


    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public List<String> getValues() {
        return Arrays.asList("Si", "No");
    }

    @PostConstruct
    public void init() {

        referendum = (Referendum) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .get("eleccion");

    }

    public void votar() {

        if (!voted) {

            Map<String, Object> session = FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap();
            Voter v = (Voter) session.get("LOGGEDIN_VOTER");


            boolean hasVoted = Factories.services.createVoteFactory().voteInReferendum(referendum, selectedValue, v);

            if (hasVoted) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        "Ha votado correctamente, muchas gracias por su participación."));
                voted = hasVoted;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                    "Usted ya había ejercido su derecho a voto, no intente hacerlo dos veces"));

        }

    }


    public Referendum getReferendum() {
        return referendum;
    }

    public void setReferendum(Referendum referendum) {
        this.referendum = referendum;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }


}
