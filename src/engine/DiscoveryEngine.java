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
import java.util.List;

public class DiscoveryEngine {

	public static ArrayList<Config> registerAllParsers()
			throws ClassNotFoundException, IOException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// get a list of all teh files in teh parsers package
		Class[] list = getClasses("parsers");
		List<Class> fileList = Arrays.asList(list);

		// System.out.println(Arrays.asList(list));

		// iterate through the list of parsers
		for (Class<?> file : fileList) {

			String fileName = file.getName();
			System.out.println(fileName);

			// create a class object from the filenames

			/*
			 * Class<?> dirClass = Class.forName(fileName);
			 * 
			 * //get the associated register method
			 * 
			 * Method registerStaticMethod = dirClass.getDeclaredMethod( "register");
			 * 
			 * 
			 * //invoke it registerStaticMethod.invoke(null);
			 */

			 Method registerMethod = MethodHandles.lookup().lookupClass().getClassLoader().loadClass(fileName)
					.getDeclaredMethod("register");

			registerMethod.invoke(null);

		}

		// query the pojo to see if its populate and how much entries

		ArrayList<Config> data = ConfigStorage.get();

		return data;

	}

	/**
	 * Scans all classes accessible from the context class loader which belong to
	 * the given package and subpackages.
	 *
	 * @param packageName
	 *            The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {

		ClassLoader classLoader = MethodHandles.lookup().lookupClass().getClassLoader();
		assert classLoader != null;

		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);

		List<File> dirs = new ArrayList<File>();

		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}

		ArrayList<Class> classes = new ArrayList<Class>();

		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}

		return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory
	 *            The base directory
	 * @param packageName
	 *            The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {

		List<Class> classes = new ArrayList<Class>();

		if (!directory.exists()) {
			return classes;
		}

		File[] files = directory.listFiles();

		for (File file : files) {

			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(
						Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

}
