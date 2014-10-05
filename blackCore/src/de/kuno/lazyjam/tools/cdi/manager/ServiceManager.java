package de.kuno.lazyjam.tools.cdi.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.kuno.lazyjam.tools.cdi.annotations.Service;
import de.kuno.lazyjam.tools.reflect.ReflectionUtil;

public class ServiceManager {
	
	private Map<Class<?>, Object> servicesObjects;
	
	public ServiceManager() {
		servicesObjects = new HashMap<Class<?>,Object>();
	}
	
	public void registerAsService(Object service) {
		servicesObjects.put(service.getClass(), service);
	}
	
	public <T> T getService(Class<T> clazz) {
		return clazz.cast(servicesObjects.get(clazz));
	}
	
	public void searchForServices() {
		try {
			Class<?>[] allClasses = ReflectionUtil.getClasses("");
			for(Class<?> clazz : allClasses) {
				Class<?> superClass = clazz;
				while(superClass != null) {
					if(clazz.getAnnotation(Service.class) != null) {
						Object service = clazz.newInstance();
						servicesObjects.put(clazz, service);
					} else {
						superClass = superClass.getSuperclass();
					}
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		
	}
	

} 
