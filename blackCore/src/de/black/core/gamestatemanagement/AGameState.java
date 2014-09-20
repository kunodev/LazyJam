package de.black.core.gamestatemanagement;

import java.util.List;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.openal.Audio;

import de.black.core.constants.Constants;
import de.black.core.gamestatemanagement.concrete.VNGameState;
import de.black.core.input.IInput;

public abstract class AGameState implements IGameState{

	protected final List<IInput> inputHandler;
	protected final List<Runnable> updateAbles; 
	protected int tick = 0;
	protected final int TICK_TIME;
	public Audio bgm;
	
	protected AGameState(IInput inputHandler) {
		this.inputHandler = new ArrayList<IInput>();
		this.inputHandler.add(inputHandler);
		this.TICK_TIME = Constants.DEFAULT_TICK_TIME;
		this.updateAbles = new ArrayList<Runnable>();
	}
	
	protected AGameState(IInput inputHandler, int TICK_TIME) {
		this.inputHandler = new ArrayList<IInput>();
		this.inputHandler.add(inputHandler);
		this.TICK_TIME = TICK_TIME;
		this.updateAbles = new ArrayList<Runnable>();
	}
	
	protected abstract void update(GameContainer gc);
	
	@Override
	public void onUpdate(GameContainer gc, int deltaInMilliseconds) {
		startBGM();
		tick += deltaInMilliseconds;
		if(tick >= TICK_TIME) {
			getInput().stream().forEach(e -> e.update());
			update(gc);
			tick = 0;
		}
	}
	private void startBGM() {
		if(bgm != null) {
			if(!bgm.isPlaying()) {
				bgm.playAsMusic(1f, 1f, true);
			}
		}
	}
	
	@Override
	public void onLeaveState() {
		if(bgm != null) {
			bgm.stop();
		}
	}
	
	@Override
	public List<IInput> getInput() {
		return inputHandler;
	}

	@Override
	public int getTickTimer() {
		return Constants.DEFAULT_TICK_TIME;
	}
	
	public abstract int getGameStateID();
	
	public void triggerVN(String key, int gameStateId) {
		GameStateManager.getInstance().setGameState(VNGameState.ID);
		GameStateManager.getInstance().getGameStateAs(VNGameState.class).triggerAction(key, gameStateId);
	}
	
	public void addInputHandler(IInput inputHandler) {
		this.inputHandler.add(inputHandler);
		
	}

}
