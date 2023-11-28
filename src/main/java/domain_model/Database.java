package domain_model;

import java.util.ArrayList;

public class Database {
    private ArrayList<Member> database = new ArrayList<>();
    private ArrayList<Coach> coachData = new ArrayList<>();
    private ArrayList<CompetitionResult> competitionResult = new ArrayList<>();
    private ArrayList<TrainingResult> trainingResult = new ArrayList<>();

    private Coach coach = new Coach();
    private boolean changesMade = true;

    public boolean isChangesMade() {
        return changesMade;
    }

    public void setChangesMade(boolean changesMade) {
        this.changesMade = changesMade;
    }


    public Member createMember(String firstName, String lastName, boolean competitive, int age, boolean active, int idNumber) {
        Member member = new Member(firstName, lastName, competitive, age, active, idNumber);
        database.add(member);

        return member;
    }

    public Coach createCoach(String firstName, String lastName, boolean isCompetitive, int age, boolean isActive, int idNumber, boolean isCoach) {
        Coach coach = new Coach(firstName, lastName, isCompetitive, age, isActive, idNumber, isCoach);
        coachData.add(coach);

        return coach;
    }

    public void createTestData() {
    }

    public void createCoachData() {
        createCoach("Abdulmajid", "Moalim", false, 20, true, 260703, true);
        createCoach("Esha", "Ahmed", false, 20, true, 060603, false);
    }

    public void createTournamentResultTestData() {

    }

    public void createPracticeResultTestData() {
    }

    public ArrayList<Member> getMembers() {
        return database;
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

    public void addAll(ArrayList<Member> members) {
        database.addAll(members);
    }

    public boolean deleteMembers(Member member) {
        boolean deleteMember = database.remove(member);
        return deleteMember;
    }

    public ArrayList<Member> searchMembersFirstName(String searchTerm) {

        ArrayList<Member> searchResult = new ArrayList<>();

        for (Member member : database) {
            if (member.getFirstName().toLowerCase().contains(searchTerm.toLowerCase().trim())) {
                searchResult.add(member);
            }
        }
        return searchResult;
    }

    public void setJuniorOrSenior() {
        for (Member member : database) {
            if (member.getAge() < 18) {
                member.setJunior(true);
            } else if (member.getAge() < 18) {
                member.setJunior(false);
            }

        }
    }

    public CompetitionResult createCompetitionResult(double timeResult, boolean practice, int placement, String discipline, String tournamentName, String date, int idNumber) {
        CompetitionResult competitionResults = new CompetitionResult(timeResult, practice, placement, discipline, tournamentName, date, idNumber);
        competitionResult.add(competitionResults);
        return competitionResults;
    }

    public TrainingResult createTrainingResult(double timeResult, boolean practice, String discipline, String date, int membershipNumber, boolean isJunior) {
        TrainingResult trainingResults = new TrainingResult(timeResult, practice, discipline, date, membershipNumber, isJunior);
        trainingResult.add(trainingResults);
        return trainingResults;
    }


}




