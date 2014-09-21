package de.black.core.asset.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Sound;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.DeferredSound;
import org.newdawn.slick.openal.SoundStore;

import de.black.core.asset.assets.ARenderableObject;
import de.black.core.asset.assets.ASCIIPicture;
import de.black.core.asset.assets.ImagePicture;
import de.black.core.gameengine.renderer.abstrct.SimpleAbstractAnimationComponent;
import de.black.core.gameengine.renderer.concrete.ASCIISpriteAnimation;
import de.black.core.gameengine.renderer.concrete.PNGSpriteRendererComponent;
import de.black.core.tools.log.LogManager;

public class AssetManager {
	
	private final String defaultFolder = "assets/";
	
	/* Different loaders for files. Every single one is tried from left to right. ASCIIPicture should be last,
	 * since it is loading plain text and will not fail on Image files.
	 */	
	@SuppressWarnings("rawtypes")
	private final Class[] loaders = new Class[] {
		ASCIISpriteAnimation.class, PNGSpriteRendererComponent.class
	};

	
	private static AssetManager instance;
	
	private Map<String, SimpleAbstractAnimationComponent> assetBank;
	private Map<String, Audio> soundBank;
	
	public static AssetManager getInstance() {
		if (instance == null)
			instance = new AssetManager();
		return instance;
	}
	
	public AssetManager() {
		this.assetBank = new HashMap<String, SimpleAbstractAnimationComponent>();
		this.soundBank = new HashMap<String, Audio>();
		SoundStore.get().init();
		init();
	}

	public void init() {
		File folder = new File(defaultFolder);
		init(folder);
	}
	/*
	 * Goes through every file in 'folderPath' and tries to load it. If it could be loaded
	 * it will be added to the database.
	 */
	public void init(File folder)
	{
		for(File f : folder.listFiles()) {
			if(f.isDirectory()) {
				init(f);
			} else if(isSound(f)) {
				loadSound(f);
			} else {
				loadAsset(f);
			}
		}
	}
	
	private void loadSound(File file) {
		try {
			String name = getNameOfAsset(file);
			InputStream ioStream = new FileInputStream(file);
			soundBank.put(name, SoundStore.get().getOgg(ioStream));
		} catch (IOException e) {
			LogManager.getInstance().log(e.getStackTrace().toString());
		}
	}

	/*
	 * Try different loaders to load an assetfile and put it in the assetBank
	 */
	@SuppressWarnings("unchecked")
	private void loadAsset(File file) {
		SimpleAbstractAnimationComponent result;
		for (Class<SimpleAbstractAnimationComponent> c : loaders) {
			try {
				result = (SimpleAbstractAnimationComponent)c.newInstance();
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
	
	public void addAsset(String key, SimpleAbstractAnimationComponent value) {
		
		if (!assetExits(key)) {
			assetBank.put(key, value);
		}
		
	}
	
	public SimpleAbstractAnimationComponent getAsset(String key) {
		return assetBank.get(key);
	}
	
	private boolean isSound(File f) {
		return f.getAbsolutePath().endsWith(".ogg");
	}
	
	public Audio getSound(String name) {
		return soundBank.get(name);
	}
	
}
