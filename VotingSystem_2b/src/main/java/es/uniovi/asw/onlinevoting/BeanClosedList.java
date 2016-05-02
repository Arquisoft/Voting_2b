package es.uniovi.asw.onlinevoting;

import de.larmic.butterfaces.event.TableSingleSelectionListener;
import es.uniovi.asw.dbupdate.InsertOnlineVotesP;
import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.ClosedList;
import es.uniovi.asw.model.Voter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ignaciofernandezalvarez on 14/4/16.
 */
@Component("beanClosed")
@Scope("view")
public class BeanClosedList implements Serializable, TableSingleSelectionListener {

	private static final long serialVersionUID = 1L;
	
	private ClosedList closedList;
    private boolean voted;

    @PostConstruct
    public void init() {


        closedList = (ClosedList) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .get("eleccion");
    }

    public void votar(String inicial) {

        if (!voted) {

            Map<String, Object> session = FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap();
            Voter v = (Voter) session.get("LOGGEDIN_VOTER");


            Object hasVoted = new InsertOnlineVotesP().votingClosedList(inicial,v,closedList);//= Factories.services.createVoteFactory().voteInReferendum(referendum, selectedValue, v);

            if (hasVoted!=null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        "Ha votado correctamente, muchas gracias por su participación."));
                voted = true;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                    "Usted ya había ejercido su derecho a voto, no intente hacerlo dos veces"));

        }


    }

    public ClosedList getClosedList() {
        return closedList;
    }

    public void setClosedList(ClosedList closedList) {
        this.closedList = closedList;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public List<Candidature> getValue() {
        final List<Candidature> pairs = new ArrayList<>();
        pairs.addAll(closedList.getCandidatures());
        return pairs;
    }

    private Candidature selectedRow;


    public void processTableSelection(final Candidature data) {
        this.selectedRow = data;
    }

    public boolean isValueSelected(Candidature data) {
        return selectedRow != null ? data.getId() == selectedRow.getId() : false;
    }

    public Candidature getSelectedRow() {
        return selectedRow;
    }


    @Override
    public void processTableSelection(Object o) {

    }

    @Override
    public boolean isValueSelected(Object o) {
        return false;
    }
}
