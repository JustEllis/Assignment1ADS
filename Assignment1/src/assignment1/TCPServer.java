package assignment1;

import java.net.*;
import java.io.*;

public class TCPServer {
	public static void main (String args[]) {
		try{
			int serverPort = 1101;
			ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("TCP Server running...");
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
                                System.out.printf("Data Recieved",
                                    listenSocket.getLocalPort(), clientSocket.getPort() );
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
                        memberDataFile.saveMember(member);
                       //String data = in.readUTF();
                        // System.out.println(data);
                        //out.writeUTF("Info has been saved");

		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} catch(ClassNotFoundException ex){
					 ex.printStackTrace();
		}finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}

	}
}

			






