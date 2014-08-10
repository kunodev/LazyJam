package de.black.core.gamestatemanagement.concrete;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;

import de.black.core.content.game.CoreGameContentProvider;

public class GameGameStateWithPhysics extends GameGameState{
	
	protected World physicsWorld;
	protected Vec2 gravity = new Vec2(0f,1f);

	public GameGameStateWithPhysics(GameContainer gc) {
		super(gc);
	}
	
	protected void initWorld() {
		physicsWorld = new World(gravity);
	}

	@Override
	protected void update(GameContainer gc) {
		super.update(gc);
		physicsWorld.step(1f, 1, 1);
	}
	
	public void init() {
		initWorld();
		CoreGameContentProvider.initGameObjects(physicsWorld);
	}

}
