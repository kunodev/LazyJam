package de.kuno.lazyjam.gamestatemanagement.concrete;

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

import de.kuno.lazyjam.camera.Cam;
import de.kuno.lazyjam.constants.Settings;
import de.kuno.lazyjam.gameengine.basic.GameObject;
import de.kuno.lazyjam.gamestatemanagement.AGameState;
import de.kuno.lazyjam.tools.cdi.manager.ServiceManager;
import de.kuno.lazyjam.tools.dua.set.QueueSet;
import de.kuno.lazyjam.tools.vectors.VectorDistanceComparator;

public class GameState extends AGameState {

	public Collection<GameObject> gameObjects;
	public Map<String, Collection<GameObject>> taggedGameObjects;
	private TiledMap map;
	
	public GameState() {
		taggedGameObjects = new HashMap<String, Collection<GameObject>>();
		gameObjects = new QueueSet<GameObject>(this);
	}

	@Override
	public void onRender(ServiceManager serviceMan) {
		Cam cam = serviceMan.getService(Cam.class);
		if (map != null) {
			map.render(cam.getX() % serviceMan.getService(Settings.class).getInt("TILE_SIZE"), cam.getY()
					% serviceMan.getService(Settings.class).getInt("TILE_SIZE"),
					cam.getX() / serviceMan.getService(Settings.class).getInt("TILE_SIZE"), cam.getY()
							/ serviceMan.getService(Settings.class).getInt("TILE_SIZE"),
					(serviceMan.getService(Settings.class).getInt("SCREENWIDTH") / serviceMan.getService(Settings.class).getInt("TILE_SIZE")) + 2,
					(serviceMan.getService(Settings.class).getInt("SCREENHEIGHT") / serviceMan.getService(Settings.class).getInt("TILE_SIZE")) + 2);
			// //
			// map.render(cam.getX(), cam.getY());
			// map.render(0, 0, 0, 0, 1024, 768);
		}
		gameObjects.stream().forEach(e -> e.onRender(this, serviceMan));
	}

	@Override
	protected void update(ServiceManager serviceMan) {
		updateAbles.stream().forEach(e -> e.run());
		for (GameObject go : gameObjects) {
			go.onUpdate(this, serviceMan);
		}
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
		if(tag == null) {
			return;
		}
		if (this.taggedGameObjects.get(tag) == null) {
			this.taggedGameObjects.put(tag, new QueueSet<GameObject>(this));
		}
		this.taggedGameObjects.get(tag).add(gameObject);
	}

	public void setMap(TiledMap map) {
		this.map = map;
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
