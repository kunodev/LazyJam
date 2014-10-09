package de.kuno.lazyjam.tools.text;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;

import de.kuno.lazyjam.camera.Cam;
import de.kuno.lazyjam.constants.Constants;
import de.kuno.lazyjam.tools.cdi.annotations.InjectedService;
import de.kuno.lazyjam.tools.cdi.annotations.Service;

@Service
public class FontManager {

	@InjectedService
	private Cam cam;
	
	/**
	 * Font to use TODO: put into a global config? => when option menu comes
	 */
	private FontDefinition font;
	private int lowestAssetFont = 0;

	public FontManager() {
		
	}
	
	public void init(int fontNumber) {
		font = new FontDefinition(fontNumber);
	}

	public void drawTextRelative(int xoffset, int yoffset, String text) {
		drawTextRelative(xoffset, yoffset, text, Constants.DEFAULT_ID, Color.black);
	}

	public void drawTextRelative(int xoffset, int yoffset, String text, int stateId) {
		drawTextRelative(xoffset, yoffset, text, stateId, Color.black);
	}

	public void drawTextRelative(int xoffset, int yoffset, String text, Color color) {
		drawTextRelative(xoffset, yoffset, text, Constants.DEFAULT_ID, color);
	}

	public void drawTextRelative(int xoffset, int yoffset, String text, int stateId, Color color) {
		Cam cam = this.cam;
		if(this.font == null) {
			init(3); //TODO: MAGIC NUMBER
		}
		font.getTtfFont().drawString(cam.getX() + xoffset, cam.getY() + yoffset, text, color);
	}

	public void drawTextAbsolut(int xpos, int ypos, String text) {
		drawTextAbsolut(xpos, ypos, text, Constants.DEFAULT_ID, Color.black);
	}

	public void drawTextAbsolut(int xpos, int ypos, String text, int stateId) {
		drawTextAbsolut(xpos, ypos, text, stateId, Color.black);
	}

	public void drawTextAbsolut(int xpos, int ypos, String text, Color color) {
		drawTextAbsolut(xpos, ypos, text, Constants.DEFAULT_ID, color);
	}

	public void drawTextAbsolut(int xpos, int ypos, String text, int stateId, Color color) {
		if(this.font == null) {
			init(3); //TODO: MAGIC NUMBER
		}
		font.getTtfFont().drawString(xpos, ypos, text, color);
	}

	public String getCurrentFontName(int gamestate) {
		return font.getAwtFont().getFontName();
	}

	public void nextFont(int gamestate) {
		font.awtFontIncr();
	}

	public void prevFont(int gamestate) {
		font.awtFontDecr();
	}

	public void nextFont() {
		nextFont(Constants.DEFAULT_ID);
	}

	public void prevFont() {
		prevFont(Constants.DEFAULT_ID);
	}

	public String getCurrentFontName() {
		return getCurrentFontName(Constants.DEFAULT_ID);
	}

	public FontDefinition getFontDefinition() {
		return font;
	}

	public void drawTextAbsolut(float x, float y, String text) {
		this.drawTextAbsolut((int) x, (int) y, text);
	}

	public void drawTextAbsolut(float x, float y, String text, Color color) {
		this.drawTextAbsolut((int) x, (int) y, text, color);
	}

}
