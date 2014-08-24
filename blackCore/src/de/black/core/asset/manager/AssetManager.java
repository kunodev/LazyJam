package de.black.core.asset.manager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Sound;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;

import de.black.core.asset.assets.ARenderableObject;
import de.black.core.asset.assets.ASCIIPicture;
import de.black.core.asset.assets.ImagePicture;
import de.black.core.tools.log.LogManager;

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
	
	private Map<String, ARenderableObject> assetBank;
	private Map<String, Audio> soundBank;
	
	public static AssetManager getInstance() {
		if (instance == null)
			instance = new AssetManager();
		return instance;
	}
	
	public AssetManager() {
		this.assetBank = new HashMap<String, ARenderableObject>();
		this.soundBank = new HashMap<String, Audio>();
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
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if(isSound(listOfFiles[i])) {
				loadSound(listOfFiles[i]);
			} else {
				loadAsset(listOfFiles[i]);
			}
		}
	}
	
	private void loadSound(File file) {
		try {
			String name = getNameOfAsset(file);
			soundBank.put(name, SoundStore.get().getOgg(file.getPath()));
		} catch (IOException e) {
			LogManager.getInstance().log(e.getStackTrace().toString());
		}
		
	}

	/*
	 * Try different loaders to load an assetfile and put it in the assetBank
	 */
	@SuppressWarnings("unchecked")
	private void loadAsset(File file) {
		ARenderableObject result;
		for (Class<ARenderableObject> c : loaders) {
			try {
				result = (ARenderableObject)c.newInstance();
				/* if loading succeeds exit loading */
				if (result.load(file.getPath())) {
					String name = getNameOfAsset(file);
					
					this.addAsset(name, result);	
				}
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private String getNameOfAsset(File file) {
		String name = file.getName();
		int lastIndex = name.lastIndexOf(".");
		
		if (lastIndex > 0) {
			name = name.substring(0, lastIndex);
		}
		return name;
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
	
	private boolean isSound(File f) {
		return f.getAbsolutePath().endsWith(".mp3");
	}
	
}
