package de.black.core.tools.text;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import de.black.core.camera.Cam;
import de.black.core.constants.Constants;
import de.black.core.main.MainGameWindow;

public class FontManager {

	private static FontManager instance;
	
	public static FontManager getInstance() {
		if(instance == null) {
			instance = new FontManager();
		}
		return instance;
	}

	/**
	 * Font to use TODO: put into a global config? => when option menu comes
	 */
	private List<FontDefinition> fonts;
	private int lowestAssetFont = 0;
	
	public FontManager() {
		fonts = new ArrayList<FontDefinition>();
		int fontNumber = 3;
		fonts.add(new FontDefinition(Constants.DEFAULT_ID, fontNumber));
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
		Cam cam = MainGameWindow.getInstance().getCam();
		fonts.stream()
				.filter(e -> e.getGameState() == stateId)
				.forEach(e -> e.getTtfFont().drawString(cam.getX() + xoffset, cam.getY() + yoffset, text, color));
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
		fonts.stream()
				.filter(e -> e.getGameState() == stateId)
				.forEach(e -> e.getTtfFont().drawString(xpos, ypos, text, color));
		
	}
	
	public String getCurrentFontName(int gamestate) {
		return fonts.stream()
				.filter(e -> e.getGameState() == gamestate)
				.findFirst().get().getAwtFont().getFontName();
	}
	
	public void nextFont(int gamestate) {
		fonts.stream()
			.filter(e -> e.getGameState() == gamestate)
			.findFirst().get().awtFontIncr();
	}
	
	public void prevFont(int gamestate) {
		fonts.stream()
			.filter(e -> e.getGameState() == gamestate)
			.findFirst().get().awtFontDecr();
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
		return getFontDefinition(Constants.DEFAULT_ID);
	}
	
	public FontDefinition getFontDefinition(int gamestate) {
		return fonts.stream().filter(e -> e.getGameState() == gamestate).findAny().get();
	}
	
	public void addAssetFont(FontDefinition fd) {
		fd.setGameState(--lowestAssetFont);
		this.fonts.add(fd);
	}
	
}
