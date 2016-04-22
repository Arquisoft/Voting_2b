package es.uniovi.asw.bussiness;

/**
 * Created by ignaciofernandezalvarez on 14/4/16.
 */
public interface BussinesFactory {

    ElectionFactory createElectionFactory();

    VoteFactory createVoteFactory();

    VoterFactory createVoterFactory();

    PollingPlaceFactory createPollingPlace();
}
