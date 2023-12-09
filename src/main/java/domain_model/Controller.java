package domain_model;

import datasource.FileHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Controller {
    private Database database;
    private FileHandler fileHandler;
    private Member member;


    public Controller() {
        this.database = new Database();
        this.fileHandler = new FileHandler();
        fileHandler.setDatabase(database);
    }

    public void createMember(String firstName, String lastName, boolean swimType, int age, boolean activity, int birthday) {
        database.createMember(firstName, lastName, swimType, age, activity, birthday);
    }

    public void createTestData() {
        database.createTestData();
    }

    public void createTestDataCoach() {
        database.createCoachData();
    }

    public void createTournamentResultTestData() {
        database.createTournamentResultTestData();
    }

    public void createTrainingResultTestData() {
        database.createTrainingResultTestData();
    }

    public void createCompetitionResult(double timeResult, boolean training, int placement, String discipline, String tournamentName, int date, String firstName) {
        database.createCompetitionResult(timeResult, training, placement, discipline, tournamentName, date, firstName);
    }

    public void createTrainingResult(double timeResult, boolean training, String discipline, int date, String firstName, boolean isJunior) {
        database.createTrainingResult(timeResult, training, discipline, date, firstName, isJunior);
    }

    public ArrayList<Member> getMembers() {
        return database.getMembers();
    }

    public ArrayList<Coach> getCoaches() {
        return database.getCoach();
    }

    public ArrayList<TrainingResult> getTrainingResults() {
        return database.getTrainingResult();
    }

    public ArrayList<CompetitionResult> getCompetitionResults() {
        return database.getCompetitionResult();
    }

    public ArrayList<Member> searchMembersFirstName(String searchTerm) {
        return database.searchMembersFirstName(searchTerm);
    }

    public void saveMembers() {
        try {
            fileHandler.saveData(database.getMembers());
        } catch (FileNotFoundException e) {
        }
    }

    public void loadMembers() {
        try {
            ArrayList<Member> members = fileHandler.loadData();
            database.addAll(members);
        } catch (FileNotFoundException e) {
        }
    }

    public void setJuniorOrSenior() {
        database.setJuniorOrSenior();
    }

    public boolean deleteMember(Member member) {
        return database.deleteMembers(member);
    }

    public double getTotalPayment() {
        return database.getTotalPayment();
    }

    public void sortTopFive () {
        database.sortTopFive();
    }
}
