package parsers;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.ArrayList;

import engine.Config;
import engine.ConfigStorage;
import models.ParsersInt;

public class JSONParser implements ParsersInt {

	/**
	 * constructor
	 */
	public JSONParser() {
		
		
		
		
	}
	
	/** getInstance
	 * 
	 * @return
	 */
	
	public static JSONParser getInstance() {
		
		
		System.out.println("New instance of JSONParser created via MethodHandles");
		
		return new JSONParser();
		
	}
	
	
	/**register
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 */
	public static void register() throws NoSuchMethodException, SecurityException, ClassNotFoundException {
		
		
		//java.lang.Class.getSimpleName()
		String name=MethodHandles.lookup().lookupClass().getSimpleName()+".java";
		

		/*
		Method meth = MethodHandles.lookup().lookupClass().getClassLoader().loadClass("test,java").getMethod("e", String.class);
		meth.invoke(obj, args) */
		
		
		Config config = new Config(name, "json");  //here we declare the filename and filetype
		
		System.out.println("called the json register method!");
		
		ConfigStorage.set(config);
		
		
		
	}
	
	
	/**
	 * validate
	 * 
	 * @param file
	 * @return
	 */
	public static Boolean validate(String file) {
		
		
		if (file.contains("json")) {
			
			
			return true;
	}
	return false;
		
		
		
	}



	@Override
	/**
	 * parse
	 */
	public String parse(String data) {
	
		
		String dataStr = "this has parsed some JSON data";
		
		
		return dataStr;
	}
	
	
	

	
	
	public static String getClassName(final int depth)
	{
	  final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

	  //System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
	  // return ste[ste.length - depth].getMethodName();  //Wrong, fails for depth = 0
	  return ste[ste.length - 1 - depth].getClassName(); 
	}

}
