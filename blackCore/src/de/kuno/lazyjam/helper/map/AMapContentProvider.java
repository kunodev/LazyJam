package de.kuno.lazyjam.helper.map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import de.kuno.lazyjam.constants.Settings;
import de.kuno.lazyjam.gameengine.basic.GameObject;
import de.kuno.lazyjam.gameengine.basic.helper.ComponentRegistry;
import de.kuno.lazyjam.gamestatemanagement.concrete.GameState;
import de.kuno.lazyjam.tools.log.LogManager;

public class AMapContentProvider {

	public TiledMap map;

	public AMapContentProvider() {
		this("map/test.tmx");
	}

	public AMapContentProvider(String mapPath) {
		try {
			map = new TiledMap(mapPath);
		} catch (SlickException e) {
			LogManager.getInstance().log("Could not load map" + mapPath + "\n" + e.getStackTrace().toString());
		}
	}

	public void init(GameState gs) {
		Settings.getInstance().putData("TILE_SIZE", map.getTileHeight());
		for (int gid = 0; gid < map.getObjectGroupCount(); gid++) {
			for (int oid = 0; oid < map.getObjectCount(gid); oid++) {
				Vector2f pos = new Vector2f(map.getObjectX(gid, oid), map.getObjectY(gid, oid));
				String tag = map.getObjectProperty(gid, oid, "tag", "");
				GameObject newGoResult = new GameObject(pos, tag, gs);
				// Simple Component
				for (String possibleComponentName : ComponentRegistry.getInstance().getPossibleComponentNames()) {
					String val = map.getObjectProperty(gid, oid, possibleComponentName, null);
					if (val != null) {
						ComponentFactory.attachComponent(newGoResult, possibleComponentName, val);
					}
				}
			}
		}
	}

}
