package de.black.core.asset.manager;

import java.io.File;
import java.util.HashMap;

import de.black.core.asset.assets.ARenderableObject;
import de.black.core.asset.assets.ASCIIPicture;
import de.black.core.asset.assets.ImagePicture;

public class AssetManager {
	
	private final String defaultFolder = "assets/";
	
	/* Different loaders for files. Every single one is tried from left to right. ASCIIPicture should be last,
	 * since it is loading plain text and will not fail on Image files.
	 */	
	@SuppressWarnings("rawtypes")
	private final Class[] loaders = new Class[] {
		ImagePicture.class, ASCIIPicture.class, 
	};

	
	private static AssetManager instance;
	
	private HashMap<String, ARenderableObject> assetBank;
	
	public static AssetManager getInstance() {
		if (instance == null)
			instance = new AssetManager();
		return instance;
	}
	
	public AssetManager() {
		this.assetBank = new HashMap<String, ARenderableObject>();
	}

	public void init() {
		init(defaultFolder);
	}
	/*
	 * Goes through every file in 'folderPath' and tries to load it. If it could be loaded
	 * it will be added to the database.
	 */
	public void init(String folderPath)
	{
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		ARenderableObject asset;
		
		for (int i = 0; i < listOfFiles.length; i++) {
			asset = loadAsset(listOfFiles[i]);
			
			if (asset != null) {
				String name = listOfFiles[i].getName();
				int lastIndex = name.lastIndexOf(".");
				
				if (lastIndex > 0) {
					name = name.substring(0, lastIndex);
				}
				
				this.addAsset(name, asset);
				
			}
		}
	}
	
	/*
	 * Try different loaders to load an assetfile. If it is sucessfull return the loaded asset.
	 * If all loaders fail, null is returned.
	 */
	@SuppressWarnings("unchecked")
	private ARenderableObject loadAsset(File file) {
		ARenderableObject result;
		for (Class<ARenderableObject> c : loaders) {
			try {
				result = (ARenderableObject)c.newInstance();
				/* if loading succeeds exit loading */
				if (result.load(file.getPath())) {
					return result;
				}
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean assetExits(String key) {
		return assetBank.containsKey(key);
	}
	
	public void addAsset(String key, ARenderableObject value) {
		
		if (!assetExits(key)) {
			assetBank.put(key, value);
		}
		
	}
	
	public ARenderableObject getAsset(String key) {
		return assetBank.get(key);
	}
	
}
