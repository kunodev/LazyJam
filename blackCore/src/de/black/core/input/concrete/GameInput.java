package de.black.core.input.concrete;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.Input;

import de.black.core.constants.Tags;
import de.black.core.gameengine.basic.GameObject;
import de.black.core.gameengine.physics.BodyAdaptorComponent;
import de.black.core.gamestatemanagement.GameStateManager;
import de.black.core.gamestatemanagement.concrete.GameGameState;
import de.black.core.input.IInput;
import de.black.core.input.InputConfiguration;
import de.black.core.input.InputConfiguration.InputCommand;

public class GameInput implements IInput{

	Input input;
	InputConfiguration config;
	public final static int V = 5;
	public final static Vec2 JUMPFORCE = new Vec2(0f,10f);
	
	@Override
	public IInput init(Input i, InputConfiguration config) {
		this.input = i;
		this.config = config;
		return this;
	}

	@Override
	public void update() {
		GameGameState ggs = GameStateManager.getInstance().getGameStateAs(GameGameState.class);
		GameObject player = ggs.getFirstTaggedGameObject(Tags.PLAYER);
		BodyAdaptorComponent bodycomp = player.getComponent(BodyAdaptorComponent.class);
		if(input.isKeyDown(config.commandToKeyCodeMap.get(InputCommand.LEFT))) {
			bodycomp.body.m_linearVelocity.x = -V;
		} else if(input.isKeyDown(config.commandToKeyCodeMap.get(InputCommand.RIGHT))) {
			bodycomp.body.m_linearVelocity.x = V;
		} else {
			bodycomp.body.m_linearVelocity.x = 0f;
		}
		if(input.isKeyDown(config.commandToKeyCodeMap.get(InputCommand.UP))) {
			bodycomp.body.applyForceToCenter(JUMPFORCE);
		}
	}

}
