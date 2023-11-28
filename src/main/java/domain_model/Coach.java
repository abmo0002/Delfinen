package domain_model;

public class Coach extends Member {
    private boolean isCoach;

    public Coach(String firstName, String lastName, boolean isCompetitive, int age, boolean isActive, int idNumber, boolean isCoach) {
        super(firstName, lastName, isCompetitive, age, isActive, idNumber);
        this.isCoach = isCoach;

    }

    public Coach() {
    }

    public boolean isCoach() {
        return isCoach;
    }

}
