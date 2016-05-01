package es.uniovi.asw.bussiness.implementation.classes;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.Turnout;
import es.uniovi.asw.model.Voter;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by ignaciofernandezalvarez on 16/4/16.
 */
public class LogIn {

    private String nif;
    private String password;
    private Voter voter;

    public LogIn(String nif, String password, Voter voter) {
        this.nif = nif;
        this.password = password;
        this.voter = voter;
    }

    public Election logIn() {



        if (voter == null) {
            return null;
        }

        Election election = Repository.electionR.findActual();

        if (hasAlreadyVoted(voter, election)) {


            return null;

        }

        if (voter.getPassword().equals(password)) {
            return election;

        } else {
            password = "";
        }

        return null;
    }

    private boolean hasAlreadyVoted(Voter v, Election election) {

        Turnout t = Repository.turnoutR.findByVoterAndElection(v, election);
        if (t == null) {

            return false;
        }
        return true;

    }

}
