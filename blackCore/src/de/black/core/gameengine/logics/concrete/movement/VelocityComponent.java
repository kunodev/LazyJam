package de.black.core.gameengine.logics.concrete.movement;

import org.newdawn.slick.geom.Vector2f;

import de.black.core.gameengine.basic.ALogicComponent;

public class VelocityComponent extends ALogicComponent {
	
	public static String COMPONENT = "velocity";

	protected Vector2f v;
	
	public VelocityComponent() {
		this.v = new Vector2f(0f,0f);
	}

	public VelocityComponent(Vector2f v) {
		this.v = v;
	}
	
	@Override
	public void onUpdate() {
		this.getGameObject().getPos().add(v);
	}
	
	
	public Vector2f getV() {
		return v;
	}

	public void setV(Vector2f v) {
		this.v = v;
	}
	
	
	
	

}
