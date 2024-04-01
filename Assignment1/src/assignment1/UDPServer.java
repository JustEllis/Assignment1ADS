package assignment1;
import java.net.*;
import java.io.*;

/**
 *
 * @author EllisF
 */

public class UDPServer{
    public static void main(String args[]){ 
    	DatagramSocket aSocket = null;
        String filename = "memberListObject"; 
        
	FileInputStream fis = null;
	ObjectInputStream in = null;   
	try{
	    aSocket = new DatagramSocket(2201);
            // create socket at agreed port
                 
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            Object member = (Object)in.readObject();
            
            fis.close();
            //String[] message = null;
            //String info = member.ToString;
            
            String message = new String("|First Name    |Last Name  |Address        |Phone Number       |\n"
                + "====================================================================\n" + 
                member);
            
            //"|" + member.getFirstName() + " |" + member.getLastName() + 
            //"   |" + member.getAddress() + "    |" + member.getPhoneN() +"\n");

            byte[] buffer = new byte[1000];
            while(true){
 		DatagramPacket request = new DatagramPacket(buffer, buffer.length);
  		aSocket.receive(request);  
		System.out.println("Client Requests file: "+ filename);            
                DatagramPacket reply = new DatagramPacket(message.getBytes(), message.getBytes().length, 
    		request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }finally {if(aSocket != null) aSocket.close();}
    }
}
