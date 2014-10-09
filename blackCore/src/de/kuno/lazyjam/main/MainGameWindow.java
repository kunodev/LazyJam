package de.kuno.lazyjam.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import de.kuno.lazyjam.camera.Cam;
import de.kuno.lazyjam.constants.Settings;
import de.kuno.lazyjam.gamestatemanagement.GameStateContextManager;
import de.kuno.lazyjam.tools.cdi.manager.ServiceManager;
import de.kuno.lazyjam.tools.reflect.ReflectionUtil;
import de.kuno.lazyjam.tools.text.FontManager;

/**
 * Main class hahahaha
 * 
 * @author Kuno
 * 
 */
public class MainGameWindow extends BasicGame {

	protected GameStateContextManager gameStateMan;
	protected ServiceManager serviceMan;
	
	public MainGameWindow(String gamename) {
		super(gamename);
	}

	/**
	 * Do do a different game add your gamestates and set the initial one
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {
		gameStateMan = new GameStateContextManager(gc, serviceMan);
	}

	@Override
	public void update(GameContainer gc, int deltaInMilliseconds) throws SlickException {
		gameStateMan.update(deltaInMilliseconds);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// GameState.getInstance().renderObjects();
		gameStateMan.render();
		// VNManager.getInstance().renderVN();
	}

	public static void createMainGameWindow(MainGameWindow mainClass) {
		try {
			ReflectionUtil.init();
			mainClass.serviceMan = new ServiceManager();
			mainClass.serviceMan.searchForServices();
			AppGameContainer appgc;
			appgc = new AppGameContainer( mainClass );
			appgc.setDisplayMode(mainClass.serviceMan.getService(Settings.class).getInt(Settings.SCREEN_WIDTH),
					mainClass.serviceMan.getService(Settings.class).getInt(Settings.SCREEN_HEIGHT), false);
			appgc.start();
			
			
		} catch (SlickException ex) {
			Logger.getLogger(MainGameWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		createMainGameWindow(new MainGameWindow("LazyJam Lib / Black Core"));
	}
}
