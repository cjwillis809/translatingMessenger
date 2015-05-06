//this is example code showing how the CCtranslator works

import java.util.Scanner;


public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CCtranslator t = new CCtranslator("TranslatingMessenger","YY4h0AMA9M4q/3Ty52plCUCU998eE3qHAmwk/7Y8P4c=");
		System.out.println("Languages available are: English, French, Italian, and Arabic");
		System.out.println("What language do you want to translate from?(options are case sensitive)");
		Scanner scan = new Scanner(System.in);
		String lang1 = scan.nextLine();
		System.out.println("What language do you want to translate to?(options are case sensitive)");
		String lang2 = scan.nextLine();
		t.setFromLang(lang1);
		t.setToLang(lang2);
		System.out.println("Please type what you want translated: ");
		String s = scan.nextLine();
		String r = t.translate(s);
		System.out.println(r);
		scan.close();
	}

}
