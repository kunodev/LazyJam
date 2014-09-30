package de.black.core.gameengine.logics.concrete.movement;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

public class MultiAccelComponent extends AccelelerationComponent {

	public static String COMPONENT = "multiAccel";

	private List<Vector2f> accellerations;

	public MultiAccelComponent() {
		accellerations = new ArrayList<Vector2f>();
	}

	public void addAccelleration(Vector2f a_x) {
		accellerations.add(a_x);
	}

	@Override
	public void onUpdate() {
		Vector2f result = new Vector2f();
		accellerations.stream().forEach(a_x -> result.add(a_x));
		this.a = result;
	}

}
