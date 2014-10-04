package de.kuno.lazyjam.input.concrete;

import java.util.Collection;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import de.kuno.lazyjam.constants.Tags;
import de.kuno.lazyjam.gameengine.basic.GameObject;
import de.kuno.lazyjam.gameengine.logics.abstrct.AMouseListenerComponent;
import de.kuno.lazyjam.gameengine.renderer.abstrct.SimpleAbstractAnimationComponent;
import de.kuno.lazyjam.gamestatemanagement.GameStateContextManager;
import de.kuno.lazyjam.gamestatemanagement.concrete.GameGameState;
import de.kuno.lazyjam.input.IInput;
import de.kuno.lazyjam.input.InputConfiguration;

public class MouseInput implements IInput {

	Input input;

	@Override
	public IInput init(Input i, InputConfiguration config) {
		input = i;
		return this;
	}

	@Override
	public void update() {
		GameGameState ggs = GameStateContextManager.getInstance().getGameStateAs(GameGameState.class);
		Collection<GameObject> mouseListeners = ggs.getTaggedGameObjects(Tags.MOUSE);
		int mX = input.getMouseX();
		int mY = input.getMouseY();
		for (GameObject mouseListener : mouseListeners) {
			Rectangle box = mouseListener.getComponent(SimpleAbstractAnimationComponent.class).getDefaultRectangle();
			box.setX(mouseListener.getPos().x);
			box.setY(mouseListener.getPos().y);
			if (box.contains(mX, mY)) {
				AMouseListenerComponent mouseComp = mouseListener.getComponent(AMouseListenerComponent.class);
				mouseComp.onHover();
				if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
					mouseComp.onClick();
				}
			}
		}

	}

}
