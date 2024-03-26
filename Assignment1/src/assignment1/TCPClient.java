package assignment1;

import java.net.*;
import java.io.*;
import java.util.*;

public class TCPClient {
	public static void main (String args[]) {
		Socket s = null;
                MemberDataFile memberDataFile = new MemberDataFile();
		try{
                        
			int serverPort = 8888;

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
			memberDataFile.saveMember(member);
                        //write object ot the server
			//out.writeObject(memberList.txt);
                        //memberDataFile.saveMember();

			//read object sent by the server
			

			System.out.println("Membership Details:");
			System.out.println("====================================");
			System.out.println(memberN + firstName + lastName);

		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     
        
        
        
        }

}