package de.black.core.gameengine.physics;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import de.black.core.gameengine.basic.ALogicComponent;
import de.black.core.tools.physics.BodyBuilder;
import de.black.core.tools.physics.TestContactListener;

public class BodyAdaptorComponent extends ALogicComponent{
	
	public Body body;
	public TestContactListener testListener; 
	
	public BodyAdaptorComponent(World w) {
		BodyBuilder bb = BodyBuilder.getBodyBuilder(w).withRectanglePolygonShape(20f, 20f);
		body = bb.build();
	}

	public BodyAdaptorComponent(BodyBuilder bb) {
		body = bb.build();
		testListener = new TestContactListener(body);
		body.getWorld().setContactListener(testListener);
	}

	@Override
	public void onUpdate() {
		VectorHelper.setSlickVector(getGameObject().getPos(), body.getPosition());
	}
	@Override
	public void onAdded() {
		VectorHelper.setJBoxVector(body.getPosition(), getGameObject().getPos());
	}

}