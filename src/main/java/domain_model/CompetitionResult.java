package domain_model;

public class CompetitionResult {

    private String firstName;
    private double timeResult;
    private boolean isTraining;
    private int placement;
    private String discipline;
    private String tournamentName;
    private int date;

    public CompetitionResult(double timeResult, boolean isTraining, int placement, String discipline, String tournamentName, int date, String firstName) {
        this.timeResult = timeResult;
        this.isTraining = isTraining;
        this.placement = placement;
        this.discipline = discipline;
        this.tournamentName = tournamentName;
        this.date = date;
        this.firstName = firstName;

    }

    public String getFirstName() {
        return firstName;
    }

    public double getResult() {
        return timeResult;
    }

    public void setTimeResult(double timeResult) {
        this.timeResult = timeResult;
    }

    public boolean isTraining() {
        return isTraining;
    }

    public int getPlacement() {
        return placement;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public int getDate() {
        return date;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }


    public void setTraining(boolean training) {
        isTraining = training;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }


    public String toString() {
        return ("Fornavn: " + firstName + "\n" + "Tid: " + timeResult + "\n" +
                "Placering : " + placement + "\n" + "Disciplin: " + discipline + "\n" +
                "Dato: " + date + "\n" + "Stævne " + tournamentName + "\n");
    }
}
