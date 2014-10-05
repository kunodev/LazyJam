package de.kuno.lazyjam.tools.cdi.injector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import de.kuno.lazyjam.gameengine.basic.GameObject;
import de.kuno.lazyjam.gamestatemanagement.concrete.GameState;
import de.kuno.lazyjam.tools.cdi.annotations.Update;
import de.kuno.lazyjam.tools.cdi.manager.ServiceManager;

public class InjectorCaller {
	//TODO: first im going to assume every field in a component is public, 
	// probablygoing to solve this by an annotation that declares this field injectable or publishable

	public static void callUpdate(GameObject gameObject, GameState gs, ServiceManager serviceMan) {
		for(Object comp : gameObject.getComponents()) {
			Method m = getMethod(comp, Update.class);
			if(m != null) {
				try {
					Object[] params = getParams(m.getParameterTypes(), gameObject, gs, serviceMan);
					m.invoke(comp, params);
				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	private static Method getMethod(Object comp, Class<? extends Annotation> annotation) {
		for(Method m : comp.getClass().getMethods()) {
			if(m.getAnnotation(annotation) != null) {
				return m;
			}
		}
		return null;
	}

	public static void callRender(GameObject gameObject, GameState gs, ServiceManager serviceMan) {
		
	}

	public static void callMapInit(GameObject newGoResult, Object component, String val) {
		
	}
	
	private static Object[] getParams(Class<?>[] paramClasses, GameObject gameObject, GameState gs, ServiceManager serviceMan) throws IllegalArgumentException, IllegalAccessException {
		Object[] result = new Object[paramClasses.length];
		for(int i=0; i<paramClasses.length; i++) {
			result[i] = getParam(paramClasses[i], gameObject, gs, serviceMan);
		}
		return result;
	}

	private static Object getParam(Class<?> toFind, GameObject gameObject, GameState gs, ServiceManager serviceMan) throws IllegalArgumentException, IllegalAccessException {
		Object result;
		// try gameObject (pos, maybe cool stuff in extensions)
		result = tryFindField(toFind, gameObject);
		if(result != null) {
			return result;
		}
		//try silbingComponent
		result = tryOtherComps(toFind, gameObject);
		if(result != null) {
			return result;
		}
		// ok. fuck it, try gamestate
		result = tryFindField(toFind, gs);
		if(result != null) {
			return result;
		}
		// must be a service otherwise
		result = serviceMan.getService(toFind);
		return result;
	}

	private static Object tryOtherComps(Class<?> toFind, GameObject gameObject) throws IllegalArgumentException, IllegalAccessException {
		for(Object obj : gameObject.getComponents()) {
			Object res = tryFindField(toFind, obj);
			if(res != null) {
				return res;
			}
		}
		return null;
	}

	private static Object tryFindField(Class<?> toFind, Object gameObject) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = gameObject.getClass();
		while(clazz != null) {
			Field[] fields = clazz.getDeclaredFields();
			for(Field field : fields) {
				if(field.getType() == toFind) {
					field.setAccessible(true);
					return field.get(gameObject);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}

}
