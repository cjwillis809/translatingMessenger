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


	private int port = 0;
	private InetAddress add = null;
	private PrintWriter output;
    	private BufferedReader input;
	public String pname;
	private Socket socket;
	clientWriter(InetAddress tdd, int port){
		this.port = port;
		this.add = add;
	}
	

	void connect(){	
		try{
			socket = new Socket(this.add, this.port);//opening socket to server specified in the args       
			output= new PrintWriter(socket.getOutputStream(),true);
	                input= new BufferedReader( new InputStreamReader( socket.getInputStream()));

		}
		catch(Exception e){
			System.out.println("The Server has refused the connection");
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

	}

			
	public void run(){
		this.connect();
		Scanner scan = new Scanner(System.in);
		while(true){
			System.out.print(">> ");
			String message = scan.nextLine();
			this.sendMessage(message);
		}
	}

}
