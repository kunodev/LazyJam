package de.black.core.gamestatemanagement.concrete;

import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import de.black.core.tools.physics.TestContactListener;

public class GameGameStateWithPhysics extends GameGameState {

	protected World physicsWorld;
	protected Vec2 gravity = new Vec2(0f, 0.03f);
	protected ContactListener cl;

	public GameGameStateWithPhysics(GameContainer gc) {
		super(gc);
	}

	protected void initWorld() {
		physicsWorld = new World(gravity);
		cl = new TestContactListener();
	}

	@Override
	protected void update(GameContainer gc) {
		super.update(gc);
		physicsWorld.step(1f, 6, 2);
	}

	public void init() {
		super.init();
		initWorld();
	}

	public World getWorld() {
		return physicsWorld;
	}

}
