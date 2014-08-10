package de.black.core.gameengine.physics;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.geom.Vector2f;

public class VectorHelper {
	
	public static void setSlickVectorFromJBoxVector(Vec2 target, Vector2f source) {
		target.x = source.x;
		target.y = source.y;
	}
	
	public static void setJBoxVectorFromSlickVector(Vector2f target, Vec2 source) {
		target.x = source.x;
		target.y = source.y;
	}
	
}
