package de.black.core.gameengine.physics;

import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

import de.black.core.gameengine.basic.ALogicComponent;

public class BodyAdaptorComponent extends ALogicComponent{
	
	public Body body;
	
	public BodyAdaptorComponent(World w) {
		BodyDef defaultDef = new BodyDef();
		defaultDef.fixedRotation = true;
		defaultDef.active = true;
		defaultDef.awake = true;
		defaultDef.type = BodyType.DYNAMIC;
		body = w.createBody(defaultDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(20, 20);
		body.createFixture(shape, 1f);
	}

	public BodyAdaptorComponent(World w, BodyDef def) {
		body = w.createBody(def);
	}

	@Override
	public void onUpdate() {		
		VectorHelper.setJBoxVectorFromSlickVector(getGameObject().getPos(), body.getPosition());
	}
	@Override
	public void onAdded() {
		VectorHelper.setSlickVectorFromJBoxVector(body.getPosition(), getGameObject().getPos());
	}

}