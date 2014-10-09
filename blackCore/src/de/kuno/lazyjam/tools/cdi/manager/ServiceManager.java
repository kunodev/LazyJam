package de.kuno.lazyjam.tools.cdi.manager;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.reflections.Reflections;

import de.kuno.lazyjam.tools.cdi.annotations.InjectedService;
import de.kuno.lazyjam.tools.cdi.annotations.Service;
import de.kuno.lazyjam.tools.reflect.ReflectionUtil;

public class ServiceManager {

	private Map<Class<?>, Object> servicesObjects;
	private Map<List<Field>,Class<?>> injections;

	public ServiceManager() {
		servicesObjects = new HashMap<Class<?>, Object>();
		injections = new HashMap<List<Field>,Class<?>>();
	}

	public void registerAsService(Object service) {
		Class<?> clazz = service.getClass();
		addServiceAndInject(clazz, service);
	}

	public <T> T getService(Class<T> clazz) {
		return clazz.cast(servicesObjects.get(clazz));
	}

	public void searchForServices() {
		Set<Class<?>> classes = ReflectionUtil.reflect.getTypesAnnotatedWith(Service.class);
		for (Class<?> clazz : classes) {
			addServiceAndInject(clazz, null);
		}
	}
	
	private void addServiceAndInject(Class<?> clazz, Object o) {
		try {
			Object service = o;
			if(o == null) {
				service = clazz.newInstance();
			}
			servicesObjects.put(clazz, service);
			injectIntoNew(service);
			injectIntoOlds(service);
			addNewLists(clazz);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void addNewLists(Class<?> clazz) {
		List<Field> injectorFields = new ArrayList<Field>();
		for(Field f : clazz.getFields()) {
			if(f.isAnnotationPresent(InjectedService.class)) {
				injectorFields.add(f);
			}
		}
		this.injections.put(injectorFields, clazz);
		
	}

	private void injectIntoOlds(Object service) throws IllegalArgumentException, IllegalAccessException {
		for(Entry<List<Field>,Class<?>> entries : injections.entrySet()) {
			for(Field f : entries.getKey()) {
				if( f.getType() == service.getClass()) {
					f.setAccessible(true);
					f.set(servicesObjects.get(entries.getValue()), service);
				}
			}
		}
		
	}

	private void injectIntoNew(Object service) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = service.getClass().getFields();
		for(Field field : fields) {
			if(field.isAnnotationPresent(InjectedService.class)) {
				Class<?> typeOfField = field.getType();
				if(servicesObjects.containsKey(typeOfField)) {
					field.set(service, servicesObjects.get(typeOfField));
				}
				//TODO: i could also look if i have an extension of the injected service... not sure
			}
		}
		
	}

}
