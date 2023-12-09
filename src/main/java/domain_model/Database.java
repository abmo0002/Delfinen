package domain_model;

import domain_model.*;
import domain_model.comparators.ResultComparator;

import java.util.ArrayList;
import java.util.Collections;

public class Database {


    private ArrayList<Member> memberData = new ArrayList<>();
    private ArrayList<Member> juniorComp = new ArrayList<>();

    private ArrayList<Coach> coachData = new ArrayList<>();

    private ArrayList<TrainingResult> trainingResult = new ArrayList<>();
    private ArrayList<CompetitionResult> competitionResult = new ArrayList<>();;

    private Coach coach = new Coach();


    private boolean changesMade = true;

    public boolean isChangesMade() {
        return changesMade;
    }

    public void setChangesMade(boolean changesMade) {
        this.changesMade = changesMade;
    }

    public Member createMember(String firstName, String lastName, boolean competitive, int age, boolean active, int birthday) {
        Member member = new Member(firstName, lastName, competitive, age, active, birthday);
        memberData.add(member);

        return member;
    }

    public Coach createCoach(String firstName, String lastName, boolean isCompetitive, int age, boolean isActive, int birthday, boolean isCoach) {
        Coach coach = new Coach(firstName, lastName, isCompetitive, age, isActive, birthday, isCoach);
        coachData.add(coach);

        return coach;
    }

    public void createTestData() {
    }

    public void createCoachData() {
        createCoach("Niels", "Rasmussen", false, 60, true, 020265, true);
        createCoach("Kim", "Toft", false, 20, true, 020203, false);
    }

    public void createTournamentResultTestData () {

    }

    public void createTrainingResultTestData () {
    }

    public ArrayList<Member> getMembers() {
        return memberData;
    }

    public ArrayList<Coach> getCoach() {
        return coachData;
    }

    public ArrayList<TrainingResult> getTrainingResult() {
        return trainingResult;
    }

    public ArrayList<CompetitionResult> getCompetitionResult() {
        return competitionResult;
    }

    public ArrayList<Member> searchMembersFirstName(String searchTerm) {

        ArrayList<Member> searchResult = new ArrayList<>();

        for (Member member : memberData) {
            if (member.getFirstName().toLowerCase().contains(searchTerm.toLowerCase().trim())) {
                searchResult.add(member);
            }
        }
        return searchResult;
    }


    public boolean deleteMembers(Member member) {
        boolean deleteMember = memberData.remove(member);
        return deleteMember;
    }

    public void addAll(ArrayList<Member> members) {
        memberData.addAll(members);
    }

    public double getTotalPayment() {
        double yearlyPayment = 0;
        for (Member member : memberData) {
            yearlyPayment += member.getPayment();
        }
        return yearlyPayment;
    }


    public void setJuniorOrSenior() {
        for (Member member : memberData) {
            if (member.getAge() < 18) {
                member.setJunior(true);
            } else if (member.getAge() < 18) {
                member.setJunior(false);
            }
        }
    }

    public CompetitionResult createCompetitionResult(double timeResult, boolean training, int placement, String discipline, String tournamentName, int date, String firstName) {
        CompetitionResult competitionResults = new CompetitionResult(timeResult, training, placement, discipline, tournamentName, date, firstName);
        competitionResult.add(competitionResults);
        return competitionResults;
    }

    public TrainingResult createTrainingResult(double timeResult, boolean training, String discipline, int date, String firstName, boolean isJunior) {
        TrainingResult trainingResults = new TrainingResult(timeResult, training, discipline, date, firstName, isJunior);
        trainingResult.add(trainingResults);
        return trainingResults;
    }

    public void sortTopFive () {
        Collections.sort(trainingResult, new ResultComparator());
    }


}




