package domain_model;

public class CompetitionResult {

    private int idNumber;
    private double timeResult;
    ;
    private boolean isPractice;
    private int placement;
    private String discipline;
    private String tournamentName;
    private String date;

    public CompetitionResult(double timeResult, boolean isPractice, int placement, String discipline, String tournamentName, String date, int idNumber) {
        this.timeResult = timeResult;
        this.isPractice = isPractice;
        this.placement = placement;
        this.discipline = discipline;
        this.tournamentName = tournamentName;
        this.date = date;
        this.idNumber = idNumber;

    }

    public int getIdNumber() {
        return idNumber;
    }

    public double getTimeResult() {
        return timeResult;
    }

    public boolean isPractice() {
        return isPractice;
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

    public String getDate() {
        return date;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }

    public void setTimeResult(double timeResult) {
        this.timeResult = timeResult;
    }

    public void setPractice(boolean practice) {
        isPractice = practice;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }


    public String toString() {
        return ("Medlemsnummer: " + idNumber + "\n" + "Tid: " + timeResult + "\n" +
                "Placering : " + placement + "\n" + "Disciplin: " + discipline + "\n" +
                "Dato: " + date + "\n" + "Stævne " + tournamentName + "\n");
    }
}
