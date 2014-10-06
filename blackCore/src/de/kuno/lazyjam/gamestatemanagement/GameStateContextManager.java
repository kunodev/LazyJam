package de.kuno.lazyjam.gamestatemanagement;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import de.kuno.lazyjam.camera.Cam;
import de.kuno.lazyjam.tools.cdi.manager.ServiceManager;
import de.kuno.lazyjam.tools.text.FontManager;

public class GameStateContextManager {

	private Map<Class<? extends IGameState>, IGameState> gameStates;
	ServiceManager serviceMan;
	IGameState activeState;
	IGameState mainState;

	public GameStateContextManager(GameContainer gc) {
		gameStates = new HashMap<Class<? extends IGameState>, IGameState>();
		serviceMan = new ServiceManager();
		serviceMan.searchForServices();
		serviceMan.registerAsService(gc.getInput());
		serviceMan.registerAsService(gc);
		serviceMan.registerAsService(new FontManager());
		serviceMan.registerAsService(new Cam());
	}

	public void addGameState(Class<? extends IGameState> key, IGameState state) {
		if (gameStates.containsKey(key)) {
			System.err.println("Key " + key + " is duplicated!");
			return;
		}
		this.gameStates.put(key, state);
	}
	
	public void initMainGameState(IGameState gs) {
		this.mainState = gs;
		this.activeState = gs;
	}

	public void setGameState(Class<? extends IGameState> class1) {
		if (this.activeState != null) {
			activeState.onLeaveState();
		}
		this.activeState = gameStates.get(class1);
	}

	public IGameState getGameState() {
		return this.activeState;
	}

	public <T> T getGameStateAs(Class<T> t) {
		return t.cast(this.activeState);
	}

	public <T> T getGameStateAs(Class<T> t, int gamestateId) {
		return t.cast(this.gameStates.get(gamestateId));
	}

	public void render() {
		if(activeState != null) {
			activeState.onRender(serviceMan);
		}
	}

	public void update(int deltaInMilliseconds) {
		if(activeState != null) {
			activeState.onUpdate(serviceMan, deltaInMilliseconds);
		}
	}

	public <T extends IGameState> T getMainGameState(Class<T> clazz) {
		return clazz.cast(mainState);
	}
}
