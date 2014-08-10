package de.black.core.input;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Input;

public class InputConfiguration {

	public enum InputCommand {
		CONFIRM,
		CANCEL,
		LEFT,
		RIGHT,
		UP,
		DOWN
	}
	public Map<InputCommand, Integer> commandToKeyCodeMap;
	
	public InputConfiguration() {
		commandToKeyCodeMap = new HashMap<InputCommand, Integer>();
		commandToKeyCodeMap.put(InputCommand.CONFIRM, Input.KEY_ENTER);
		commandToKeyCodeMap.put(InputCommand.CANCEL, Input.KEY_BACK);
		commandToKeyCodeMap.put(InputCommand.LEFT, Input.KEY_A);
		commandToKeyCodeMap.put(InputCommand.RIGHT, Input.KEY_D);
		commandToKeyCodeMap.put(InputCommand.UP, Input.KEY_W);
		commandToKeyCodeMap.put(InputCommand.DOWN, Input.KEY_S);
	}
	
	
}
