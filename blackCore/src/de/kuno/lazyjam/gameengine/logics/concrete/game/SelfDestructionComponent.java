package de.kuno.lazyjam.gameengine.logics.concrete.game;

import de.kuno.lazyjam.gameengine.basic.GameObject;
import de.kuno.lazyjam.gamestatemanagement.concrete.GameState;
import de.kuno.lazyjam.helper.map.Component;


@Component(name="selfDestruct")
public class SelfDestructionComponent {

	private int ticksToDestruction = 30;


	public void onUpdate(GameObject go, GameState gs) {
		if (ticksToDestruction == 0) {
			go.selfDestruct(gs);
		}
		ticksToDestruction--;
	}

	public void initWithString(String s) {
		this.ticksToDestruction = Integer.parseInt(s);
	}

}
