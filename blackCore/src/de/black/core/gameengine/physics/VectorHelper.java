package de.black.core.gameengine.physics;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.geom.Vector2f;

public class VectorHelper {
	
	public static final float SCALING_FACTOR = 100f;
	
	public static void setToJboxVector(Vec2 target, Vector2f source) {
		target.set(source.x/SCALING_FACTOR, source.y/SCALING_FACTOR);
	}
	
	public static void setSlickVector(Vector2f target, Vec2 source) {
		target.set(source.x*SCALING_FACTOR, source.y*SCALING_FACTOR);
	}
	
	public static Vec2 createVec2Scaled(float x, float y) {
		Vec2 result = new Vec2(x/SCALING_FACTOR, y/SCALING_FACTOR);
		return result;
	}
	
	public static float scaleDown(float x) {
		return x/SCALING_FACTOR;
	}
	
}
