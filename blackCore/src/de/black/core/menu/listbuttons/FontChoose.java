package de.black.core.menu.listbuttons;

import de.black.core.tools.text.FontManager;

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
