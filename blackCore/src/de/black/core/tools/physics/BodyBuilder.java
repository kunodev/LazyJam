package de.black.core.tools.physics;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

public class BodyBuilder {
	//TODO: scale everything in meters (make it smaller)
	//TODO: Read manual pdf, try HelloWorldExample in this shit
	//TODO: find out why kitten does not fall to the ground
	public static BodyBuilder getBodyBuilder(World w) {
		return new BodyBuilder(w);
	}
	
	private World w;
	private BodyDef def;
	private Shape shape;
	private float density;
	
	private BodyBuilder(World w) {
		def = new BodyDef();
		def.fixedRotation = true;
		def.active = true;
		def.awake = true;
		def.type = BodyType.DYNAMIC;
		density = 1f;
		def = new BodyDef();
		this.w = w;
	}
	
	public BodyBuilder withFixedRotation(boolean fixed) {
		def.fixedRotation = fixed;
		return this;
	}
	
	public BodyBuilder withActive(boolean active) {
		def.active = true;
		return this;
	}
	
	public BodyBuilder withAwake(boolean awake) {
		def.awake = true;
		return this;
	}
	
	public BodyBuilder withType(BodyType type) {
		def.type = type;
		return this;
	}
	
	public BodyBuilder withDefaultPolygonShape() {
		PolygonShape pshape = new PolygonShape();
		pshape.setAsBox(0.2f, 0.2f);
		shape = pshape;
		return this;
	}
	
	public BodyBuilder withPolygonShape(Vec2[] vertices) {
		PolygonShape pshape = new PolygonShape();
		pshape.set(vertices, vertices.length);
		shape = pshape;
		return this;
	}
	
	public BodyBuilder withRectanglePolygonShape(float wx, float hy) {
		PolygonShape pshape = new PolygonShape();
		pshape.setAsBox(wx, hy);
		shape = pshape;
		return this;
	}
	
	public BodyBuilder withDensity(float density) {
		this.density = density;
		return this;
	}
	
	public Body build() {
		Body result = w.createBody(def);
		result.createFixture(shape, density);
		return result;
	}
	
	

}
