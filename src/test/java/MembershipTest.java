
import domain_model.Member;
import domain_model.Membership;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MembershipTest {
@Test
    public void testMembership() {
    Membership membership = new Membership();
    Member activeUnder18Member = new Member("Kim", "Larsen", true, 20, true, 221203);
    assertEquals(1000, membership.getMembershipForEachMember());
    Member activeOver18Member = new Member("Kim", "Madsen", true, 70, true, 070551);
    assertEquals(1600, membership.getMembershipForEachMember());
    Member activeOver60Member = new Member("Kim", "Petersen", false, 20, false, 270803);
    assertEquals(1200, membership.getMembershipForEachMember());
    Member inactiveMember = new Member("Kim","Kimsen",false,20,false,270803);
}
}


