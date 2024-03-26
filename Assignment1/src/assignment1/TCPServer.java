/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author Mary
 */
import java.net.*;
import java.io.*;

public class TCPServer {
	public static void main (String args[]) {

		try{
			int serverPort = 8888;
			ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("TCP Server running...");
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

            System.out.println("Sent book with cost computed");
            System.out.println("");
            try {clientSocket.close();}catch (IOException e){/*close failed*/}


	}
}



