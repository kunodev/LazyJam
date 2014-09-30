package de.black.core.gamestatemanagement.concrete;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import de.black.core.camera.Cam;
import de.black.core.constants.Settings;
import de.black.core.gameengine.basic.GameObject;
import de.black.core.gamestatemanagement.AGameState;
import de.black.core.input.IInput;
import de.black.core.input.InputConfiguration;
import de.black.core.input.concrete.GameInput;
import de.black.core.input.concrete.MouseInput;
import de.black.core.tools.dua.set.QueueSet;
import de.black.core.tools.vectors.VectorDistanceComparator;

public class GameGameState extends AGameState {

	public static final int ID = 3;

	public Collection<GameObject> gameObjects;
	public Map<String, Collection<GameObject>> taggedGameObjects;
	private TiledMap map;
	private Cam cam;

	public GameGameState(GameContainer gc, IInput input) {
		super(input);
		taggedGameObjects = new HashMap<String, Collection<GameObject>>();
	}

	public GameGameState(GameContainer gc) {
		super(new GameInput().init(gc.getInput(), new InputConfiguration()));
		super.addInputHandler(new MouseInput().init(gc.getInput(), new InputConfiguration()));
		taggedGameObjects = new HashMap<String, Collection<GameObject>>();
	}

	@Override
	public void onRender() {
		if (map != null) {
			map.render(cam.getX() % Settings.getInstance().getInt("TILE_SIZE"), cam.getY()
					% Settings.getInstance().getInt("TILE_SIZE"),
					cam.getX() / Settings.getInstance().getInt("TILE_SIZE"), cam.getY()
							/ Settings.getInstance().getInt("TILE_SIZE"),
					(Settings.getInstance().getInt("SCREENWIDTH") / Settings.getInstance().getInt("TILE_SIZE")) + 2,
					(Settings.getInstance().getInt("SCREENHEIGHT") / Settings.getInstance().getInt("TILE_SIZE")) + 2);
			// //
			// map.render(cam.getX(), cam.getY());
			// map.render(0, 0, 0, 0, 1024, 768);
		}
		gameObjects.stream().forEach(e -> e.onRender());
	}

	@Override
	protected void update(GameContainer gc) {
		updateAbles.stream().forEach(e -> e.run());
		for (GameObject go : gameObjects) {
			go.onUpdate();
		}
	}

	@Override
	public int getGameStateID() {
		return ID;
	}

	public void addGameObject(GameObject go) {
		gameObjects.add(go);
	}

	public void removeGameObject(GameObject go, String tag) {
		gameObjects.remove(go);
		if (tag != null) {
			taggedGameObjects.get(tag).remove(go);
		}
	}

	public void init() {
		gameObjects = new QueueSet<GameObject>();
		// TODO: If used in core Project, give new init method
		// CoreGameContentProvider.initGameObjects();
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
		if (result == null) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public void addTag(GameObject gameObject, String tag) {
		if (this.taggedGameObjects.get(tag) == null) {
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

	public void registerUpdateable(Runnable r) {
		this.updateAbles.add(r);
	}

	public List<GameObject> getGameObjectsInRange(Vector2f pos, int width, int height) {
		return getGameObjectsInRange(pos, width, height, gameObjects);
	}

	public List<GameObject> getGameObjectsInRange(Vector2f pos, int width, int height, String tag) {
		Collection<GameObject> tagged = taggedGameObjects.get(tag);
		if (tagged == null) {
			return Collections.emptyList();
		}
		return getGameObjectsInRange(pos, width, height, tagged);
	}

	public List<GameObject> getGameObjectsInRange(Vector2f pos, int width, int height, Collection<GameObject> list) {
		Rectangle range = new Rectangle(pos.x, pos.y, width, height);
		Stream<GameObject> filter = list.stream().filter(e -> range.contains(e.getPos().x, e.getPos().y));
		return filter.collect(Collectors.toList());
	}

	public List<GameObject> getClosestGameObject(Vector2f pos) {
		return getClosestGameObject(pos, gameObjects);
	}

	public List<GameObject> getClosestGameObject(Vector2f pos, String tag) {
		Collection<GameObject> tagged = taggedGameObjects.get(tag);
		if (tagged == null) {
			return Collections.emptyList();
		}
		return getClosestGameObject(pos, tagged);
	}

	public List<GameObject> getClosestGameObject(Vector2f pos, Collection<GameObject> list) {
		return list.stream().sorted(new VectorDistanceComparator(pos)).collect(Collectors.toList());
	}

}
