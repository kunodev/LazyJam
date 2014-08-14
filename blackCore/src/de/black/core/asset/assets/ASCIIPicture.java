package de.black.core.asset.assets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.newdawn.slick.geom.Rectangle;

import de.black.core.tools.text.FontDefinition;
import de.black.core.tools.text.FontManager;


public class ASCIIPicture extends ARenderableObject{
	
	private String picture;
	private FontDefinition drawingFont;

	public ASCIIPicture() {
	}
	
	public ASCIIPicture(String picture) {
		this.picture = picture;
		this.drawingFont = FontManager.getInstance().getFontDefinition();
	}

	public ASCIIPicture(String picture, FontDefinition fd) {
		this.picture = picture;
		this.drawingFont = fd;
		FontManager.getInstance().addAssetFont(fd);
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public Rectangle getRectangle() {
		String[] pictures = this.picture.split("\n");
		int maxLength = 0;
		for(String pic : pictures) {
			maxLength = Math.max(maxLength, pic.length());
		}
		int fontSize = this.drawingFont.getAwtFont().getSize();
		Rectangle result = new Rectangle(0, 0, fontSize*maxLength, fontSize*pictures.length);
		return result;
	}

	public FontDefinition getDrawingFont() {
		return drawingFont;
	}

	public void setDrawingFont(FontDefinition drawingFont) {
		this.drawingFont = drawingFont;
	}

	@Override
	public boolean load(String path) {
		// TODO Auto-generated method stub
		File f = new File(path);
		String picture = "";
		/* Load String from file */
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        picture = picture + line + "\n";
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		    return false;
		}
		this.picture = picture;
		this.drawingFont = FontManager.getInstance().getFontDefinition();
		return true;
	}
	
}
