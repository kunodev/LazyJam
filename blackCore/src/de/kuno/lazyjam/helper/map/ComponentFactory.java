package de.kuno.lazyjam.helper.map;

import de.kuno.lazyjam.gameengine.basic.GameObject;
import de.kuno.lazyjam.gameengine.basic.helper.ComponentRegistry;
import de.kuno.lazyjam.tools.cdi.injector.InjectorCaller;
import de.kuno.lazyjam.tools.log.LogManager;

public class ComponentFactory {

	public static void attachComponent(GameObject newGoResult, String possibleComponentName, String val) {
		Class<?> toInstantianteComponent = ComponentRegistry.getInstance()
				.getComponentClass(possibleComponentName);
		try {
			Object result = toInstantianteComponent.newInstance();
			newGoResult.addComponent(result);
			InjectorCaller.callMapInit(newGoResult, result, val); 
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			LogManager.getInstance().log("Error while instantiating component : " + possibleComponentName);
		}

	}
}
