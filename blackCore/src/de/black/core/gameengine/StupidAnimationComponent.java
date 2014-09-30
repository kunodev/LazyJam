package de.black.core.gameengine;

import de.black.core.gameengine.basic.ALogicComponent;
import de.black.core.gameengine.logics.concrete.physics.BodyAdaptorComponent;
import de.black.core.gameengine.renderer.abstrct.SimpleAbstractAnimationComponent;

public class StupidAnimationComponent extends ALogicComponent {

	public static String COMPONENT = "stupidAnimation";

	public int TICKS_PER_CHANGE = 5;
	private int ticksCounted = 0;

	@Override
	public void onUpdate() {
		BodyAdaptorComponent bodycomp = getGameObject().getComponent(BodyAdaptorComponent.class);
		SimpleAbstractAnimationComponent aComp = getGameObject().getComponent(SimpleAbstractAnimationComponent.class);
		if (bodycomp.body.m_linearVelocity.x == 0 && bodycomp.body.m_linearVelocity.y == 0) {
			aComp.setState(0);
		} else {
			aComp.setState(1);
			ticksCounted++;
			if (ticksCounted >= TICKS_PER_CHANGE) {
				aComp.incrementXOffset();
				ticksCounted = 0;
			}
		}

	}

}
