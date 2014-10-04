package de.kuno.lazyjam.input.concrete;

import org.newdawn.slick.Input;

import de.kuno.lazyjam.gamestatemanagement.GameStateManager;
import de.kuno.lazyjam.gamestatemanagement.concrete.VNGameState;
import de.kuno.lazyjam.input.IInput;
import de.kuno.lazyjam.input.InputConfiguration;
import de.kuno.lazyjam.input.InputConfiguration.InputCommand;

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
