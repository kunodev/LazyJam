package de.black.core.asset.assets;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class ImagePicture extends ARenderableObject {

	private Image picture;

	public ImagePicture() {

	}

	public ImagePicture(String path) {

		this.load(path);
	}

	public Image getPicture() {
		return picture;
	}

	public void setPicture(Image pic) {
		this.picture = pic;
	}

	@Override
	public Rectangle getRectangle() {
		// TODO Auto-generated method stub
		if (picture == null) {
			return new Rectangle(0, 0, 0, 0);
		}
		return new Rectangle(0, 0, picture.getWidth(), picture.getHeight());
	}

	public boolean load(String path) {
		try {
			this.picture = new Image(path);
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
		return true;
	}

}
