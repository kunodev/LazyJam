package de.black.core.asset.assets;

import org.newdawn.slick.geom.Rectangle;

public abstract class ARenderableObject {

	public abstract Rectangle getRectangle();
	public abstract Object getPicture();
	public abstract boolean load(String path);
	
}
