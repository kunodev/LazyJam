package de.black.core.gameengine.logics.concrete.mouse;

import org.newdawn.slick.geom.Vector2f;

import de.black.core.asset.manager.AssetManager;
import de.black.core.gameengine.basic.GameObject;
import de.black.core.gameengine.logics.abstrct.AMouseListenerComponent;
import de.black.core.gameengine.logics.concrete.game.SelfDestructionComponent;

public class DemoMouseListener extends AMouseListenerComponent{

	private static Vector2f offSet = new Vector2f(10f,-10f);

	public static final String COMPONENT = "demoMouse";
	
	@Override
	public void onHover() {
		Vector2f spawn = new Vector2f(getGameObject().getPos());
		spawn.add(offSet);
		GameObject click = new GameObject(spawn);
		click.addRenderComp(AssetManager.getInstance().getAsset("hover"));
		click.addLogicComp(new SelfDestructionComponent());
	}

	@Override
	public void onClick() {
		Vector2f spawn = new Vector2f(getGameObject().getPos());
		spawn.add(offSet);
		GameObject click = new GameObject(spawn);
		click.addRenderComp(AssetManager.getInstance().getAsset("click"));
		click.addLogicComp(new SelfDestructionComponent());
	}
	
}
