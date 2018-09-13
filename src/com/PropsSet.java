package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PropsSet {

	public static void main(String[] args) {
		
		/**
		 * 1. pass in as argument a list of all the properties names from Jenkins
		 * 2. pass in the location of where the files are at in git
		 * 3. needs to recursively look for the files if they are not in the same folder.
		 * 4. create dynamic Maps to lists
		 * 5. check values
		 * 5. Compare
		 */
		
		HashMap<String,String> map = new HashMap();  //data will come in parsed in a HashMap<String, String>
		
		String [] arr1 = new String[] {"port", "server", "host", "password"};  //PROD
		String [] arr2 = new String[] {"port", "server", "host", "password"};  //UAT
		String [] arr3 = new String[] {"port", "email", "host", "password","data"}; //QA
		String [] arr4 = new String[] {"port", "server", "host", "password","apikey"};  //DEV
		String [] arr5 = new String[] {"port", "server", "host", "password","apikey2"};
		
		
		List<String> result_map = map.keySet().stream()
				.collect(Collectors.toList());
		

		
		
		List<String> prod = Arrays.asList(arr1);
		List<String> uat = Arrays.asList(arr2);
		List<String> qa = Arrays.asList(arr3);
		List<String> dev = Arrays.asList(arr4);
		//List<String> message_constants = Arrays.asList(arr5);
		
		
		
		
		Boolean dev_result=dev.containsAll(prod);
		Boolean uat_result=uat.containsAll(prod);
		Boolean qa_result=qa.containsAll(prod);
		//Boolean message_constants_result=message_constants.containsAll(prod);
		//Boolean uat_result=uat.contains(prod);
		
		


		

		
		
		
		if(dev_result.equals(true)&&qa_result.equals(true)&&uat_result.equals(true)) {
			
			System.out.println("{ integrity: pass");
			
			
		}
		
		
		
		else {
			
			System.out.println("{ validation: failure }");
			
			//left symetric arr5 vs rest of data structures
			
			System.out.println("dev result is: "+ dev_result);  //arr4
			System.out.println("qa result is: "+qa_result); //arr3 - this should be false as its missing server
			System.out.println("uat result is: "+uat_result); //arr2
			//System.out.println("message constants result is: "+message_constants_result);  //arr5
			
			
			
			
		}
		
		
		// TODO Auto-generated method stub

	}

}
