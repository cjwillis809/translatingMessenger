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
/1.main class provides socket to clientReader and writer
/2. both threads run
/3. messages are sent as strings in the format "[username] message"
/4. server relays message to other user. 
/5. client translates all receieved text to chosen language
*/


//this class is the one that will send messages to the server
class clientWriter implements Runnable{


	private int portn = 0;
	private InetAddress addr = null;
	private PrintWriter output;
    private ObjectInputStream input;
	private Socket socket;
	private String username;
	
	clientWriter(Socket s,String uname){
		socket  = s;
		username = uname;
	} 
	

//	public void setUserName(String uname){
//	output.println(uname);
//	
//
//	}
//	public String getUserName(){
 //		pname = input.readString();
//		return pname;
//	}
	void sendMessage(String s){
		output.println(s);
		output.flush();
	}

			
	public void run(){
		try{	
			output = new PrintWriter(socket.getOutputStream());
		}
		catch(Exception e){
		System.out.println(e);
		}
		Scanner scan = new Scanner(System.in);
		String line;

		try{
	
			while(true){					
				System.out.print(">> ");
				line = scan.nextLine();
				sendMessage("["+username+"]: "+line);
			}
		}	
		catch(Exception e){
			System.out.println(e);
		}	
	}
}