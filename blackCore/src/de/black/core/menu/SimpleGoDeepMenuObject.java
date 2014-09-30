package de.black.core.menu;

public class SimpleGoDeepMenuObject extends SimpleMenuObject {

	public static final int ID = 1;

	public SimpleGoDeepMenuObject(String text) {
		super(text);
	}

	@Override
	public int getActionID() {
		return ID;
	}

}
