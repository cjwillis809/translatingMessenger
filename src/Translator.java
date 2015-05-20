import java.util.Scanner;

import com.memetix.mst.language.Language;
import java.net.InetAddress;
import java.net.Socket;



public class Translator {
		
	public static void listLanguages() throws Exception{
		System.out.println("\n---\nLanguages available: \n");
        for(Language lang : Language.values())
            System.out.println(lang.name());
	}
	
	public static void main(String[] args) throws Exception {
		int ss = 0;	
		InetAddress serverIP = null;
		if(args.length != 2){
			System.out.println("Usage: java -jar translatingMessenger.jar {server ip address}{server socket}");
			System.exit(0);

		}
		else{
			ss = Integer.parseInt(args[1]);
			serverIP = InetAddress.getByName(args[0]);


		}
		
		client c = new client(serverIP, ss);//handles the connection to the server
		Thread cl = new Thread(c);
		c.run();
		
		CCtranslator t = new CCtranslator("TranslatingMessenger","YY4h0AMA9M4q/3Ty52plCUCU998eE3qHAmwk/7Y8P4c=");
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to the Translating Messenger");
		
		System.out.print("\nEnter your username: ");
		String username = scan.nextLine();
		c.setUserName(username);//sends the username to the server
		System.out.print("Enter your language (type -o for options): ");
		String lang1 = scan.nextLine();
		if (lang1.equals("-o")){
			listLanguages();
			System.out.print("\nEnter your language (type -o for options): ");
			lang1 = scan.nextLine();
		}
		t.setFromLang(Language.valueOf(lang1.toUpperCase()));
		System.out.println("Your language: " + t.from.name());
		
		t.setToLang(Language.SPANISH); //temporary
		
		System.out.println("\n[User] has entered the conversation \nTheir language: " + t.to.name()); //translate!!!!!!
		
		System.out.print("\n[You]: "); 
		String s = scan.nextLine();
		String r = t.translate(s);
		System.out.println(r);
		
		scan.close();
	}

}
