package assignment1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemberDataFile {
    int memberN;
    public void saveMember(Member member) {
        memberN = member.getMemberN();
        try (BufferedWriter bookData = new BufferedWriter(new FileWriter("Member"+memberN+".txt", true))) {
            bookData.write("Member Number:" + member.getMemberN() + "\nFirst Name: " + member.getFirstName() +
                    "\nLast Name: " + member.getLastName() + "\nAddress: " + member.getAddress() +
                    "\nPhone: " + member.getPhoneN() + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayMembers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Member"+memberN+".txt"))) {
            System.out.println("No more records...\n");

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
