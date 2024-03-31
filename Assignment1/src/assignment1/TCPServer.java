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

public class TCPServer {

	public static void main (String args[]) {
                
		try{                        
                        int interval = 2000; //Print to file every 2 seconds

                        java.util.Timer tm = new java.util.Timer(); // using timer from util package

                        //schedule timer to write to file after interval and repeat every interval
                        tm.schedule(new WriteToFile(), interval, interval);
                    
			int serverPort = 1101;
			ServerSocket listenSocket = new ServerSocket(serverPort);
            
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);                                
			}

		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
        }                    
}

class Connection extends Thread {
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;
         
	public Connection (Socket aClientSocket) {

		try {
			clientSocket = aClientSocket;
			in = new ObjectInputStream( clientSocket.getInputStream());
			out =new ObjectOutputStream( clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	
        
       
        }
        
	public void run(){
                MemberDataFile memberDataFile = new MemberDataFile();              
                Member member = null;
		try {
                        
			member = (Member)in.readObject();
                                
                        System.out.println("Receving data from client: "+member.getMemberN());
                        memberDataFile.saveMember(member);
                       
                        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("memberObject", true));  
                        outputStream.writeObject(member);    
       
                        
		} catch(EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} catch(ClassNotFoundException ex){
					 ex.printStackTrace();
		}finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}

            }
           
        	
    }


class WriteToFile extends TimerTask implements Serializable{
            @Override            
            public void run() {           
                try{
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("memberObject"));
                Member member = (Member) inputStream.readObject();
                inputStream.close();
                    
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("memberListObject"));
                outputStream.writeObject(member);
                outputStream.close(); 
                //System.out.println("Test");
                }//end of try block
                 catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
} 
           class MemberDataFile {
    int memberN;
    public void saveMember(Member member) {
        memberN = member.getMemberN();
        try (BufferedWriter memberData = new BufferedWriter(new FileWriter("memberList.txt", true))) {
            memberData.write(member.getMemberN() + " : " + member.getFirstName() +
                    " : " + member.getLastName() + " : " + member.getAddress() +
                    " : " + member.getPhoneN() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }          
}