package assignment1;

public class MemberTest {
    public static void main(String[] args) {
        MemberDataFile memberDataFile = new MemberDataFile();

        // Creating two book objects
        Member member1 = new Member(1, "Ellis", "Floriani", "10 streety street", 0406090406);
        Member member2 = new Member("Firewalls and Network Security", "Greg Holden", "Thomson Course Technology", 2005,
                "0-619-13039-3");

        // Saving books to the file
        memberDataFile.saveMember(member1);
        memberDataFile.saveMember(member2);

        // Reading back and displaying books from the file
        memberDataFile.displayMembers();
    }
}
