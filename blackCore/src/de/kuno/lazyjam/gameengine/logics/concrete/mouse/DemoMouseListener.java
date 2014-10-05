package de.kuno.lazyjam.gameengine.logics.concrete.mouse;

import org.newdawn.slick.geom.Vector2f;

import de.kuno.lazyjam.asset.manager.AssetManager;
import de.kuno.lazyjam.gameengine.basic.GameObject;
import de.kuno.lazyjam.gameengine.logics.abstrct.AMouseListenerComponent;
import de.kuno.lazyjam.gameengine.logics.concrete.game.SelfDestructionComponent;
import de.kuno.lazyjam.gamestatemanagement.concrete.GameState;

public class DemoMouseListener extends AMouseListenerComponent {

	private static Vector2f offSet = new Vector2f(10f, -10f);

	public static final String COMPONENT = "demoMouse";

	public void onHover(GameObject go, GameState gs) {
		Vector2f spawn = new Vector2f(go.getPos());
		spawn.add(offSet);
		GameObject click = new GameObject(spawn,gs);
		click.addComponent(AssetManager.getInstance().getAsset("hover"));
		click.addComponent(new SelfDestructionComponent());
	}

	public void onClick(GameObject go, GameState gs) {
		Vector2f spawn = new Vector2f(go.getPos());
		spawn.add(offSet);
		GameObject click = new GameObject(spawn, gs);
		click.addComponent(AssetManager.getInstance().getAsset("click"));
		click.addComponent(new SelfDestructionComponent());
	}

}
