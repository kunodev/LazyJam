package de.kuno.lazyjam.helper.map;

import de.kuno.lazyjam.gameengine.basic.AGameObjectComponent;
import de.kuno.lazyjam.gameengine.basic.GameObject;
import de.kuno.lazyjam.gameengine.basic.helper.ComponentRegistry;
import de.kuno.lazyjam.tools.log.LogManager;

public class ComponentFactory {

	public static void attachComponent(GameObject newGoResult, String possibleComponentName, String val) {
		Class<? extends AGameObjectComponent> toInstantianteComponent = ComponentRegistry.getInstance()
				.getComponentClass(possibleComponentName);
		try {
			AGameObjectComponent result = toInstantianteComponent.newInstance();
			newGoResult.addComponent(result);
			result.initWithString(val);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			LogManager.getInstance().log("Error while instantiating component : " + possibleComponentName);
		}

	}
}
