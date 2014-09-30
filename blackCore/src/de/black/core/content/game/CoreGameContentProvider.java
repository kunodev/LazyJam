package de.black.core.content.game;

import org.jbox2d.dynamics.World;

import de.black.core.gamestatemanagement.GameStateManager;

public class CoreGameContentProvider {

	public static void initGameObjects(World physicsWorld) {
		AMapContentProvider amap = new AMapContentProvider();
		GameStateManager.getInstance().getGameGameState().setMap(amap.map);
		amap.init();
	}
}
