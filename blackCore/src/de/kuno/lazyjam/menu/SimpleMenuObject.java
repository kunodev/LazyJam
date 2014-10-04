package de.kuno.lazyjam.menu;

public abstract class SimpleMenuObject {

	private String text;

	public SimpleMenuObject(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	protected void setText(String text) {
		this.text = text;
	}

	public abstract int getActionID();

}
