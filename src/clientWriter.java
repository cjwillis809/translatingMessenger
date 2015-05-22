/*
/@Author Colin Cruse
/@version 0.1
/
/
*/
import java.net.InetAddress;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.Scanner;
import java.io.ObjectInputStream;

/*
/Server Client Protocol:
/1. Set username. Client sends username to server. Servers stores the username and associates it with the client socket
/2. Get username. Client requests username from server that the other user is using.
/3 or 4. Send Message to server, server relays to other client 
/3 or 4. receive message from server that was relayed from other client
/
*/


//this class is the one that will send messages to the server
class clientWriter implements Runnable{


	private int portn = 0;
	private InetAddress addr = null;
	private PrintWriter output;
    	private ObjectInputStream input;
	public String pname;
	private Socket socket;
	clientWriter(InetAddress tdd, int port){
		portn = port;
		addr = tdd;
	}
	

	void connect(){	
		try{
			System.out.println(addr+":"+portn);
			socket = new Socket(addr, portn);//opening socket to server specified in the args       
			output= new PrintWriter(socket.getOutputStream(),true);
	                //input= new ObjectInputStream( socket.getInputStream());
	
//			output.println("Hey");
			//System.out.println("cw connected");

		}
		catch(Exception e){
			System.out.println("The Server has refused the connection cw");
			System.exit(0);
			}
		

        } 
	

	public void setUserName(String uname){
	output.println(uname);
	

	}
//	public String getUserName(){
 //		pname = input.readString();
//		return pname;
//	}
	void sendMessage(String s){
		output.println(s);
		output.flush();
	}

			
	public void run(){
		connect();
		Scanner scan = new Scanner(System.in);
		String line;

	try{

		while(true){					
			System.out.print(">> ");
			line = scan.nextLine();
			sendMessage(line);
		}
	}	
	catch(Exception e){}
		//while(true){

		//	System.out.println(">> ");
		//		line = scan.nextLine();
		//	utput.println(line);
		//}
	}
}


