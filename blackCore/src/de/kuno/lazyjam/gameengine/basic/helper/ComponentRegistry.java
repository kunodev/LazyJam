package de.kuno.lazyjam.gameengine.basic.helper;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.reflections.Reflections;

import de.kuno.lazyjam.helper.map.Component;
import de.kuno.lazyjam.tools.cdi.annotations.Service;
import de.kuno.lazyjam.tools.log.LogManager;
import de.kuno.lazyjam.tools.reflect.ReflectionUtil;

@Service
public class ComponentRegistry {
	
	private Map<String, Class<?>> componentRegistry;
	
	public ComponentRegistry() {
		this.componentRegistry = new TreeMap<String, Class<?>>();
		this.loadComponents();
	}

	public void loadComponents() {
		Set<Class<?>> classes = ReflectionUtil.reflect.getTypesAnnotatedWith(Component.class, true);
		for (Class<?> c : classes) {
			try {
				Component anno = c.getAnnotation(Component.class);
				System.out.println(c.toGenericString());
				componentRegistry.put(anno.name(), c);
			} catch (IllegalArgumentException | SecurityException e) {
				System.err.println(c.getName());
				System.err.println("a gameobject component does not have a COMPONENT annotation!");
				e.printStackTrace();
			}
		}
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
