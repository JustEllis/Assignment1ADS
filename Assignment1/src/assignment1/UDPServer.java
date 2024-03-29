/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.net.*;
import java.io.*;
public class UDPServer{
    public static void main(String args[]){ 
    	DatagramSocket aSocket = null;
        String filename = "memberListObject"; 
        Member member = null;
	 FileInputStream fis = null;
	   ObjectInputStream in = null;   
		try{
	    	aSocket = new DatagramSocket(2201);
					// create socket at agreed port
                                        
                                        
                                        
                                        
                fis = new FileInputStream(filename);
                in = new ObjectInputStream(fis);
                member = (Member)in.readObject();
                fis.close();
                int i = member.getMemberN();
                String message = new String("\nMember number: " + member.getMemberN() + "\nFirst name: " + member.getFirstName() + 
                                                "\nlast name: " + member.getLastName() + "\nAddress: " + member.getAddress() + 
                                                "\nPhone number: " + member.getPhoneN() +"\n");
                //String[] message = {"\nMember number: " + member.getMemberN() + "\nFirst name: " + member.getFirstName() + 
                //                                "\nlast name: " + member.getLastName() + "\nAddress: " + member.getAddress() + 
                //                                "\nPhone number: " + member.getPhoneN() +"\n"};
                
                //ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //ObjectOutputStream oos = new ObjectOutputStream(baos);
                //oos.writeObject(message);
                
                byte[] buffer = new byte[1000];
			//byte[] buffer = new byte[1000];
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
		
                }  catch(ClassNotFoundException ex){
					 ex.printStackTrace();
            }finally {if(aSocket != null) aSocket.close();}
    }
}

