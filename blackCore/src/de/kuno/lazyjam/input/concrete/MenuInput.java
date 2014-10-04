package de.kuno.lazyjam.input.concrete;

import org.newdawn.slick.Input;

import de.kuno.lazyjam.gamestatemanagement.GameStateManager;
import de.kuno.lazyjam.gamestatemanagement.concrete.MenuGameState;
import de.kuno.lazyjam.input.IInput;
import de.kuno.lazyjam.input.InputConfiguration;
import de.kuno.lazyjam.input.InputConfiguration.InputCommand;

public class MenuInput implements IInput {

	Input input;
	InputConfiguration config;

	@Override
	public IInput init(Input i, InputConfiguration config) {
		this.input = i;
		this.config = config;
		return this;
	}

	@Override
	public void update() {
		MenuGameState menu = GameStateManager.getInstance().getGameStateAs(MenuGameState.class);

		if (input.isKeyPressed(config.commandToKeyCodeMap.get(InputCommand.CONFIRM))) {
			menu.clickOkay();
		}
		if (input.isKeyPressed(config.commandToKeyCodeMap.get(InputCommand.CANCEL))) {
			menu.clickCancel();
		}
		if (input.isKeyPressed(config.commandToKeyCodeMap.get(InputCommand.DOWN))) {
			menu.down();
		}
		if (input.isKeyPressed(config.commandToKeyCodeMap.get(InputCommand.UP))) {
			menu.up();
		}
		if (input.isKeyPressed(config.commandToKeyCodeMap.get(InputCommand.LEFT))) {
			menu.pressSide(true);
		}
		if (input.isKeyPressed(config.commandToKeyCodeMap.get(InputCommand.RIGHT))) {
			menu.pressSide(false);
		}

	}
}
