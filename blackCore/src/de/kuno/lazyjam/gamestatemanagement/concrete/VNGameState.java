package de.kuno.lazyjam.gamestatemanagement.concrete;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;

import de.kuno.lazyjam.camera.Cam;
import de.kuno.lazyjam.gamestatemanagement.AGameState;
import de.kuno.lazyjam.gamestatemanagement.GameStateManager;
import de.kuno.lazyjam.input.InputConfiguration;
import de.kuno.lazyjam.input.concrete.VNInput;
import de.kuno.lazyjam.main.MainGameWindow;
import de.kuno.lazyjam.tools.vnengine.SimpleVNSequence;

public class VNGameState extends AGameState {

	private SimpleVNSequence currentSequence;
	private Map<Integer, SimpleVNSequence> sequenceConfig;
	private String currentAction;
	private int returningGameState;
	public Cam camera;

	public static final int ID = 2;

	public VNGameState(GameContainer gc) {
		super(new VNInput().init(gc.getInput(), new InputConfiguration()));
		returningGameState = MenuGameState.ID; // In doubt return to menu
	}

	public void init(HashMap<Integer, SimpleVNSequence> config) {
		camera = MainGameWindow.getInstance().getCam();
		this.sequenceConfig = config;
	}

	/**
	 * Use only for test purpose
	 */
	public void init() {
		camera = MainGameWindow.getInstance().getCam();
		sequenceConfig = new HashMap<Integer, SimpleVNSequence>();
	}

	public void triggerAction(String action, int returningState) {
		if (sequenceConfig.containsKey(action.hashCode())) {
			currentSequence = sequenceConfig.get(action.hashCode());
			currentSequence.setIndex(0);
			currentAction = action;
			if (returningState == ID) {
				System.err.println("Trying to trigger VN in endless loop! dont do >.>!");
				returningGameState = MenuGameState.ID;
			} else {
				returningGameState = returningState;
			}
			GameStateManager.getInstance().setGameState(ID);
		} else {
			System.err.println("Could not find VNSequenceKey: " + action);
		}
	}

	/**
	 * if there is something to render, render it, else nothing
	 */
	@Override
	public void onRender() {
		if (currentSequence != null && !currentSequence.isDone()) {
			currentSequence.render(camera);
		}
	}

	public void triggerNext() {
		currentSequence.next();
		if (currentSequence.isDone()) {
			notifyObservers();
		}
	}

	/**
	 * Handles backtriggers, e.g. returning to a menu after a text sequence
	 */
	private void notifyObservers() {
		GameStateManager.getInstance().setGameState(returningGameState);

	}

	@Override
	protected void update(GameContainer gc) {

	}

	@Override
	public int getGameStateID() {
		return ID;
	}

}
