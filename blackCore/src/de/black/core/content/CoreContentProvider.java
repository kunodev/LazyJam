package de.black.core.content;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

import de.black.core.gameengine.basic.GameObject;
import de.black.core.gameengine.renderer.ASCIISpriteAnimation;
import de.black.core.gamestatemanagement.GameStateManager;
import de.black.core.gamestatemanagement.concrete.GameGameState;
import de.black.core.gamestatemanagement.concrete.GameGameStateWithPhysics;
import de.black.core.gamestatemanagement.concrete.MenuGameState;
import de.black.core.gamestatemanagement.concrete.VNGameState;

public class CoreContentProvider {
	
	public static void initGameStates(GameContainer gc) {
		GameStateManager man = GameStateManager.getInstance();
		man.addGameState(MenuGameState.ID, new MenuGameState(gc));
		man.setGameState(MenuGameState.ID);
		VNGameState vnman = new VNGameState(gc);
		vnman.init();
		man.addGameState(VNGameState.ID, vnman);
		GameGameStateWithPhysics game = new GameGameStateWithPhysics(gc);
		man.addGameState(GameGameState.ID, game);
		game.init();
	}
	
	public static void initGameObjects(GameContainer gc) {

	}
	
	
}
