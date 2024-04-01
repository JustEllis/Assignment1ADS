package assignment1;
import java.net.*;
import java.io.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EllisF
 */

public class TCPServer {
    public static void main(String args[]) {
        try {
            int interval = 2000; // Print to file every 2 seconds
            java.util.Timer tm = new java.util.Timer(); // using timer from util package
            // schedule timer to write to file after interval and repeat every interval
            tm.schedule(new WriteToFile(), interval, interval);
            //Create a TCP socket
            int serverPort = 1101;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }//end of main
}//end of TCPServer

class Connection extends Thread {
    ObjectInputStream in;
    ObjectOutputStream out;
    DataInputStream datain;
    DataOutputStream dataout;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {

        try {
            clientSocket = aClientSocket;
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            datain = new DataInputStream(clientSocket.getInputStream());
            dataout = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        MemberDataFile memberDataFile = new MemberDataFile();
        Member member = null;
        try {
            //reads object
            member = (Member) in.readObject();
            //displays current client number being entered by TCPClient
            System.out.println("Receving data from client: " + member.getMemberN());
            memberDataFile.saveMember(member);
            String data = datain.readUTF();
            dataout.writeUTF(data + "Entry Saved");
        }//end of try block
        catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                /* close failed */}
        }
    }
}//end of connection class

class WriteToFile extends TimerTask implements Serializable {
    @Override
    public void run() {
        try {
            Object member = null;
            BufferedReader in = new BufferedReader(new FileReader("memberList.txt"));
            String memberData = in.readLine();
            member = memberData;
            in.close();

            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("memberListObject"));
            outputStream.writeObject(member);
            outputStream.close();

            // a print to test if timer is working correctly
            // System.out.println(test);
        } // end of try block
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}//end of WriteToFile class

class MemberDataFile {
    int memberN;

    public void saveMember(Member member) {
        memberN = member.getMemberN();
        try (BufferedWriter memberData = new BufferedWriter(new FileWriter("memberList.txt", true))) {
            memberData.write(member.getMemberN() + " : " + member.getFirstName() +
                    " : " + member.getLastName() + " : " + member.getAddress() +
                    " : " + member.getPhoneN() + " \n ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}//end of MemberDataFile