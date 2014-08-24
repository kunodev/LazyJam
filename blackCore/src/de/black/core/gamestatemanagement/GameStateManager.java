package de.black.core.gamestatemanagement;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GameStateManager {
	
	private Map<Integer, IGameState> gameStates;
	IGameState activeState;

	public GameStateManager() {
		gameStates = new HashMap<Integer, IGameState>();
	}
	
	public void addGameState(int key, IGameState state) {
		if(gameStates.containsKey(key)) {
			System.err.println("Key " + key + " is duplicated!");
			return;
		}
		this.gameStates.put(key, state);
	}
	
	public void setGameState(int key) {
		if(this.activeState != null) {
			activeState.onLeaveState();
		}
		this.activeState = gameStates.get(key);
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
	
	private static GameStateManager instance;
	
	public static GameStateManager getInstance() {
		if(instance == null) {
			instance = new GameStateManager();
		}
		return instance;
	}
	
}
