package parsers;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

import org.xml.sax.Parser;

import engine.Config;
import engine.ConfigStorage;
import models.ParsersInt;

public class XMLParser implements ParsersInt {
	
	
	public XMLParser() {
		
	
		
		
	}
	
	
	
	public static XMLParser getInstance() {
		
		
		System.out.println("creating a new instance of XML Parser via MethodHandles");
		
		return new XMLParser();
		
	}
	
	
	public static void register() {
		
		String name=MethodHandles.lookup().lookupClass().getSimpleName()+".java";
	//	System.out.println("The Java name that has been auto discovered is: "+name);
		
		Config config = new Config(name, "xml");   //here we declare the filename and filetype
		
		System.out.println("called the xml register method!");
		
		ConfigStorage.set(config);
		
		
		
	}
	
	
	
	public static Boolean validate(String file) {
		
		
	if (file.contains("xml")) {
		
		
		
		return true;
	}
	return false;
		
		
		
	}



	@Override
	public String parse(String data) {
	
		String dataStr = "this has parsed some xml data";
		
		
		return dataStr;
		
		
		
	}
	
	
	

}
