package de.black.core.gameengine.renderer;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import de.black.core.asset.assets.ARenderableObject;
import de.black.core.asset.assets.ASCIIPicture;
import de.black.core.tools.text.FontManager;

public class ASCIISpriteAnimation extends SimpleAbstractAnimationComponent {

	public Color color;
	
	public ASCIISpriteAnimation(ArrayList<ArrayList<? extends ARenderableObject>> pic) {
		this.renderableObjects = pic;
	}
	
	public ASCIISpriteAnimation(ASCIIPicture[] pic) {
		this(new ArrayList<ArrayList<? extends ARenderableObject>>());
		addFromArray(pic);
	}
	
	public ASCIISpriteAnimation(ASCIIPicture pic) {
		this(new ArrayList<ArrayList<? extends ARenderableObject>>());
		ASCIIPicture[] pics = {pic};
		addFromArray(pics);
	}
	
	@Override
	public void onRender() {
		ASCIIPicture pic = (ASCIIPicture) this.renderableObjects.get(state).get(xOffset);
		String[] pics = pic.getPicture().split("\n");
		for(int i=0; i< pics.length; i++) {
			FontManager.getInstance().drawTextAbsolut((int)getGameObject().getPos().x,
													(int)getGameObject().getPos().y + (i*20),
														pics[i], pic.getDrawingFont().getGameState(), color);
		}
	}


	public void addFromArray(ASCIIPicture[] pic) {
		ArrayList<ASCIIPicture> picAsArray = new ArrayList<ASCIIPicture>();
		for(ASCIIPicture p : pic) {
			picAsArray.add(p);
		}
		this.renderableObjects.add(picAsArray);
	}
}
