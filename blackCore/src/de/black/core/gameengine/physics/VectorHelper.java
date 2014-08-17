package de.black.core.gameengine.physics;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.geom.Vector2f;

public class VectorHelper {
	
	public static final float SCALING_FACTOR = 100f;
	
	public static void setJBoxVector(Vec2 target, Vector2f source) {
		target.x = source.x/SCALING_FACTOR;
		target.y = source.y/SCALING_FACTOR;
	}
	
	public static void setSlickVector(Vector2f target, Vec2 source) {
		target.x = source.x*SCALING_FACTOR;
		target.y = source.y*SCALING_FACTOR;
	}
	
	public static Vec2 createVec2Scaled(float x, float y) {
		Vec2 result = new Vec2(x/SCALING_FACTOR, y/SCALING_FACTOR);
		return result;
	}
	
}
