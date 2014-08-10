package de.black.core.input.concrete;

import org.newdawn.slick.Input;

import de.black.core.gamestatemanagement.GameStateManager;
import de.black.core.gamestatemanagement.concrete.MenuGameState;
import de.black.core.input.IInput;
import de.black.core.input.InputConfiguration;
import de.black.core.input.InputConfiguration.InputCommand;

public class MenuInput implements IInput{

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
		
		if(input.isKeyPressed(config.commandToKeyCodeMap.get(InputCommand.CONFIRM))) {
			menu.clickOkay();
		}
		if(input.isKeyPressed(config.commandToKeyCodeMap.get(InputCommand.CANCEL))) {
			menu.clickCancel();
		}
		if(input.isKeyPressed(config.commandToKeyCodeMap.get(InputCommand.DOWN))) {
			menu.down();
		}	
		if(input.isKeyPressed(config.commandToKeyCodeMap.get(InputCommand.UP))) {
			menu.up();
		}
		if(input.isKeyPressed(config.commandToKeyCodeMap.get(InputCommand.LEFT))) {
			menu.pressSide(true);
		}
		if(input.isKeyPressed(config.commandToKeyCodeMap.get(InputCommand.RIGHT))) {
			menu.pressSide(false);
		}
		
	}
}
