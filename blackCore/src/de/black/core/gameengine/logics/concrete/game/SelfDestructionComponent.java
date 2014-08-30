package de.black.core.gameengine.logics.concrete.game;

import de.black.core.gameengine.basic.ALogicComponent;

public class SelfDestructionComponent extends ALogicComponent{
	
	private int ticksToDestruction = 30;
	
	public static final String COMPONENT = "selfDestruct";

	@Override
	public void onUpdate() {
		if(ticksToDestruction == 0) {
			getGameObject().selfDestruct();
		}
		ticksToDestruction--;
	}

	@Override
	public void initWithString(String s) {
		this.ticksToDestruction = Integer.parseInt(s);
	}
	
}
