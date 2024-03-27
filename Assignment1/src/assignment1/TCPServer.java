package assignment1;

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPServer {
	public static void main (String args[]) {
		try{
                    
       
                        
			int serverPort = 1101;
			ServerSocket listenSocket = new ServerSocket(serverPort);
            //System.out.println("TCP Server running...");
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
                                //System.out.printf("Data Recieved",
                                //   listenSocket.getLocalPort(), clientSocket.getPort() );
			}

		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
        
}

class Connection extends Thread {

	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;
        
        private PrintWriter prn = null;
        
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
                
		try {
                        
			Member member = (Member)in.readObject();	
			/*
			System.out.println("The Recevied Gym Member Data:");
			System.out.println("====================================");
                        //reads txt file to print to server
                        
                        BufferedReader br = new BufferedReader(new FileReader("memberList.txt"));
                        String line;
                        while ((line = br.readLine()) != null) {
                        System.out.println(line);
                        }
                        */
                        System.out.println("Receving data from client: "+member.getMemberN());
                        memberDataFile.saveMember(member);
                       //String data = in.readUTF();
                        // System.out.println(data);
                        //out.writeUTF("Info has been saved");
                        
                        
                prn = new PrintWriter(new FileOutputStream(new File("memberList.txt"), true /* append = true */));
                //Read system date
                Date date = new Date();
                //Date formatter
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				//foramtt date to the given mask
				String dateString=formatter.format(date);
				// Display on the screen
                System.out.println( "Current time of the day using Date - 12 hour format: " + dateString);
                //Write the same contents to the file
                prn.println("Current time of the day using Date - 12 hour format: " + dateString);
                //close the file
                prn.close();
                                               

		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} catch(ClassNotFoundException ex){
					 ex.printStackTrace();
		}finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}

	}
}

			






