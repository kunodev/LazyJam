package de.kuno.lazyjam.tools.cdi.injector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import de.kuno.lazyjam.gameengine.basic.GameObject;
import de.kuno.lazyjam.gamestatemanagement.concrete.GameState;
import de.kuno.lazyjam.helper.map.MapInit;
import de.kuno.lazyjam.tools.cdi.annotations.Render;
import de.kuno.lazyjam.tools.cdi.annotations.Update;
import de.kuno.lazyjam.tools.cdi.manager.ServiceManager;

public class InjectorCaller {
	//TODO: first im going to assume every field in a component is public, 
	// probablygoing to solve this by an annotation that declares this field injectable or publishable

	public static void callUpdate(GameObject gameObject, GameState gs, ServiceManager serviceMan) {
		callMethodOnGameObjects(gameObject, gs, serviceMan, Update.class);
	}


	public static void callRender(GameObject gameObject, GameState gs, ServiceManager serviceMan) {
		callMethodOnGameObjects(gameObject, gs, serviceMan, Render.class);
	}
	
	private static Method getMethod(Object comp, Class<? extends Annotation> annotation) {
		for(Method m : comp.getClass().getMethods()) {
			if(m.getAnnotation(annotation) != null) {
				return m;
			}
		}
		return null;
	}
	
	public static void callMapInit(GameObject newGoResult, Object component, String val) {
		newGoResult.addComponent(component);
		Method initFromMap = getMethod(component, MapInit.class);
		if(initFromMap != null) {
			try {
				initFromMap.setAccessible(true);
				initFromMap.invoke(component, val);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void callMethodOnGameObjects(GameObject gameObject, GameState gs, ServiceManager serviceMan, Class<? extends Annotation> anoType) {
		for(Object comp : gameObject.getComponents()) {
			Method m = getMethod(comp, anoType);
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
		result = tryOtherComps(toFind, gameObject);
		if(result != null) {
			return result;
		}
		
		result = tryObject(gameObject, toFind);
		if(result != null) {
			return result;
		}
		
		//try silbingComponents field
		result = tryOtherCompsFields(toFind, gameObject);
		if(result != null) {
			return result;
		}
		// ok. fuck it, try gamestate
		result = tryObject(gs, toFind);
		if(result != null) {
			return result;
		}
		
		result = tryFindField(toFind, gs);
		if(result != null) {
			return result;
		}
		// must be a service otherwise
		result = serviceMan.getService(toFind);
		return result;
	}

	private static Object tryOtherComps(Class<?> toFind, GameObject gameObject) {
		for(Object obj : gameObject.getComponents()) {
			Object result = tryObject(obj, toFind);
			if(result != null) {
				return result;
			}
		}
		return null;
	}
	
	private static Object tryObject(Object obj, Class<?> toFind) {
		Class<?> someClass = obj.getClass();
		while(someClass != null) {
			if(someClass == toFind) {
				return obj;
			} else {
				someClass = someClass.getSuperclass();
			}
		}
		return null;
	}

	private static Object tryOtherCompsFields(Class<?> toFind, GameObject gameObject) throws IllegalArgumentException, IllegalAccessException {
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
					//TODO: superclasses?
					field.setAccessible(true);
					return field.get(gameObject);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}

}
