package domain_model;

public class Membership {
    private Member member;
    private double payment;


    public Membership (Member member) {
        this.member = member;
    }

    public Membership() {

    }


    public double getMembershipForEachMember() {

        if (member.isActive()) {
            if (member.memberAgeGroup() == ageGroup.UNDER_18) {
                payment = 1000;
            } else if (member.memberAgeGroup() == ageGroup.OVER_18) {
                payment = 1600;
            } else if (member.memberAgeGroup() == ageGroup.OVER_60) {
                payment = 1200;
            }
        } else {
            payment = 500;
        }
        return payment;
    }
}

