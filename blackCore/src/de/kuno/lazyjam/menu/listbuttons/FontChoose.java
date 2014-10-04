package de.kuno.lazyjam.menu.listbuttons;

import de.kuno.lazyjam.tools.text.FontManager;

public class FontChoose extends AChooseFromList {

	public FontChoose() {
		super(FontManager.getInstance().getCurrentFontName());
	}

	@Override
	public void right() {
		FontManager.getInstance().nextFont();
		this.setText(FontManager.getInstance().getCurrentFontName());
	}

	@Override
	public void left() {
		FontManager.getInstance().prevFont();
		this.setText(FontManager.getInstance().getCurrentFontName());
	}

}
