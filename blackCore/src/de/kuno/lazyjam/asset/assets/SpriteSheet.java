package de.kuno.lazyjam.asset.assets;

import org.newdawn.slick.geom.Rectangle;

public class SpriteSheet extends ImagePicture {

	public int maxX = 1;
	public int maxY = 1;

	public SpriteSheet(String file) {
		super(file);
	}

	@Override
	public Rectangle getRectangle() {
		// TODO Auto-generated method stub
		if (this.getPicture() == null) {
			return new Rectangle(0, 0, 0, 0);
		}
		return new Rectangle(0, 0, this.getPicture().getWidth() / maxX, getPicture().getHeight() / maxY);
	}

}
