package de.black.core.tools.physics;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Collision;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.WorldManifold;
import org.jbox2d.collision.Collision.PointState;
import org.jbox2d.common.Settings;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

public class TestContactListener implements ContactListener{
	

	private static final int MAX_CONTACT_POINTS = 100;
	private final PointState[] state1 = new PointState[Settings.maxManifoldPoints];
	private final PointState[] state2 = new PointState[Settings.maxManifoldPoints];
	private final WorldManifold worldManifold = new WorldManifold();
	private int pointCount;
	private ContactPoint[] points;
	
	@Override
	public void beginContact(Contact contact) {
		
	}

	@Override
	public void endContact(Contact contact) {		
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		  Manifold manifold = contact.getManifold();

		    if (manifold.pointCount == 0) {
		      return;
		    }

		    Fixture fixtureA = contact.getFixtureA();
		    Fixture fixtureB = contact.getFixtureB();

		    Collision.getPointStates(state1, state2, oldManifold, manifold);

		    contact.getWorldManifold(worldManifold);

		    for (int i = 0; i < manifold.pointCount && pointCount < MAX_CONTACT_POINTS; i++) {
		      ContactPoint cp = points[pointCount];
		      cp.fixtureA = fixtureA;
		      cp.fixtureB = fixtureB;
		      cp.position.set(worldManifold.points[i]);
		      cp.normal.set(worldManifold.normal);
		      cp.state = state2[i];
		      cp.normalImpulse = manifold.points[i].normalImpulse;
		      cp.tangentImpulse = manifold.points[i].tangentImpulse;
		      ++pointCount;
		    }
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

}
