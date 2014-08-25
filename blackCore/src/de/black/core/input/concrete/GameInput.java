package de.black.core.input.concrete;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.Input;

import de.black.core.constants.Tags;
import de.black.core.gameengine.basic.GameObject;
import de.black.core.gameengine.logics.concrete.physics.BodyAdaptorComponent;
import de.black.core.gamestatemanagement.GameStateManager;
import de.black.core.gamestatemanagement.concrete.GameGameState;
import de.black.core.input.IInput;
import de.black.core.input.InputConfiguration;
import de.black.core.input.InputConfiguration.InputCommand;

public class GameInput implements IInput{

	Input input;
	InputConfiguration config;
	public final static int V = 1;
	public final static Vec2 JUMPFORCE = new Vec2(0f,-5f);
	public final static Vec2 RUNFORCE = new Vec2(0.35f,0f);
	
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
			bodycomp.body.applyForceToCenter(RUNFORCE.mul(-1));
		} else if(input.isKeyDown(config.commandToKeyCodeMap.get(InputCommand.RIGHT))) {
			bodycomp.body.applyForceToCenter(RUNFORCE);
		}
		
		if(input.isKeyDown(config.commandToKeyCodeMap.get(InputCommand.UP))) {
			if(Math.abs(bodycomp.body.m_linearVelocity.y) <= 0.01f) {
				bodycomp.body.applyForceToCenter(JUMPFORCE);
			}
		}
	}

}
