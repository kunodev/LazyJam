package de.black.core.gameengine.basic.helper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.black.core.gameengine.basic.AGameObjectComponent;
import de.black.core.gameengine.basic.ARenderComponent;
import de.black.core.tools.log.LogManager;

public class ComponentRegistry {
	
	private static ComponentRegistry instance;
	
	public static ComponentRegistry getInstance() {
		if(instance == null) {
			instance = new ComponentRegistry();
		}
		return instance;
	}
	
	private Map<String, Class<? extends AGameObjectComponent>> componentRegistry;
	public ComponentRegistry() {
		this.componentRegistry = new TreeMap<String, Class<? extends AGameObjectComponent>>();
		this.loadDefaults();
	}
	
	public void loadComponents(String packageName) {
		Class[] classes = {};
		try {
			classes = getClasses(packageName);
		} catch (IOException | ClassNotFoundException e1) {
			LogManager.getInstance().log("Could not load  classes!");
		}
		for(Class c : classes) {
			if(isGOComponent(c)) {
				String componentName;
				try {
					Class<? extends AGameObjectComponent> target = (Class<? extends AGameObjectComponent>) c;
					componentName = c.getDeclaredField("COMPONENT").get(null).toString();
					componentRegistry.put(componentName, target);
				} catch (IllegalArgumentException | IllegalAccessException
						| NoSuchFieldException | SecurityException e) {
					System.err.println(c.getName());
					LogManager.getInstance().log("a gameobject component does not have a COMPONENT field!");
					e.printStackTrace();
				}
			}
		}
	}
	
	private boolean isGOComponent(Class c) {
		return AGameObjectComponent.class.isAssignableFrom(c);
	}
	
	public void loadDefaults() {
		this.loadComponents("de.black.core.gameengine.renderer.concrete");
		this.loadComponents("de.black.core.gameengine.logics.concrete");
	}

	/**
	* Scans all classes accessible from the context class loader which belong to the given package and subpackages.
	*
	* @param packageName The base package
	* @return The classes
	* @throws ClassNotFoundException
	* @throws IOException
	*/
	private static Class[] getClasses(String packageName)
					throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
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
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
				}
		}
		return classes;
	}
	
	public Set<String> getPossibleComponentNames() {
//		List<String> names = new ArrayList<String>();
//		return names;
		return componentRegistry.keySet();
	}

	public Class<? extends AGameObjectComponent> getComponentClass(
			String possibleComponentName) {
		return this.componentRegistry.get(possibleComponentName);
	}
	
	
	
}
