package de.black.core.gameengine.physics;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import de.black.core.gameengine.basic.ALogicComponent;
import de.black.core.tools.physics.BodyBuilder;

public class BodyAdaptorComponent extends ALogicComponent{
	
	public Body body;
	
	public BodyAdaptorComponent(World w) {
		BodyBuilder bb = BodyBuilder.getBodyBuilder(w).withRectanglePolygonShape(20f, 20f);
		body = bb.build();
	}

	public BodyAdaptorComponent(BodyBuilder bb) {
		body = bb.build();
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