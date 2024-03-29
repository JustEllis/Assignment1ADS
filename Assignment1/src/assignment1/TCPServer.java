package assignment1;

import java.net.*;
import java.io.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {

	public static void main (String args[]) {
                
		try{
                        
                        int interval = 2000; //Print to file every 2 seconds

                        java.util.Timer tm = new java.util.Timer(); // using timer from util package

                        //schedule timer to write to file after interval and repeat every interval
                        tm.schedule(new WriteToFile(), interval, interval);
                    
			int serverPort = 1101;
			ServerSocket listenSocket = new ServerSocket(serverPort);
            //System.out.println("TCP Server running...");
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
                                //System.out.printf("Data Recieved",
                                //   listenSocket.getLocalPort(), clientSocket.getPort() );
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
                MemberDataFile memberDataFile = new MemberDataFile();
                String filename = "memberObject";
                FileOutputStream fos = null;
		try {
                        
			Member member = (Member)in.readObject();
                       
                         
                        
                        
			/*
			System.out.println("The Recevied Gym Member Data:");
			System.out.println("====================================");
                        //reads txt file to print to server
                        
                        BufferedReader br = new BufferedReader(new FileReader("memberList.txt"));
                        String line;
                        while ((line = br.readLine()) != null) {
                        System.out.println(line);
                        }
                        */
                        System.out.println("Receving data from client: "+member.getMemberN());
                        memberDataFile.saveMember(member);
                       //String data = in.readUTF();
                        // System.out.println(data);
                        //out.writeUTF("Info has been saved");
                        fos = new FileOutputStream(filename);
                        out = new ObjectOutputStream(fos); 
                        out.writeObject(member);  
                                            
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} catch(ClassNotFoundException ex){
					 ex.printStackTrace();
		}finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}

            }
           
        	
    }


class WriteToFile extends TimerTask implements Serializable{

            //this method is called automatically when the task is scheduled
            public void run() {
                
               
                Member member = null;
                
                String filename1 = "memberObject";
                FileInputStream fis = null;
                ObjectInputStream in = null;
                
                String filename2= "memberListObject";
                FileOutputStream fos = null;
                ObjectOutputStream out = null;
                
               
                 
                try{
                  
                fis = new FileInputStream(filename1);
                in = new ObjectInputStream(fis);
                member = (Member)in.readObject();
                fis.close();
                //int i = member.getMemberN();
                
                //[] memberobj = new Array[i]; 
                //memberobj[i] = member(member.getMemberN(), member.getFirstName(),member.getLastName(),member.getAddress(),member.getPhoneN()); 
                //member = memberobj[i];       
                 
                fos = new FileOutputStream(filename2);
                out = new ObjectOutputStream(fos);
                out.writeObject(member);
                out.close();
                
                
                //System.out.println("Test");
             
                }//end of try block
                catch(IOException ex) //exception handling for file handling
                {
                  ex.printStackTrace();
                }  catch(ClassNotFoundException ex){
					 ex.printStackTrace();
            }
            
    }
          
}
