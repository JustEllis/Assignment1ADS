package assignment1;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author EllisF
 */

public class TCPClient {
    public static void main (String args[]) {
        boolean loop = true;
        int memberN = 0;
        Socket s = null;
        String message = "Status: ";
        int answer;
        //loop for multipule entries 
        while(loop == true){ 
            try{                       
                int serverPort = 1101;
                //Create a TCP socket
                s = new Socket("localhost", serverPort);               			
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream in = new ObjectInputStream( s.getInputStream());
                DataInputStream datain =new DataInputStream(s.getInputStream());
                DataOutputStream dataout =new DataOutputStream(s.getOutputStream());
                Scanner sa=new Scanner(System.in);        
                if(memberN == memberN){
                    memberN++;
                }
                //data input  
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
                
                //format of output of entered details
                System.out.println("Gym Registration Details for member number: "+memberN);
                System.out.println("=================================================");
                System.out.println(member.getMemberN() +" :  "+ member.getFirstName() +" :  "+ 
                member.getLastName() +" :  "+ member.getAddress() +" :  "+ member.getPhoneN());
                System.out.println();
                
                //displays string confriming save of entry
                dataout.writeUTF(message);
                String data = datain.readUTF();                       
                System.out.println(data);      
                System.out.println();   
                
                //loop for incase of invalid response
                boolean miniloop = true;
                while(miniloop == true){
                    //to prompt user to continue or stop loop
                    System.out.println("Type 1 to make another entry \nType 2 to stop");
                    answer=sa.nextInt();
                    if(answer == 1){
                        loop = true;
                        miniloop = false;
                    }else if (answer == 2){
                        loop = false;
                        miniloop = false;
                    }else if (answer != 1 || answer != 2){
                        System.out.println("Invalid Response");
                        miniloop = true;    
                    }
                }
            }//end of try block
            catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
            }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
            }catch (IOException e){System.out.println("readline:"+e.getMessage());               
            }finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}                
        }//end of loop
    }//end of main
}//end of TCPClient