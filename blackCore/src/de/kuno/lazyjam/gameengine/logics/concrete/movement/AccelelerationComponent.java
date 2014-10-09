package de.kuno.lazyjam.gameengine.logics.concrete.movement;

import org.newdawn.slick.geom.Vector2f;

import de.kuno.lazyjam.tools.cdi.annotations.Update;
import de.kuno.lazyjam.gameengine.logics.concrete.movement.VelocityComponent;
import de.kuno.lazyjam.helper.map.Component;

@Component(name = "accel")
public class AccelelerationComponent extends VelocityComponent {


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

	@Update
	public void onUpdate(Vector2f pos) {
		v.add(a);
		super.onUpdate(pos);
	}

	public Vector2f getA() {
		return a;
	}

	public void setA(Vector2f a) {
		this.a = a;
	}

}
