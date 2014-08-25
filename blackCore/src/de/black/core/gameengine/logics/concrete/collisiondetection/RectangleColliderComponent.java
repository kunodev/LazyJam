package de.black.core.gameengine.logics.concrete.collisiondetection;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import de.black.core.asset.assets.ARenderableObject;
import de.black.core.gameengine.basic.ALogicComponent;
import de.black.core.gameengine.basic.ARenderComponent;
import de.black.core.gameengine.logics.abstrct.AColliderComponent;
import de.black.core.gameengine.renderer.abstrct.SimpleAbstractAnimationComponent;

public abstract class RectangleColliderComponent extends AColliderComponent{
	
	public static String COMPONENT = "RectangleColliderComponent";
	
	public enum DIRECTION {
		UP,DOWN,LEFT,RIGHT,NONE
	}
	protected Rectangle collisionBox;
	
	public RectangleColliderComponent() {
		super();
		collisionBox = getGameObject().getComponent(SimpleAbstractAnimationComponent.class).getDefaultRectangle();	
	}
	
	public Rectangle getRectangle() {
		return collisionBox;
	}

	@Override
	public void onUpdate() {
		
	}
	
	public DIRECTION intersectsAt(AColliderComponent otherComp) {
		if(!intersects(otherComp)){
			return DIRECTION.NONE;
		}
		float width = collisionBox.getWidth();
		float height = collisionBox.getHeight();
//		Vector2f vWidth = new Vector2f(width,0f);
//		Vector2f vHeight = new Vector2f(0f,height);
//		Vector2f topRight = getGameObject().getPos().copy().add(vWidth);
//		Vector2f bottomRight = getGameObject().getPos().copy().add(vHeight).add(vWidth);
//		Vector2f bottomLeft = getGameObject().getPos().copy().add(vHeight);
		boolean topLeft = otherComp.shape.contains(getGameObject().getPos().x, getGameObject().getPos().y);
		boolean topRight = otherComp.shape.contains(getGameObject().getPos().x + width, getGameObject().getPos().y);
		boolean bottomLeft = otherComp.shape.contains(getGameObject().getPos().x, getGameObject().getPos().y + height);
		boolean bottomRight = otherComp.shape.contains(getGameObject().getPos().x + width, getGameObject().getPos().y + height);
		if(topLeft && topRight) {
			return DIRECTION.UP;
		}
		if(topLeft && bottomLeft) {
			return DIRECTION.LEFT;
		}
		if(bottomLeft && bottomRight) {
			return DIRECTION.DOWN;
		}
		if(bottomRight && topRight) {
			return DIRECTION.RIGHT;
		}
		return DIRECTION.NONE;
	}
	
	private void updateLines() {
	}
}
