package assignment1;

import java.net.*;
import java.io.*;
import java.util.*;

public class TCPClient {
	public static void main (String args[]) {
		Socket s = null;
                String message ="Saved";
		try{
			int serverPort = 1101;

			s = new Socket("localhost", serverPort);
                        
			ObjectInputStream in = null;
			ObjectOutputStream out =null;

			out =new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream( s.getInputStream());
			
                        Scanner sa=new Scanner(System.in);
                         
			System.out.println("Enter member number:");
			int memberN=sa.nextInt();
			System.out.println("Enter first name");
			String firstName=sa.nextLine();
                        System.out.println("Enter last name");
			String lastName=sa.nextLine();
                        System.out.println("Enter address");
			String address=sa.nextLine();
                        System.out.println("Enter phone number");
			String phoneN=sa.nextLine();
                        
                        Member member = new Member(memberN,firstName,lastName,address,phoneN);
                        out.writeObject(member);
                        //write object ot the server
			//out.writeObject(memberList.txt);
                     
			//read object sent by the server
				      
                        

			System.out.println("The Sent Gym Member Data:");
			System.out.println("====================================");
			System.out.println("Member number: " + member.getMemberN());
			System.out.println("First name: " + member.getFirstName());
			System.out.println("last name: " + member.getLastName());
			System.out.println("Address: " + member.getAddress());	
                        System.out.println("Phone number: " + member.getPhoneN());
			System.out.println();
                        member = (Member)in.readObject();
                        /*	    
			System.out.println("The Received Gym Member Data");
			System.out.println("====================================");
			System.out.println("Member number: " + member1.getMemberN());
			System.out.println("First name: " + member1.getFirstName());
			System.out.println("last name: " + member1.getLastName());
			System.out.println("Address: " + member1.getAddress());	
                        System.out.println("Phone number: " + member1.getPhoneN());
			System.out.println();
                        */
                        
                }catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
                }catch(ClassNotFoundException ex){
					 ex.printStackTrace();
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
                
        }

}