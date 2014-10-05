package de.kuno.lazyjam.tools.text;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import org.newdawn.slick.TrueTypeFont;

public class FontDefinition {

	private int awtFont;
	private volatile TrueTypeFont ttfFont;
	private int gameState;

	public FontDefinition(int awtfontID) {
		this.awtFont = awtfontID;
		actuateFont();
	}

	private void actuateFont() {
		Font awtfont = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()[awtFont];
		this.ttfFont = new TrueTypeFont(awtfont.deriveFont(20f), false);
	}

	public Font getAwtFont() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()[awtFont];
	}

	public void setAwtFont(int awtFont) {
		if (awtFont < 0) {
			awtFont += GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts().length;
		}
		this.awtFont = awtFont;
		actuateFont();
	}

	public void awtFontIncr() {
		this.setAwtFont(this.awtFont + 1);
	}

	public void awtFontDecr() {
		this.setAwtFont(this.awtFont - 1);
	}

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	public TrueTypeFont getTtfFont() {
		return ttfFont;
	}

}
