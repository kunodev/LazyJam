package de.black.core.gameengine;

import de.black.core.gameengine.basic.ALogicComponent;
import de.black.core.gameengine.renderer.SimpleAbstractAnimationComponent;

public class StupidAnimationComponent extends ALogicComponent{

	public int TICKS_PER_CHANGE = 5;
	private int ticksCounted = 0;
	
	@Override
	public void onUpdate() {
//		VelocityComponent vComp = getGameObject().getComponent(VelocityComponent.class);
//		SimpleAbstractAnimationComponent aComp = getGameObject().getComponent(SimpleAbstractAnimationComponent.class);
//		if(vComp.getV().x == 0 && vComp.getV().y == 0) {
//			aComp.setState(0);
//		}else {
//			aComp.setState(1);
//			ticksCounted++;
//			if(ticksCounted >= TICKS_PER_CHANGE) {
//				aComp.incrementXOffset();	
//				ticksCounted = 0;
//			}
//		}
		
		
	}
	
	
}
