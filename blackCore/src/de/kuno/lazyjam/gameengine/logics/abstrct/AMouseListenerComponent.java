package de.kuno.lazyjam.gameengine.logics.abstrct;

import de.kuno.lazyjam.constants.Tags;
import de.kuno.lazyjam.gameengine.basic.ALogicComponent;
import de.kuno.lazyjam.gamestatemanagement.GameStateContextManager;
import de.kuno.lazyjam.gamestatemanagement.concrete.GameGameState;

public abstract class AMouseListenerComponent extends ALogicComponent {

	@Override
	public void onUpdate() {
		// Dont do shit
	}

	public abstract void onHover();

	public abstract void onClick();

	@Override
	public void onAdded() {
		GameGameState ggs = GameStateContextManager.getInstance().getGameStateAs(GameGameState.class);
		ggs.addTag(this.getGameObject(), Tags.MOUSE);
	}

}
