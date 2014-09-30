package de.black.core.input;

import org.newdawn.slick.Input;

public interface IInput {

	public IInput init(Input i, InputConfiguration config);

	public void update();

}
