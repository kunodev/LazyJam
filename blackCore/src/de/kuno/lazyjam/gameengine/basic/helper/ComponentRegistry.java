package de.kuno.lazyjam.gameengine.basic.helper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.kuno.lazyjam.helper.map.Component;
import de.kuno.lazyjam.tools.log.LogManager;
import de.kuno.lazyjam.tools.reflect.ReflectionUtil;

public class ComponentRegistry {

	private static ComponentRegistry instance;

	public static ComponentRegistry getInstance() {
		if (instance == null) {
			instance = new ComponentRegistry();
		}
		return instance;
	}

	private Map<String, Class<?>> componentRegistry;

	public ComponentRegistry() {
		this.componentRegistry = new TreeMap<String, Class<?>>();
		this.loadDefaults();
	}

	public void loadComponents(String packageName) {
		Class<?>[] classes = {};
		try {
			classes = ReflectionUtil.getClasses(packageName);
		} catch (IOException | ClassNotFoundException e1) {
			LogManager.getInstance().log("Could not load  classes!");
		}
		for (Class<?> c : classes) {
			try {
				Component anno = c.getAnnotation(Component.class);
				componentRegistry.put(anno.name(), c);
			} catch (IllegalArgumentException | SecurityException e) {
				System.err.println(c.getName());
				LogManager.getInstance().log("a gameobject component does not have a COMPONENT field!");
				e.printStackTrace();
			}
		}
	}

	public void loadDefaults() {
		this.loadComponents("de.black.core.gameengine.renderer.concrete");
		this.loadComponents("de.black.core.gameengine.logics.concrete");
	}


	public Set<String> getPossibleComponentNames() {
		// List<String> names = new ArrayList<String>();
		// return names;
		return componentRegistry.keySet();
	}

	public Class<?> getComponentClass(String possibleComponentName) {
		return this.componentRegistry.get(possibleComponentName);
	}

}
