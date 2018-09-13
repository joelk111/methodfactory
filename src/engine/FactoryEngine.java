package engine;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import models.ParsersInt;

public class FactoryEngine {
	

	
	
	/**
	 * factory
	 * 
	 * This method uses auto discovered factories to populate the specific type of
	 * parser.
	 * 
	 * @param fileStr
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static ParsersInt factory(String fileStr)
			throws ClassNotFoundException, IOException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		String factoryParser;

		System.out.println("inside the factory class");

		ArrayList<Config> data = ConfigStorage.get();

		Long count = ConfigStorage.count();
		// System.out.println("the count is: "+count);

		for (Config config : data) {

			String fileName = config.getName().replaceAll(".java", "");
			// System.out.println(fileName);

			// create a class object from the filenames

			/*
			 * Class<?> dirClass = Class.forName("parsers." + fileName);
			 * 
			 * // get the associated register method
			 * 
			 * Method registerStaticMethod = dirClass.getDeclaredMethod("validate",
			 * String.class);
			 * 
			 * // invoke it Boolean result = (Boolean) registerStaticMethod.invoke(null,
			 * fileStr);
			 */

			Method validateMethod = MethodHandles.lookup().lookupClass().getClassLoader()
					.loadClass("parsers." + fileName).getMethod("validate", String.class);

			Boolean result = (Boolean) validateMethod.invoke(null, fileStr);

			if (result.equals(true)) {

				System.out.println("found a validation rule that matched");

				// make this parser the type that will parse the file

				/*
				 * factoryParser = config.getName().replaceAll(".java", "");
				 * 
				 * Class factoryParserClass = Class.forName("parsers." + factoryParser);
				 * 
				 * Method getInstance = factoryParserClass.getDeclaredMethod("getInstance");
				 */

				// use methodhandles instaed of reflection
				factoryParser = config.getName().replaceAll(".java", "");
				
				Method getInstanceMethod = MethodHandles.lookup().lookupClass().getClassLoader()
						.loadClass("parsers." + factoryParser).getDeclaredMethod("getInstance");

				System.out.println("creating new object: " + getInstanceMethod.getName());

				// invoke it

				// To invoke an instance method, the first argument to invoke() must be an
				// instance of Method that reflects the method being invoked:

				ParsersInt parsedData = (ParsersInt) getInstanceMethod.invoke(null);

				return parsedData;

			}

			else {

				System.out.println("not a match");
			}

		}
		return null;

	}
	
	
	

    
	
	
	 public static <T> Stream<T> enumerationAsStream(Enumeration<T> e) {
	     return StreamSupport.stream(
	         Spliterators.spliteratorUnknownSize(
	             new Iterator<T>() {
	                 public T next() {
	                     return e.nextElement();
	                 }
	                 public boolean hasNext() {
	                     return e.hasMoreElements();
	                 }
	             },
	             Spliterator.ORDERED), false);
	 }
}


