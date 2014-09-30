package de.black.core.tools.vectors;

import java.util.Comparator;

import org.newdawn.slick.geom.Vector2f;

import de.black.core.gameengine.basic.GameObject;

public class VectorDistanceComparator implements Comparator<GameObject> {

	Vector2f referenceVector;

	public VectorDistanceComparator(GameObject reference) {
		this.referenceVector = reference.getPos();
	}

	public VectorDistanceComparator(Vector2f referenceVector) {
		this.referenceVector = referenceVector;
	}

	@Override
	public int compare(GameObject o1, GameObject o2) {
		return distance(o1.getPos(), o2.getPos(), referenceVector);
	}

	public static int distance(Vector2f o1, Vector2f o2, Vector2f ref) {
		return (int) (ref.distance(o1) - ref.distance(o2));
	}

}
