package engine;

import java.util.ArrayList;

public class ConfigStorage {
	
	
	public static ArrayList<Config> factoryConfig = new ArrayList();
	
	
	
	public static ArrayList<Config> get() {
		
		
		
		return factoryConfig;
	}
	
	
	
	
	
	
	public static void set(Config config) {
		
		
		factoryConfig.add(config);
		
		
		
	}
	
	
	public static long count() {
		
		return factoryConfig.stream().count();
		
		
	}
	
	

}
