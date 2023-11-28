package domain_model;

public class Member {
    private String firstName;
    private String lastName;
    private int age;
    private boolean isActive;
    private boolean isCompetitive;
    private int idNumber;
    private boolean isJunior;

    public Member(String firstName, String lastName, boolean isCompetitive, int age, boolean isActive, int idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isActive = isActive;
        this.isCompetitive = isCompetitive;
        this.idNumber = idNumber;

    }

    public Member() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isCompetitive() {
        return isCompetitive;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public boolean isJunior() {
        return isJunior;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setCompetitive(boolean competitive) {
        isCompetitive = competitive;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setJunior(boolean junior) {
        isJunior = junior;
    }

    public ageGroup memberAgeGroup() {

        if (age < 18) {
            return ageGroup.UNDER_18;
        } else if (age < 60) {
            return ageGroup.OVER_18;
        } else {
            return ageGroup.OVER_60;
        }
    }

    public void setJuniorOrSenior() {
        if (age < 18) {
            setJunior(true);
        } else {
            setJunior(false);
        }
    }

    public String toString() {
        return ("Fornavn: " + firstName + "\n" + "Efternavn: " + lastName + "\n" + "Alder: " + age + "\n" + "Aktiv: " + isActive + "\n" + "Konkurrencesvømmer: " + isCompetitive + "\n"
                + "MedlemID " + idNumber + "\n" + "Aldergruppe " + isJunior + "\n");
    }

}

