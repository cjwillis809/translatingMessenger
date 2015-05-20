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
class clientReader implements Runnable {


	private int portn = 0;
	private InetAddress addr = null;
	private PrintWriter output;
    	private BufferedReader input;
	public String pname;
	private Socket socket;
	clientReader(InetAddress tdd, int port)throws IOException{
		portn = port;
		addr = tdd;
	}
	

	void connect()throws IOException{
		try{	
	//		System.out.println(port);
			socket = new Socket(addr,portn);//opening socket to server specified in the args
			       
		} catch(Exception e){
				System.out.println("The Server has refused the connection cr");
				System.exit(0);
			}
		output= new PrintWriter(socket.getOutputStream(),true);
	        input= new BufferedReader( new InputStreamReader( socket.getInputStream()));
        } 
	

//	public void setUserName(String uname){
//	output.println(uname);
	

//	}
	public String getUserName()throws IOException{
		pname = input.readLine();
		return pname;
	}
//	void sendMessage(String s){
//		ouput.writeUTF(s);

//	}

			
	public void run(){
		try{
			connect();
		//	this.getUserName();
		}
		catch(Exception e){
		System.out.println(e);
		}
		//Scanner scan = new Scanner(System.in);
		String line;//received
		while(true){
			try{	
				String m = input.readLine();
				if(m!=null &&m.length() > 0){
					System.out.println(">> "+input.readLine());
				}
			}
			catch(Exception e)
			{}
		}
//		while(true){
//			try{//loop reading inputs
//				while((line = input.readLine()) != null && line.length() > 0){
//					System.out.print(">> ");
//					System.out.println(line);
//				}
//			}
//			catch(Exception e){
//				System.out.println(e);
//			}
	//	}
	}

}

