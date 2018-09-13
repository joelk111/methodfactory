package com;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodTest {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		
		String data = "json";
		
		//java.lang.Class.getSimpleName()
		String name=MethodHandles.lookup().lookupClass().getSimpleName()+".java";
		

		
/*		Method validateMethod = MethodHandles.lookup().lookupClass().getClassLoader().loadClass("parsers." + fileName).getMethod("validate", String.class);
	
		
		Boolean result = (Boolean) validateMethod.invoke(null, fileStr);
		
		System.out.println(result);*/
		

	}

}
