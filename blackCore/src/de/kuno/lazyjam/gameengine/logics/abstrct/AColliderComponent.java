//package de.kuno.lazyjam.gameengine.logics.abstrct;
//
//import org.newdawn.slick.geom.Shape;
//
//import de.kuno.lazyjam.gameengine.basic.ALogicComponent;
//import de.kuno.lazyjam.gameengine.basic.helper.CollisionManager;
//
//public abstract class AColliderComponent extends ALogicComponent {
//
//	public Shape shape;
//
//	public AColliderComponent() {
//		CollisionManager.getInstance().registerComponent(this);
//	}
//
//	public void initLocation() {
//		this.shape.setLocation(getGameObject().getPos());
//	}
//
//	@Override
//	public void onUpdate() {
//
//	}
//
//	public abstract void onCollision(AColliderComponent otherCollider);
//
//	public boolean intersects(AColliderComponent otherComp) {
//		return this.shape.intersects(otherComp.shape);
//	}
//
//}
