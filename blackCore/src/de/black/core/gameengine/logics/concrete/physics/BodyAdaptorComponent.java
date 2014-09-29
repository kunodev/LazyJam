package de.black.core.gameengine.logics.concrete.physics;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.geom.Rectangle;

import de.black.core.gameengine.basic.ALogicComponent;
import de.black.core.gameengine.renderer.abstrct.SimpleAbstractAnimationComponent;
import de.black.core.gamestatemanagement.GameStateManager;
import de.black.core.gamestatemanagement.concrete.GameGameState;
import de.black.core.gamestatemanagement.concrete.GameGameStateWithPhysics;
import de.black.core.tools.log.LogManager;
import de.black.core.tools.physics.BodyBuilder;
import de.black.core.tools.vectors.VectorConverter;

public class BodyAdaptorComponent extends ALogicComponent{
	

	public static String COMPONENT = "rigidBody";
	
	public Body body;
	
	public BodyAdaptorComponent(World w) {
	
	}

	public BodyAdaptorComponent() {
		
	}

	@Override
	public void onUpdate() {
//		System.out.println("update pre: " + getGameObject().getPos().toString());
//		System.out.println("update pre: " + body.getPosition());
		VectorConverter.setSlickVector(getGameObject().getPos(), body.getPosition());
//		System.out.println("update post: " + getGameObject().getPos().toString());
//		System.out.println("update post: " + body.getPosition());
	}
	
	public void buildBody(BodyBuilder bb) {
		this.body = bb.withPosition(VectorConverter.createVec2Scaled(getGameObject().getPos().x, getGameObject().getPos().y)).build();
	}
	
	public void buildBodyWithSpriteCollider(BodyBuilder bb) {
		SimpleAbstractAnimationComponent comp = getGameObject().getComponent(SimpleAbstractAnimationComponent.class);
		float width = 0.2f;
		float height = 0.2f;
		if(comp != null) {
			Rectangle coll = comp.getDefaultRectangle();
			width = VectorConverter.scaleDown(coll.getWidth());
			height = VectorConverter.scaleDown(coll.getHeight());
		}
		this.buildBody(bb.withRectanglePolygonShape(width, height));
	}
	
	public void buildBodyWithSpriteColliderAndFixtureDef(BodyBuilder bb, FixtureDef fd) {
		buildBodyWithSpriteCollider(bb.withFixtureDef(fd));
	}
	
	public void initWithString(String string) {
		World w = GameStateManager.getInstance().getGameStateAs(GameGameStateWithPhysics.class,GameGameState.ID).getWorld();
		if(string.equals("simple")) {
			BodyBuilder simpleBuilder = BodyBuilder.getBodyBuilder(w).withAwake(true).withActive(true).withType(BodyType.DYNAMIC);
			this.buildBodyWithSpriteCollider(simpleBuilder);
		}
		if(string.equals("wall")) {
			BodyBuilder floorBuilder = BodyBuilder.getBodyBuilder(w)
					.withType(BodyType.STATIC)
							.withActive(true);
			this.buildBodyWithSpriteCollider(floorBuilder);
		}
	}

}