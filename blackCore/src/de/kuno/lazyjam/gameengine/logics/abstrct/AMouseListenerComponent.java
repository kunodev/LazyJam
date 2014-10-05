package de.kuno.lazyjam.gameengine.logics.abstrct;

import de.kuno.lazyjam.constants.Tags;
import de.kuno.lazyjam.gameengine.basic.GameObject;
import de.kuno.lazyjam.gamestatemanagement.concrete.GameState;

public abstract class AMouseListenerComponent{

	
	public void onAdded(GameObject go, GameState gs) {
		gs.addTag(go, Tags.MOUSE);
	}

}
