package de.black.core.gameengine.renderer.abstrct;

import java.util.ArrayList;

import org.newdawn.slick.geom.Rectangle;

import de.black.core.asset.assets.ARenderableObject;
import de.black.core.gameengine.basic.ARenderComponent;

public abstract class SimpleAbstractAnimationComponent extends ARenderComponent{

	protected int xOffset;
	protected int state;
	protected ArrayList<ArrayList<? extends ARenderableObject>> renderableObjects;
	

	public int getxOffset() {
		return xOffset;
	}
	
	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
		this.xOffset = this.xOffset % renderableObjects.get(state).size();
	}
	
	public void incrementXOffset() {
		this.xOffset = (this.xOffset + 1) % renderableObjects.get(state).size();
	}
	
	public Rectangle getDefaultRectangle() {
		return renderableObjects.get(0).get(0).getRectangle();
	}
	
	public abstract boolean load(String filePath);


}
