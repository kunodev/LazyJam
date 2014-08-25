package de.black.core.gameengine.renderer.concrete;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.newdawn.slick.Color;

import de.black.core.asset.assets.ARenderableObject;
import de.black.core.asset.assets.ASCIIPicture;
import de.black.core.gameengine.renderer.abstrct.SimpleAbstractAnimationComponent;
import de.black.core.tools.text.FontManager;

public class ASCIISpriteAnimation extends SimpleAbstractAnimationComponent {
	
	public static String COMPONENT = "ASCIISprite";

	public Color color;
	
	public ASCIISpriteAnimation() {
		
	}
	
	public ASCIISpriteAnimation(ArrayList<ArrayList<? extends ARenderableObject>> pic) {
		this.renderableObjects = pic;
	}
	
	public ASCIISpriteAnimation(ASCIIPicture[] pic) {
		this(new ArrayList<ArrayList<? extends ARenderableObject>>());
		addFromArray(pic);
	}
	
	public ASCIISpriteAnimation(ASCIIPicture pic) {
		this(new ArrayList<ArrayList<? extends ARenderableObject>>());
		ASCIIPicture[] pics = {pic};
		addFromArray(pics);
	}
	
	@Override
	public void onRender() {
		ASCIIPicture pic = (ASCIIPicture) this.renderableObjects.get(state).get(xOffset);
		String[] pics = pic.getPicture().split("\n");
		for(int i=0; i< pics.length; i++) {
			FontManager.getInstance().drawTextAbsolut((int)getGameObject().getPos().x,
													(int)getGameObject().getPos().y + (i*20),
														pics[i], pic.getDrawingFont().getGameState(), color);
		}
	}


	public void addFromArray(ASCIIPicture[] pic) {
		ArrayList<ASCIIPicture> picAsArray = new ArrayList<ASCIIPicture>();
		for(ASCIIPicture p : pic) {
			picAsArray.add(p);
		}
		this.renderableObjects.add(picAsArray);
	}
	
	@Override
	public boolean load(String path) {
 		File f = new File(path);
 		this.renderableObjects = new ArrayList<ArrayList<? extends ARenderableObject>>();
 		
		if(!f.getPath().endsWith(".txt")) {
			return false; // dont try to load binary files of something else
		}
		
		/* Load String from file */
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
//		    String line = null;
//		    boolean states = false;
//		    while ((line = reader.readLine()) != null) {
//		    	if(line.equals("/beginState"))  {
//		    		states = true;
//		    	}
//		    }
//		    reader.reset();
		    loadWithStates(reader);
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		    return false;
		}
		return true;
	}

	private void loadWithStates(BufferedReader reader) throws IOException {
		String line;
		ArrayList<ASCIIPicture> currentList = new ArrayList<ASCIIPicture>();
	    while ((line = reader.readLine()) != null) {
	    	if(line.equals("=")) {
	    		this.renderableObjects.add(currentList);
	    		currentList = new ArrayList<ASCIIPicture>();
	    		continue;
	    	} else {
	    		currentList.add(loadPic(reader));
	    	}
		}
		this.renderableObjects.add(currentList);		
	}
	
	private ASCIIPicture loadPic(BufferedReader reader) throws IOException {
		String line;
		String picture = "";
		ASCIIPicture result = new ASCIIPicture();
	    while ((line = reader.readLine()) != null) {
	    	if(line.equals("-")) {
	    		result.setPicture(picture);
	    		//TODO: load the font
	    		result.setDrawingFont(FontManager.getInstance().getFontDefinition());
	    		return result;
	    	} else {
	    		picture += line + "\n";
	    	}
	    }
		result.setPicture(picture);
		//TODO: load the font
		result.setDrawingFont(FontManager.getInstance().getFontDefinition());
		return result;
	}
}
