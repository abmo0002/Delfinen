package domain_model;

public class TrainingResult {
    private String firstName;
    private double timeResult;
    private boolean isTraining;
    private String discipline;
    private int date;
    private boolean isJunior;

    public TrainingResult(double timeResult, boolean isTraining, String discipline, int date, String firstName, boolean isJunior) {
        this.timeResult = timeResult;
        this.isTraining = isTraining;
        this.discipline = discipline;
        this.date = date;
        this.firstName = firstName;
        this.isJunior = isJunior;
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

    public int getDate() {
        return date;
    }

    public String getDiscipline() {
        return discipline;
    }

    public boolean isJunior() {
        return isJunior;
    }

    public boolean isTraining() {
        return isTraining;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setTraining(boolean training) {
        isTraining = training;
    }


    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setJunior(boolean junior) {
        isJunior = junior;
    }

    public String toString() {
        return ("Fornavn: " + firstName + "Tid: " + timeResult + "\n" + "Disciplin: " + discipline + "\n" +
                "\n" + "Dato: " + date + "\n");
    }

}
