package es.uniovi.asw.bussiness;

import com.thoughtworks.selenium.webdriven.commands.Open;
import es.uniovi.asw.model.*;

import java.util.List;

/**
 * Created by ignaciofernandezalvarez on 16/4/16.
 */
public interface VoteFactory {


    boolean voteInReferendum(Referendum referendum, String selectedValue, Voter v );

    boolean voteInCerradas(ClosedList closedList,String partyVoted,Voter v);

    boolean voteInAbiertas(OpenList openList, List<Candidate> candidates,Voter voter);

}
