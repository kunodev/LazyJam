package de.kuno.lazyjam.helper.map;

import de.kuno.lazyjam.asset.manager.AssetManager;
import de.kuno.lazyjam.gameengine.basic.GameObject;
import de.kuno.lazyjam.gameengine.basic.helper.ComponentRegistry;
import de.kuno.lazyjam.tools.cdi.annotations.InjectedService;
import de.kuno.lazyjam.tools.cdi.annotations.Service;
import de.kuno.lazyjam.tools.cdi.injector.InjectorCaller;
import de.kuno.lazyjam.tools.log.LogManager;

public class ComponentFactory {
	
	public static  void attachComponent(GameObject newGoResult, String possibleComponentName, String val, ComponentRegistry registry, AssetManager assetMan) {
		Class<?> toInstantianteComponent = registry
				.getComponentClass(possibleComponentName);
		try {
			Object result = toInstantianteComponent.newInstance();
			newGoResult.addComponent(result);
			InjectorCaller.callMapInit(assetMan, newGoResult, result, val, assetMan); 
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			System.err.println("Error while instantiating component : " + possibleComponentName);
		}

	}
}
