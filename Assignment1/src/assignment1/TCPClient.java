package assignment1;

import java.net.*;
import java.io.*;
import java.util.*;

public class TCPClient {
	public static void main (String args[]) {
            boolean loop = true;
            int memberN = 0;
            Socket s = null;
            int answer;
            while(loop == true){ 
		try{
                        
			int serverPort = 1101;

			s = new Socket("localhost", serverPort);
                        			
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream in = new ObjectInputStream( s.getInputStream());
			
                        Scanner sa=new Scanner(System.in);
                        
                        if(memberN == memberN){
                            memberN++;
                        }
                            
			System.out.println("member number:" + memberN);
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
			//member = (Member)in.readObject();	      
                       
			System.out.println("The Sent Gym Member Data:");
			System.out.println("====================================");
			System.out.println("Member number: " + member.getMemberN());
			System.out.println("First name: " + member.getFirstName());
			System.out.println("last name: " + member.getLastName());
			System.out.println("Address: " + member.getAddress());	
                        System.out.println("Phone number: " + member.getPhoneN());
			System.out.println();
                                  
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
                        System.out.println("Type 1 to make another entry \nType 2 to stop");
			answer=sa.nextInt();
                        //while((answer != 1) || (answer != 2)){
                        if(answer == 1){
                            loop = true;
                        }else if (answer == 2){
                            loop = false;
                        }/*else if ((answer != 1) || (answer != 2)){
                            System.out.println("type 1 or 2");
                        }
                        }*/
                }catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
                //}catch(ClassNotFoundException ex){
		//			 ex.printStackTrace();
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
                
            }
        }
}