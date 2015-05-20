//This class reads from the socket

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
/*
/Server Client Protocol:
/1. Set username. Client sends username to server. Servers stores the username and associates it with the client socket
/2. Get username. Client requests username from server that the other user is using.
/3 or 4. Send Message to server, server relays to other client 
/3 or 4. receive message from server that was relayed from other client
/
*/


//this class is the one that will send messages to the server
class clientReader implements Runnable{


	private int port = 0;
	private InetAddress add = null;
	private DataOutputStream output;
    	private DataInputStream input;
	public String pname;
	private Socket socket;
	clientReader(InetAddress tdd, int port){
		this.port = port;
		this.add = add;
	}
	

	void connect(){	
		try{
			socket = new Socket(this.add, this.port);//opening socket to server specified in the args       
		}
		catch(Exception e){
			System.out.println("The Server has refused the connection");
			System.exit(0);
			}
		output= new DataOutputStream(socket.getOutputStream());
	        input= new BufferedReader( new InputStreamReader( socket.getInputStream()));
        } 
	

	public void setUserName(String uname){
	output.printUTF(uname);
	

	}
	public String getUserName(){
 		pname = input.readString();
		return pname;
	}
	void sendMessage(String s){
		ouput.writeUTF(s);

	}

			
	public void run(){
		this.connect();
		Scanner scan = new Scanner(System.in);
		String r;//received
		while(r = input.readLine !=null){
			System.out.print(">> ");
			System.out.println(r);
		}
	}

}

