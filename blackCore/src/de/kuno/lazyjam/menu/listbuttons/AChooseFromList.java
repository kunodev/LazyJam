package de.kuno.lazyjam.menu.listbuttons;

import de.kuno.lazyjam.menu.SimpleMenuObject;

public abstract class AChooseFromList extends SimpleMenuObject {

	public static final int ID = 3;

	public AChooseFromList(String text) {
		super(text);
	}

	public abstract void right();

	public abstract void left();

	@Override
	public int getActionID() {
		return ID;
	}

}
