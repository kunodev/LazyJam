package de.kuno.lazyjam.tools.physics;

import org.jbox2d.dynamics.FixtureDef;

public class FixtureDefBuilder {

	private FixtureDef result;

	public FixtureDefBuilder() {
		result = new FixtureDef();
		result.friction = 0f;
	}

	public FixtureDefBuilder withFriction(float friction) {
		result.friction = friction;
		return this;
	}

	public FixtureDefBuilder withRestitution(float restitution) {
		result.restitution = restitution;
		return this;
	}

	public FixtureDef build() {
		return result;
	}

}
