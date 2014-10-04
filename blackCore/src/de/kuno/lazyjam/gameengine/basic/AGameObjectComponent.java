package de.kuno.lazyjam.gameengine.basic;

import org.newdawn.slick.geom.Vector2f;

public abstract class AGameObjectComponent {

	private GameObject go;

	public void setGameObject(GameObject go) {
		this.go = go;
	}

	protected GameObject getGameObject() {
		return go;
	}

	public Vector2f getPos() {
		return go.getPos();
	}

	public void onAdded() {
		// For Components that are initialized, when the gameObject exists
	}

	/**
	 * OverWrite me if your component needs initializationData
	 * 
	 * @param val
	 */
	public void initWithString(String val) {

	}
}
