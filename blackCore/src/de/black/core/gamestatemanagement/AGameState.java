package de.black.core.gamestatemanagement;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.openal.Audio;

import de.black.core.constants.Constants;
import de.black.core.gamestatemanagement.concrete.VNGameState;
import de.black.core.input.IInput;

public abstract class AGameState implements IGameState{

	protected final IInput inputHandler;
	protected int tick = 0;
	protected final int TICK_TIME;
	public Audio bgm;
	
	protected AGameState(IInput inputHandler) {
		this.inputHandler = inputHandler;
		this.TICK_TIME = Constants.DEFAULT_TICK_TIME;
	}
	protected AGameState(IInput inputHandler, int TICK_TIME) {
		this.inputHandler = inputHandler;
		this.TICK_TIME = TICK_TIME;
	}
	
	protected abstract void update(GameContainer gc);
	
	@Override
	public void onUpdate(GameContainer gc, int deltaInMilliseconds) {
		startBGM();
		tick += deltaInMilliseconds;
		if(tick >= TICK_TIME) {
			getInput().update();
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
	public IInput getInput() {
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

}
