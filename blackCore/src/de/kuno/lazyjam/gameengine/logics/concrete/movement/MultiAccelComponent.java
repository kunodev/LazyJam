package de.kuno.lazyjam.gameengine.logics.concrete.movement;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import de.kuno.lazyjam.gameengine.basic.GameObject;
import de.kuno.lazyjam.helper.map.Component;
@Component(name="multiAccel")
public class MultiAccelComponent extends AccelelerationComponent {


	private List<Vector2f> accellerations;

	public MultiAccelComponent() {
		accellerations = new ArrayList<Vector2f>();
	}

	public void addAccelleration(Vector2f a_x) {
		accellerations.add(a_x);
	}

	@Override
	public void onUpdate(GameObject go) {
		Vector2f result = new Vector2f();
		accellerations.stream().forEach(a_x -> result.add(a_x));
		this.a = result;
		super.onUpdate(go);
	}

}
