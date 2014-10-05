package de.kuno.lazyjam.gameengine.logics.concrete.movement;

import org.newdawn.slick.geom.Vector2f;

import de.kuno.lazyjam.gameengine.basic.GameObject;
import de.kuno.lazyjam.tools.cdi.annotations.Update;

public class VelocityComponent {

	public static String COMPONENT = "velocity";

	protected Vector2f v;

	public VelocityComponent() {
		this.v = new Vector2f(0f, 0f);
	}

	public VelocityComponent(Vector2f v) {
		this.v = v;
	}

	@Update
	public void onUpdate(GameObject go) {
		go.getPos().add(v);
	}

	public Vector2f getV() {
		return v;
	}

	public void setV(Vector2f v) {
		this.v = v;
	}

}
