//this is a wrapper that allows Languages to be specified as strings instead of as Languages only
//Creates a translator object that is linked to Microsoft translation service 
//Created by Colin Cruse 4/15


// test


import java.util.HashMap;
import java.util.Map;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;


public class CCtranslator {






	  Map<String, Language> langs;
	  Language from = null;
	  Language to = null;
	
	
	  CCtranslator(String id, String sec){
		  Translate.setClientId(id);
		  Translate.setClientSecret(sec);
		  langs = new HashMap<String, Language>();
		  
		  //setting up dictionary of supported languages
		  langs.put("English",Language.ENGLISH );
		  langs.put("French",Language.FRENCH);
		  langs.put("Arabic",Language.ARABIC );
		  langs.put("Italian",Language.ITALIAN );
		 
		  
		  //langs.put("English",Language.ENGLISH );
		  //langs.put("English",Language.ENGLISH );  
	  }
	  //set language translating from
	  public void setFromLang(String lang){
		  this.from = langs.get(lang);
		  
	  }
	  //set language translating to
	  public void setToLang(String lang){
		  this.to = langs.get(lang);
		  
	  }
	  
	  public String translate(String f){
		  String translatedText = "There has been an error in translation";// default value
		  try {
			translatedText = Translate.execute(f,this.from,this.to);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return translatedText;
		  
	  }
	
}
