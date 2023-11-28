package domain_model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import datasource.FileHandler;

public class Controller {
    private Member member;
    private Database database;
    private FileHandler fileHandler;

    public Controller(Database database) {
        this.database = new Database();
        this.fileHandler = new FileHandler();
        fileHandler.setDatabase(database);

    }

    public void createMember(String firstName, String lastName, boolean swimType, int age, boolean active, int idNumber) {
        database.createMember(firstName, lastName, swimType, age, active, idNumber);
    }
    public void createCompetitionResult(double timeResult, boolean practice, int placement, String discipline, String tournamentName, String date, int membershipNumber) {
        database.createCompetitionResult(timeResult, practice, placement, discipline, tournamentName, date, membershipNumber);
    }

    public void createTrainingResult(double timeResult, boolean practice, String discipline, String date, int membershipNumber, boolean isJunior) {
        database.createTrainingResult(timeResult, practice, discipline, date, membershipNumber, isJunior);
    }
    public void createTestDataCoach() {
        database.createCoachData();
    }

    public void createTestData() {
        database.createTestData();
    }

    public void createTournamentResultTestData() {
        database.createTournamentResultTestData();
    }

    public void createPracticeResultTestData() {
        database.createPracticeResultTestData();
    }


    public void setJuniorOrSenior() {
        database.setJuniorOrSenior();
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


    public boolean deleteMember(Member member) {
        return database.deleteMembers(member);
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
}
