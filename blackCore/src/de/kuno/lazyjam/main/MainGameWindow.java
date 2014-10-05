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
import de.kuno.lazyjam.tools.text.FontManager;

/**
 * Main class hahahaha
 * 
 * @author Kuno
 * 
 */
public class MainGameWindow extends BasicGame {

	private static MainGameWindow instance;
	public Cam cam;

	public static MainGameWindow getInstance() {
		if (instance == null)
			instance = new MainGameWindow("Black Core Project");
		return instance;

	}

	public MainGameWindow(String gamename) {
		super(gamename);
	}

	/**
	 * Do do a different game add your gamestates and set the initial one
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {
		ServiceManager services = new ServiceManager();
		services.searchForServices();
		services.registerAsService(gc.getInput());
	}

	@Override
	public void update(GameContainer gc, int deltaInMilliseconds) throws SlickException {
		GameStateContextManager.getInstance().update(gc, deltaInMilliseconds);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {

		// GameState.getInstance().renderObjects();
		GameStateContextManager.getInstance().render(gc, g);
		// VNManager.getInstance().renderVN();
	}

	public static void createMainGameWindow() {
		try {
			Settings.getInstance().setFilePath("config/settings.json");
			Settings.getInstance().load();

			AppGameContainer appgc;
			appgc = new AppGameContainer(MainGameWindow.getInstance());
			appgc.setDisplayMode(Settings.getInstance().getInt(Settings.SCREEN_WIDTH),
					Settings.getInstance().getInt(Settings.SCREEN_HEIGHT), false);
			appgc.start();
			
			
		} catch (SlickException ex) {
			Logger.getLogger(MainGameWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		createMainGameWindow();
	}
}
