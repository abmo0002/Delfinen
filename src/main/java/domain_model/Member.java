package domain_model;

public class Member {
    private CompetitionResult result;
    private Membership membership;

    private String firstName;
    private String lastName;
    private boolean isCompetitive;
    private int age;
    private boolean isActive;
    private int birthday;
    private boolean isJunior;

    public Member() {
    }

    public Member(String firstName, String lastName, boolean isCompetitive, int age, boolean isActive, int birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isCompetitive = isCompetitive;
        this.age = age;
        this.isActive = isActive;
        this.birthday = birthday;
        this.membership = new Membership(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isCompetitive() {
        return isCompetitive;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getBirthday() {
        return birthday;
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

    public void setCompetitive(boolean competitive) {
        this.isCompetitive = competitive;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public void setJunior(boolean junior) {
        this.isJunior = junior;
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

    public Membership getMembership() {
        return membership;
    }

    public double getPayment() {
        return getMembership().getMembershipForEachMember();
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
                + "Fødselsdag " + birthday + "\n" + "Aldergruppe " + isJunior + "\n");
    }

}

