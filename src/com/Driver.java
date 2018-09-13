package com;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import engine.Config;
import engine.ConfigStorage;
import engine.DiscoveryEngine;
import engine.FactoryEngine;
import models.ParsersInt;
import parsers.JSONParser;
import parsers.XMLParser;

public class Driver {
	
	public static String[] listFiles = new String [] {"test.xml", "test.json"};
	static List<String> listData = Arrays.asList(listFiles);
	

	/**Auto Discoverable Factories Driver
	 * 
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		//auto discovery and registering factories
		
		ArrayList<Config> parsers=DiscoveryEngine.registerAllParsers();
		
		Long count = ConfigStorage.count();
		System.out.println("the count is: "+count);
		
		for (Config config : parsers) {
			
			
			System.out.println(config.getFileType());
			System.out.println(config.getName());
			
		}
		
		
		
		for (String fileStr : listData) {
			
			System.out.println("the file value is: "+fileStr);
			
			//add the factory pattern here
			
			ParsersInt parser=FactoryEngine.factory(fileStr);
			
			if(parser instanceof XMLParser) {
				
				
				System.out.println("just made an XML parser");
			}
			
			
			else if(parser instanceof JSONParser) {
				
				
				System.out.println("just made an JSON parser");
			}
			
			
			else {
				
				System.out.println("created a parser but cant tell what type it is");
			}
			
			
			
			
			String result =parser.parse(fileStr);
			
			
			System.out.println(result);
			
		}
		
		
		

		
	}
	
	

}
