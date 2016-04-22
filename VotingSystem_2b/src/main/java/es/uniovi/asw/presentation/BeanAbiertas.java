package es.uniovi.asw.presentation;

import de.larmic.butterfaces.event.TableSingleSelectionListener;
import es.uniovi.asw.bussiness.Factories;
import es.uniovi.asw.model.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;

/**
 * Created by ignaciofernandezalvarez on 14/4/16.
 */
@Component("beanAbiertas")
@Scope("view")
public class BeanAbiertas implements Serializable {


    private OpenList openList;
    private String selectedValue;
    private List<Candidate> candidates;
    private Map<Long, Boolean> selectedIds = new HashMap<Long, Boolean>();


    private boolean voted;


    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }


    @PostConstruct
    public void init() {


        openList = (OpenList) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .get("eleccion");
        candidates = new ArrayList<>();
        candidates.addAll(openList.getCandidates());

    }

    public String votar() {

        boolean cancelThisTrip = false;
        List<Candidate> candidatesToVote = new ArrayList<>();

        for (Candidate trip : candidates) {

            if (selectedIds.get(trip.getId()) != null)
                cancelThisTrip = selectedIds.get(trip.getId());

            if (cancelThisTrip) {

                candidatesToVote.add(trip);


            }

        }

        if (candidatesToVote.size() < openList.getNumChoices()) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                    "Por favor escoja " + openList.getNumChoices() + " candidatos, actualmente ha escogido " + candidatesToVote.size()));

        } else {
            Map<String, Object> session = FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap();
            Voter v = (Voter) session.get("LOGGEDIN_VOTER");
            boolean correct = Factories.services.createVoteFactory().voteInAbiertas(openList, candidatesToVote, v);

            if (correct) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        "¡Gracias por votar!"));
                this.voted = true;
                return "exito";
            } else {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                        "¡Ya había votado con anterioridad!"));
            }
        }

        return "fallo";


    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public OpenList getOpenList() {
        return openList;
    }

    public void setOpenList(OpenList openList) {
        this.openList = openList;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public void setSelectedIds(Map<Long, Boolean> selectedIds) {
        this.selectedIds = selectedIds;
    }

    public Map<Long, Boolean> getSelectedIds() {
        return selectedIds;
    }
}
