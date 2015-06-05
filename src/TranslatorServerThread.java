import java.net.*;
import java.io.*;

public class TranslatorServerThread extends Thread
{  
	private TranslatorServer server    = null;
	private Socket           socket    = null;
	private int              ID        = -1;
	private DataInputStream  streamIn  = null;
   	private DataOutputStream streamOut = null;

   	public TranslatorServerThread(TranslatorServer _server, Socket _socket)
   	{
   		super();
   		server = _server;
   		socket = _socket;
   		ID     = socket.getPort();
   	}
   	public void send(String msg)
   	{
   		try
   		{
   			streamOut.writeUTF(msg);
   			streamOut.flush();
   		}
   		catch(IOException ioe)
   		{
   			System.out.println(ID + " ERROR sending: " + ioe.getMessage());
   			server.remove(ID);
   			interrupt();
   		}
   	}
   	public int getID()
   	{  
   		return ID;
   	}
   	public void run()
   	{
   		System.out.println("Server Thread " + ID + " running.");
   		while (server.clientCount > 0)
   		{
   			try
   			{  
   				//This is from a previous project
   				server.handle(ID, streamIn.readUTF());
   				//server.handle(ID, CCtranslator.translate(streamIn.readUTF()));
   			}
   			catch(IOException ioe)
   			{  
   				System.out.println(ID + " ERROR reading: " + ioe.getMessage());
   				server.remove(ID);
   				interrupt();
   			}
   		}
   	}
   	public void open() throws IOException
   	{
   		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
   		streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
   	}
   	public void close() throws IOException
   	{
   		if (socket != null)    socket.close();
   		if (streamIn != null)  streamIn.close();
   		if (streamOut != null) streamOut.close();
   	}
}
