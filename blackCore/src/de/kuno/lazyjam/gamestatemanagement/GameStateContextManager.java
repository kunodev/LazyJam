package de.kuno.lazyjam.gamestatemanagement;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GameStateContextManager {

	private Map<Class<? extends IGameState>, IGameState> gameStates;
	IGameState activeState;
	IGameState mainState;

	public GameStateContextManager() {
		gameStates = new HashMap<Class<? extends IGameState>, IGameState>();
	}

	public void addGameState(Class<? extends IGameState> key, IGameState state) {
		if (gameStates.containsKey(key)) {
			System.err.println("Key " + key + " is duplicated!");
			return;
		}
		this.gameStates.put(key, state);
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

	public void render(GameContainer gc, Graphics g) {
		activeState.onRender();
	}

	public void update(GameContainer gc, int deltaInMilliseconds) {
		activeState.onUpdate(gc, deltaInMilliseconds);
	}

	private static GameStateContextManager instance;

	public static GameStateContextManager getInstance() {
		if (instance == null) {
			instance = new GameStateContextManager();
		}
		return instance;
	}

	public <T extends IGameState> T getMainGameState(Class<T> clazz) {
		return clazz.cast(mainState);
	}
}
