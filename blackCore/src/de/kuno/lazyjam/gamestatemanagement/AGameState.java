package de.kuno.lazyjam.gamestatemanagement;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.openal.Audio;

import de.kuno.lazyjam.constants.Constants;
import de.kuno.lazyjam.tools.cdi.manager.ServiceManager;

public abstract class AGameState implements IGameState {

	protected final List<Runnable> updateAbles;
	protected int tick = 0;
	protected final int TICK_TIME;
	public Audio bgm;

	protected AGameState() {
		this.TICK_TIME = Constants.DEFAULT_TICK_TIME;
		this.updateAbles = new ArrayList<Runnable>();
	}

	protected AGameState(int TICK_TIME) {
		this.TICK_TIME = TICK_TIME;
		this.updateAbles = new ArrayList<Runnable>();
	}

	protected abstract void update(ServiceManager serviceMan);

	@Override
	public void onUpdate(ServiceManager serviceMan, int deltaInMilliseconds) {
		startBGM();
		tick += deltaInMilliseconds;
		if (tick >= TICK_TIME) {
			update(serviceMan);
			tick = 0;
		}
	}

	private void startBGM() {
		if (bgm != null) {
			if (!bgm.isPlaying()) {
				bgm.playAsMusic(1f, 1f, true);
			}
		}
	}

	@Override
	public void onLeaveState() {
		if (bgm != null) {
			bgm.stop();
		}
	}

	@Override
	public int getTickTimer() {
		return Constants.DEFAULT_TICK_TIME;
	}
}
