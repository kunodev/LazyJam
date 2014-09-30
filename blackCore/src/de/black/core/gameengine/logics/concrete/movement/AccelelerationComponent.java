package de.black.core.gameengine.logics.concrete.movement;

import org.newdawn.slick.geom.Vector2f;

public class AccelelerationComponent extends VelocityComponent {

	public static String COMPONENT = "accel";

	protected Vector2f a;

	public AccelelerationComponent(Vector2f v) {
		super(v);
		this.a = new Vector2f(0f, 0f);
	}

	public AccelelerationComponent(Vector2f v, Vector2f a) {
		super(v);
		this.a = a;
	}

	public AccelelerationComponent() {
		super();
		this.a = new Vector2f(0f, 0f);
	}

	@Override
	public void onUpdate() {
		v.add(a);
		super.onUpdate();
	}

	public Vector2f getA() {
		return a;
	}

	public void setA(Vector2f a) {
		this.a = a;
	}

}
