package de.kuno.lazyjam.input;

import org.newdawn.slick.Input;

public interface IInput {

	public IInput init(Input i, InputConfiguration config);

	public void update();

}
