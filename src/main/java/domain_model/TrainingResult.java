package domain_model;

public class TrainingResult {
    private int idNumber;
    private double timeResult;
    private boolean isPractice;
    private String discipline;
    private String date;
    private boolean isJunior;

    public TrainingResult(double timeResult, boolean isPractice, String discipline, String date, int membershipNumber, boolean isJunior) {
        this.timeResult = timeResult;
        this.isPractice = isPractice;
        this.discipline = discipline;
        this.date = date;
        this.idNumber = membershipNumber;
        this.isJunior = isJunior;
    }

    public int getIdNumber() {
        return idNumber;
    }
    public double getResult() {
        return timeResult;
    }

    public void setTimeResult(double timeResult) {
        this.timeResult = timeResult;
    }

    public String getDate() {
        return date;
    }

    public String getDiscipline() {
        return discipline;
    }

    public boolean isJunior() {
        return isJunior;
    }

    public boolean isPractice() {
        return isPractice;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setPractice(boolean practice) {
        isPractice = practice;
    }


    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setJunior(boolean junior) {
        isJunior = junior;
    }

    public String toString() {
        return ("Medlemsnummer: " + idNumber + "Tid: " + timeResult + "\n" + "Disciplin: " + discipline + "\n" +
                "\n" + "Dato: " + date + "\n");
    }

}
