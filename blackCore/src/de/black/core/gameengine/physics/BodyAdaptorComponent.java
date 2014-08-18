package de.black.core.gameengine.physics;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import de.black.core.gameengine.basic.ALogicComponent;
import de.black.core.tools.log.LogManager;
import de.black.core.tools.physics.BodyBuilder;

public class BodyAdaptorComponent extends ALogicComponent{
	
	public Body body;
	
	public BodyAdaptorComponent(World w) {
	
	}

	public BodyAdaptorComponent() {
		
	}

	@Override
	public void onUpdate() {
//		System.out.println("update pre: " + getGameObject().getPos().toString());
//		System.out.println("update pre: " + body.getPosition());
		VectorHelper.setSlickVector(getGameObject().getPos(), body.getPosition());
//		System.out.println("update post: " + getGameObject().getPos().toString());
//		System.out.println("update post: " + body.getPosition());
	}
	
	public void buildBody(BodyBuilder bb) {
		this.body = bb.withPosition(VectorHelper.createVec2Scaled(getGameObject().getPos().x, getGameObject().getPos().y)).build();
	}

}