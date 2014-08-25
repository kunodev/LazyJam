package de.black.core.content.game;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import de.black.core.tools.log.LogManager;

public class AMapContentProvider {
	
	TiledMap map;
	
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

}
