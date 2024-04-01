package assignment1;
import java.net.*;
import java.io.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author EllisF
 */

public class UDPClient{   
    private static String username;
    private static String hostName;
    public static void main(String args[]){ 		
    //The first argument is the message to send to the server. 
    //The second argument is the name of the server.	
    DatagramSocket aSocket = null;
    Scanner input = new Scanner(System.in);
    System.out.print("Please enter your name: ");
    username = input.nextLine();
    //a prompt to dsiplay list for user
    boolean loop = true;
    int entry;               
    while(loop == true){		
        System.out.println("Type 1 to display updated list");
        entry = input.nextInt();
        if(entry != 1){
            loop = true;
            System.out.println("Invalid Response");
        }else{
            loop = false;      
        }
    }                
    hostName = "localhost";  //localhost
    try {		
        //Create a UDP socket
	aSocket = new DatagramSocket();		
	//Prepare the message to send to the server
	byte [] m = username.getBytes();
	InetAddress aHost = InetAddress.getByName( hostName);		
	//Agreed port
	int serverPort = 2201;		                                                 		
	//Create a UDP datagram
	DatagramPacket request =
        new DatagramPacket(m,  username.length(), aHost, serverPort);		
	//Send the request
	aSocket.send(request);		
	//Prepare a buffer to receive the reply from the server
	byte[] buffer = new byte[1000];		
	//Waiting for reply
	DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
	aSocket.receive(reply);		
	//Display the reply
	String response=new String(reply.getData(), 0, reply.getLength());
	System.out.print("Server Response:\n"+response);			
    }//end of try block
    catch (SocketException e){System.out.println("Socket: " + e.getMessage());
    }catch (IOException e){System.out.println("IO: " + e.getMessage());
    }finally {if(aSocket != null) aSocket.close();}
    }		      	
}//end of UDPClient

