/*
/@Author Colin Cruse
/@version 0.1
/
/
*/
import java.net.InetAddress;
import java.net.Socket;





class client implements Runnable{


	private int port = 0;
	private InetAddress add = null;
	client(InetAddress add, int port){
		this.port = port;
		this.add = add;
	}
	

	void connect(){	
			try{
				Socket socket = new Socket(this.add, this.port);//opening socket to server specified in the args       
			}
			catch(Exception e){
				System.out.println("The Server has refused the connection");
				System.exit(0);
			}
        } 
	
	public void run(){
	this.connect();

	}



















}
