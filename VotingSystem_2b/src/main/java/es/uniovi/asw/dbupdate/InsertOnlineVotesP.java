package es.uniovi.asw.dbupdate;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.model.*;
import java.util.List;
/**
 * Created by ignaciofernandezalvarez on 1/5/16.
 */
public class InsertOnlineVotesP implements InsertOnlineVotes {


    private void saveTurnout(Voter v, Election election) {

        Turnout turnout = new Turnout();
        turnout.setElection(election);
        turnout.setVoter(v);
        Repository.turnoutR.merge(turnout);
    }


    private boolean hasAlreadyVoted(Voter v, Election election) {


        Turnout t = Repository.turnoutR.findByVoterAndElection(v, election);

        if (t == null) {

            return false;
        }
        return true;
    }

    @Override
    public VoteClosedList votingClosedList(String partyVoted, Voter v, ClosedList closedList) {



        try {

            Candidature c = Repository.candidatureR.findByName(partyVoted);
            VoteClosedList voteClosedList = Repository.voteR.findByElectionAndCandidatureAndPollingPlace(closedList, c, v.getPollingPlace());

            if (voteClosedList == null) {

                voteClosedList = new VoteClosedList();
                voteClosedList.setPollingPlace(v.getPollingPlace());
                voteClosedList.setNumVotes(1);
                voteClosedList.setElection(closedList);
                voteClosedList.setCandidature(c);

            } else {

                voteClosedList.setNumVotes(voteClosedList.getNumVotes() + 1);

            }

            if(hasAlreadyVoted(v,closedList)) return  null;

            VoteClosedList voteClosedList1 = Repository.voteR.save(voteClosedList);
            saveTurnout(v, closedList);


            return voteClosedList1;

        } catch (Exception e) {

            return null;
        }




    }

    @Override
    public VoteOpenList votingOpenList(List<Candidate> candidates, Voter voter, OpenList openList) {




        try {

            for (Candidate candidate: candidates) {


                VoteOpenList voteOpenList = Repository.voteR.findByElectionAndCandidateAndPollingPlace(openList, candidate, voter.getPollingPlace());

                if (voteOpenList == null) {

                    voteOpenList = new VoteOpenList();
                    voteOpenList.setPollingPlace(voter.getPollingPlace());
                    voteOpenList.setNumVotes(1);
                    voteOpenList.setElection(openList);
                    voteOpenList.setCandidate(candidate);

                } else {

                    voteOpenList.setNumVotes(voteOpenList.getNumVotes() + 1);

                }



                if(hasAlreadyVoted(voter,openList)) return  null;

                VoteOpenList voteOpenList1 = Repository.voteR.save(voteOpenList);
                saveTurnout(voter,openList);
                return voteOpenList1;
            }


        } catch (Exception e) {
            return null;
        }

        return null;
    }

    @Override
    public VoteReferendum votingReferendum(String selectedValue, Voter v, Referendum referendum) {


        VoteReferendum voteReferendum = Repository.voteR.findVoteReferendumByElectionAndPollingPlace(referendum, v.getPollingPlace());

        if (voteReferendum == null) {


            voteReferendum = new VoteReferendum();
            voteReferendum.setElection(referendum);
            voteReferendum.setPollingPlace(v.getPollingPlace());


        }

        if ("Si".equals(selectedValue)) {


            voteReferendum.setYeses(voteReferendum.getYeses() + 1);

        } else {
            voteReferendum.setNoes(voteReferendum.getNoes() + 1);

        }

        voteReferendum.setNumVotes(1);

        if(hasAlreadyVoted(v,referendum)) return  null;
        VoteReferendum voteReferendum1= Repository.voteR.save(voteReferendum);

        saveTurnout(v,referendum);

        return voteReferendum1;

    }





}

