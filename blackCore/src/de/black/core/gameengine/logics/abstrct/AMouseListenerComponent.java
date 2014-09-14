package de.black.core.gameengine.logics.abstrct;

import de.black.core.constants.Tags;
import de.black.core.gameengine.basic.ALogicComponent;
import de.black.core.gamestatemanagement.GameStateManager;
import de.black.core.gamestatemanagement.concrete.GameGameState;

public abstract class AMouseListenerComponent extends ALogicComponent {

	@Override
	public void onUpdate() {
		//Dont do shit
	}
	
	public abstract void onHover();
	
	public abstract void onClick();
	
	@Override
	public void onAdded() {
		GameGameState ggs = GameStateManager.getInstance().getGameStateAs(GameGameState.class, GameGameState.ID);
		ggs.addTag(this.getGameObject(), Tags.MOUSE);
	}

}