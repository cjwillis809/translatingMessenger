import java.util.Scanner;

import com.memetix.mst.language.Language;
import java.net.InetAddress;
import java.net.Socket;
import com.memetix.mst.translate.Translate;



public class Translator {
		
	public static void listLanguages() throws Exception{
		System.out.println("\n---\nLanguages available: \n");
        for(Language lang : Language.values())
            System.out.println(lang.name());
	}
	
	 public static Language validate(String language)
	 {
		 for (Language item : Language.values())
			 if (item.name().equalsIgnoreCase(language))
				 return item;
	        return null;
	}

	public static boolean isValidLanguage(String language)
	{
		return (validate(language) != null);
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
		
		clientWriter cw = new clientWriter(serverIP, ss);//creates a connection to the server for sending messages
		clientReader cr = new clientReader(serverIP, ss);//creates a connection to ther server for reading messages
		Thread crt = new Thread(cr);
		Thread cwt = new Thread(cw);
		cwt.run();
		crt.run();
		
		CCtranslator t = new CCtranslator("TranslatingMessenger","YY4h0AMA9M4q/3Ty52plCUCU998eE3qHAmwk/7Y8P4c=");
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to the Translating Messenger");
		
		System.out.print("\nEnter your username: ");
		String username = scan.nextLine();
		cw.setUserName(username);//sends the username to the server
		
		System.out.print("Enter your language (type -o for options): ");
		String lang1 = scan.nextLine();
		if (lang1.equals("-o")){
			listLanguages();
		}
		while (!isValidLanguage(lang1)){
			System.out.print("\nEnter your language (type -o for options): ");
			lang1 = scan.nextLine();
		}
		
		t.setFromLang(Language.valueOf(lang1.toUpperCase()));
		
		t.setToLang(Language.SPANISH);
		
		String yourInfo = "Your language: " + t.from.name();
		System.out.println(Translate.execute(yourInfo,Language.ENGLISH,t.from));
		
		String theirInfo = "\n[User] has entered the conversation \nTheir language: " + t.to.name();
		System.out.println(Translate.execute(theirInfo,Language.ENGLISH,t.from));
		
		System.out.print("\n[" + username + "]: "); 
		String s = scan.nextLine();
		String r = t.translate(s);
		System.out.println(r);
		
		scan.close();
	}

}
