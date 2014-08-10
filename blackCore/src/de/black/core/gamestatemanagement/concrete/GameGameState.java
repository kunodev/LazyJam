package de.black.core.gamestatemanagement.concrete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;

import de.black.core.content.game.CoreGameContentProvider;
import de.black.core.gameengine.basic.GameObject;
import de.black.core.gamestatemanagement.AGameState;
import de.black.core.input.IInput;
import de.black.core.input.InputConfiguration;
import de.black.core.input.concrete.GameInput;

public class GameGameState extends AGameState {

	public static final int ID = 3;
	
	public List<GameObject> gameObjects;
	public Map<String, List<GameObject>> taggedGameObjects;
	
	
	public GameGameState(GameContainer gc) {
		super(new GameInput().init(gc.getInput(), new InputConfiguration()));
		gameObjects = new ArrayList<GameObject>();
		taggedGameObjects = new HashMap<String, List<GameObject>>();
	}

	@Override
	public void onRender() {
		gameObjects.stream().forEach(e -> e.onRender());
	}

	@Override
	protected void update(GameContainer gc) {
		gameObjects.stream().forEach(e -> e.onUpdate());
	}

	@Override
	public int getGameStateID() {
		return ID;
	}
	
	public void addGameObject(GameObject go) {
		gameObjects.add(go);
	}
	
	public void removeGameObject(GameObject go) {
		gameObjects.remove(go);
	}
	
	public void init() {
		//TODO: If used in core Project, give new init method
//		CoreGameContentProvider.initGameObjects();
	}

	public void addGameObject(GameObject gameObject, String tag) {
		this.addGameObject(gameObject);
		if(this.taggedGameObjects.get(tag) == null) {
			this.taggedGameObjects.put(tag, new ArrayList<GameObject>());
		}
		this.taggedGameObjects.get(tag).add(gameObject);		
	}
	
	public GameObject getFirstTaggedGameObject(String tag) {
		return this.taggedGameObjects.get(tag).stream().findFirst().get();
	}
	
	public List<GameObject> getTaggedGameObjects(String tag) {
		return this.taggedGameObjects.get(tag);
	}

}
