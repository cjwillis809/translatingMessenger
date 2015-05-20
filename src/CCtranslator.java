//this is a wrapper that allows Languages to be specified as strings instead of as Languages only
//Creates a translator object that is linked to Microsoft translation service 
//Created by Colin Cruse & Emilia Paulski

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class CCtranslator {
		
	  
	  Language from = null;
	  Language to = null;
	
	  CCtranslator(String id, String sec){
		  Translate.setClientId(id);
		  Translate.setClientSecret(sec);
	  }
	  
	  //set language translating from
	  public void setFromLang(Language lang){
		this.from = lang;
	  }
	  
	  //set language translating to
	  public void setToLang(Language lang){
		  this.to = lang;
	  }
	  
	  public String translate(String f){
		  String translatedText = "There has been an error in translation";// default value
		  try {
			translatedText = Translate.execute(f,this.from,this.to);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return translatedText;
	  }
	
}
