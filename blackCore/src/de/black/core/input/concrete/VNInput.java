package de.black.core.input.concrete;

import org.newdawn.slick.Input;

import de.black.core.gamestatemanagement.GameStateManager;
import de.black.core.gamestatemanagement.concrete.VNGameState;
import de.black.core.input.IInput;
import de.black.core.input.InputConfiguration;
import de.black.core.input.InputConfiguration.InputCommand;

public class VNInput implements IInput {
	private Input input;
	private InputConfiguration config;

	public static float speed = 5f;

	public void update() {
		VNGameState man = GameStateManager.getInstance().getGameStateAs(VNGameState.class);
		if (input.isKeyPressed(config.commandToKeyCodeMap.get(InputCommand.CONFIRM))) {
			man.triggerNext();
		}
	}

	@Override
	public IInput init(Input i, InputConfiguration config) {
		this.config = config;
		this.input = i;
		return this;
	}

}
