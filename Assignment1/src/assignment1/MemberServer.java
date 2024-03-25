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

public class MemberServer {
   public static void main (String args[]) {
    try{
      int serverPort=7896; 
      ServerSocket listenSocket=new ServerSocket(serverPort);
      while(true) {
	 Socket clientSocket=listenSocket.accept();
	 Connection c = new Connection(clientSocket);
         System.out.printf("\nServer waiting on: %d for client from %d ",
                 listenSocket.getLocalPort(), clientSocket.getPort() );
      }
    } catch(IOException e){
 	System.out.println("Listen :"+e.getMessage());
      }
    }
}
//This class is to be used by TcpServer class, not public.
class Connection extends Thread {
      DataInputStream in;
      DataOutputStream out;
      Socket clientSocket;
      public Connection (Socket aClientSocket) {
        try {
          clientSocket = aClientSocket;
          in=new DataInputStream( 
                    clientSocket.getInputStream());
          out=new DataOutputStream(
                    clientSocket.getOutputStream());
          this.start();
        } catch(IOException e){
           System.out.println("Connection:" +e.getMessage());
          }
      }
     public void run(){
        try { // an echo server
           String data = in.readUTF();
         // System.out.println(data);
          out.writeUTF("Server received: "+data);

        }catch(EOFException e) {
             System.out.println("EOF:"+e.getMessage());
        }
        catch(IOException e){
           System.out.println("IO:"+e.getMessage());
        }
	 
	finally {
	   try {clientSocket.close();
           }
	    catch(IOException e){/*close failed*/
           }
        }
     }
}


