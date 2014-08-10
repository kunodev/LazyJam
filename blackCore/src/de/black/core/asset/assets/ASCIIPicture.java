package de.black.core.asset.assets;

import org.newdawn.slick.geom.Rectangle;

import de.black.core.tools.text.FontDefinition;
import de.black.core.tools.text.FontManager;


public class ASCIIPicture extends ARenderableObject{
	
	private String picture;
	private FontDefinition drawingFont;
	
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
	
}
