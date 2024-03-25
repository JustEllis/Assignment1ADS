package assignment1;
import java.io.Serializable;

/**
 *
 * @author wiekiang
 */

public class Member implements Serializable {
    int memberN;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneN;
    

    // Constructor
    public Member(int memberN, String firstName, String lastName, String address, String phoneN) {
        this.memberN = memberN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneN = phoneN;
    }

    // Getter and setter methods for each field
    public int getMemberN() {
        return memberN;
    }

    public void setMemberN(int memberN) {
        this.memberN = memberN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneN() {
        return phoneN;
    }

    public void setPhoneN(String phoneN) {
        this.phoneN = phoneN;
    }

    
}
  