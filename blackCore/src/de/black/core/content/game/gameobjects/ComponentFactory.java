package de.black.core.content.game.gameobjects;

import de.black.core.gameengine.basic.AGameObjectComponent;
import de.black.core.gameengine.basic.GameObject;
import de.black.core.gameengine.basic.helper.ComponentRegistry;
import de.black.core.tools.log.LogManager;

public class ComponentFactory {

	public static void attachComponent(GameObject newGoResult,
			String possibleComponentName, String val) {
		Class<? extends AGameObjectComponent> toInstantianteComponent = 
				ComponentRegistry.getInstance().getComponentClass(possibleComponentName);
		try {
			AGameObjectComponent result = toInstantianteComponent.newInstance();
			result.initWithString(val);
			newGoResult.addComponent(result);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			LogManager.getInstance().log("Error while instantiating component : " + possibleComponentName);
		}
		
	}
}
