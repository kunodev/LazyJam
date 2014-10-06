package de.kuno.lazyjam.gameengine.logics.concrete.movement;

import org.newdawn.slick.geom.Vector2f;

import de.kuno.lazyjam.gameengine.basic.GameObject;
import de.kuno.lazyjam.helper.map.Component;
import de.kuno.lazyjam.tools.cdi.annotations.Update;

@Component(name="velocity")
public class VelocityComponent {

	protected Vector2f v;

	public VelocityComponent() {
		this.v = new Vector2f(0f, 0f);
	}

	public VelocityComponent(Vector2f v) {
		this.v = v;
	}

	@Update
	public void onUpdate(Vector2f pos) {
		pos.add(v);
	}

	public Vector2f getV() {
		return v;
	}

	public void setV(Vector2f v) {
		this.v = v;
	}

}
