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
                                                    
			System.out.println("The Sent Gym Member Data:");
			System.out.println("====================================");
			System.out.println("Member number: " + member.getMemberN());
			System.out.println("First name: " + member.getFirstName());
			System.out.println("last name: " + member.getLastName());
			System.out.println("Address: " + member.getAddress());	
                        System.out.println("Phone number: " + member.getPhoneN());
			System.out.println();
                                  
                        
                        System.out.println("Type 1 to make another entry \nType 2 to stop");
			answer=sa.nextInt();
                        
                        if(answer == 1){
                            loop = true;
                        }else if (answer == 2){
                            loop = false;
                        }
                }catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());               
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
                
            }
        }
}