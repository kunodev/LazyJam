package de.black.core.gameengine.logics.collisiondetection.helper;

import java.util.ArrayList;
import java.util.List;

import de.black.core.gameengine.logics.collisiondetection.AColliderComponent;
import de.black.core.gameengine.logics.collisiondetection.RectangleColliderComponent;

public class CollisionManager {
	
	private static CollisionManager instance;
	
	public static CollisionManager getInstance() {
		if(instance == null) {
			instance = new CollisionManager();
		}
		return instance;
	}

	private List<AColliderComponent> collidingObjects;
	
	private CollisionManager() {
		collidingObjects = new ArrayList<AColliderComponent>();
	}
	
	public void registerComponent(AColliderComponent aColliderComponent) {
		this.collidingObjects.add(aColliderComponent);
	}

}
