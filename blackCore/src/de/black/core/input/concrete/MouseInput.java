package de.black.core.input.concrete;

import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import de.black.core.constants.Tags;
import de.black.core.gameengine.basic.GameObject;
import de.black.core.gameengine.logics.abstrct.AMouseListenerComponent;
import de.black.core.gameengine.renderer.abstrct.SimpleAbstractAnimationComponent;
import de.black.core.gamestatemanagement.GameStateManager;
import de.black.core.gamestatemanagement.concrete.GameGameState;
import de.black.core.input.IInput;
import de.black.core.input.InputConfiguration;

public class MouseInput implements IInput{

	Input input;
	
	@Override
	public IInput init(Input i, InputConfiguration config) {
		input = i;
		return this;
	}

	@Override
	public void update() {
		GameGameState ggs = GameStateManager.getInstance().getGameStateAs(GameGameState.class);
		List<GameObject> mouseListeners = ggs.getTaggedGameObjects(Tags.MOUSE);
		int mX = input.getMouseX();
		int mY = input.getMouseY();
		for(GameObject mouseListener : mouseListeners) {
			Rectangle box = mouseListener.getComponent(SimpleAbstractAnimationComponent.class).getDefaultRectangle();
			box.setX(mouseListener.getPos().x);
			box.setY(mouseListener.getPos().y);
			
			if(box.contains(mX, mY)) {
				AMouseListenerComponent mouseComp = mouseListener.getComponent(AMouseListenerComponent.class);
				mouseComp.onHover();
				if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
					mouseComp.onClick();
				}
			}
		}
		
	}
	
	

}
