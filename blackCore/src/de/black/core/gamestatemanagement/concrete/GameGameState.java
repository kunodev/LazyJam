package de.black.core.gamestatemanagement.concrete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.tiled.TiledMap;

import de.black.core.camera.Cam;
import de.black.core.constants.Settings;
import de.black.core.gameengine.basic.GameObject;
import de.black.core.gamestatemanagement.AGameState;
import de.black.core.input.IInput;
import de.black.core.input.InputConfiguration;
import de.black.core.input.concrete.GameInput;
import de.black.core.input.concrete.MouseInput;
import de.black.core.main.MainGameWindow;
import de.black.core.tools.dua.set.QueueSet;

public class GameGameState extends AGameState {

	public static final int ID = 3;
	
	public List<GameObject> gameObjects;
	public Map<String, Collection<GameObject>> taggedGameObjects;
	protected List<GameObject> trash;
	protected List<GameObject> ingoing;
	private TiledMap map;
	private Cam cam;

	public GameGameState(GameContainer gc, IInput input) {
		super(input);
		gameObjects = new ArrayList<GameObject>();
		taggedGameObjects = new HashMap<String, Collection<GameObject>>();
		trash = new ArrayList<GameObject>();
		ingoing = new ArrayList<GameObject>();
	}
	
	public GameGameState(GameContainer gc) {
		super(new GameInput().init(gc.getInput(), new InputConfiguration()));
		super.addInputHandler(new MouseInput().init(gc.getInput(), new InputConfiguration()));
		gameObjects = new ArrayList<GameObject>();
		taggedGameObjects = new HashMap<String, Collection<GameObject>>();
		trash = new ArrayList<GameObject>();
	}

	@Override
	public void onRender() {
		if(map != null) {
			map
		 	 .render(cam.getX() % Settings.getInstance().getInt("TILE_SIZE"),
			 		cam.getY() % Settings.getInstance().getInt("TILE_SIZE"),
			        cam.getX() / Settings.getInstance().getInt("TILE_SIZE"), 
			        cam.getY() / Settings.getInstance().getInt("TILE_SIZE"),
					(Settings.getInstance().getInt("SCREENWIDTH") / Settings.getInstance().getInt("TILE_SIZE")) + 2,
					(Settings.getInstance().getInt("SCREENHEIGHT") / Settings.getInstance().getInt("TILE_SIZE")) + 2);
////	
//			map.render(cam.getX(), cam.getY());
//			map.render(0, 0, 0, 0, 1024, 768);
		}
		gameObjects.stream().forEach(e -> e.onRender());
	}

	@Override
	protected void update(GameContainer gc) {
		if(!trash.isEmpty()) {
			gameObjects.removeAll(trash);
			for(Entry<String, Collection<GameObject>> taggedObjects : this.taggedGameObjects.entrySet()) {
				taggedObjects.getValue().removeAll(trash);
			}
			trash.clear();
		}
		if(!ingoing.isEmpty()) {
			gameObjects.addAll(ingoing);
			ingoing.clear();
		}
		gameObjects.stream().forEach(e -> e.onUpdate());
	}

	@Override
	public int getGameStateID() {
		return ID;
	}
	
	public void addGameObject(GameObject go) {
		ingoing.add(go);
	}
	
	public void removeGameObject(GameObject go) {
		trash.add(go);
	}
	
	public void init() {
		//TODO: If used in core Project, give new init method
//		CoreGameContentProvider.initGameObjects();
	}

	public void addGameObject(GameObject gameObject, String tag) {
		this.addGameObject(gameObject);
		this.addTag(gameObject, tag);
	}
	
	public GameObject getFirstTaggedGameObject(String tag) {
		return this.taggedGameObjects.get(tag).stream().findFirst().get();
	}
	
	public Collection<GameObject> getTaggedGameObjects(String tag) {
		Collection<GameObject> result = this.taggedGameObjects.get(tag);
		if(result == null) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public void addTag(GameObject gameObject, String tag) {
		if(this.taggedGameObjects.get(tag) == null) {
			this.taggedGameObjects.put(tag, new QueueSet<GameObject>());
		}
		this.taggedGameObjects.get(tag).add(gameObject);		
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}

	public Cam getCam() {
		return cam;
	}

	public void setCam(Cam cam) {
		this.cam = cam;
	}

}
