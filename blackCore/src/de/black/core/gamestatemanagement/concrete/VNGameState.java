package de.black.core.gamestatemanagement.concrete;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;

import de.black.core.camera.Cam;
import de.black.core.content.vn.CoreVNContentProvider;
import de.black.core.gamestatemanagement.AGameState;
import de.black.core.gamestatemanagement.GameStateManager;
import de.black.core.input.InputConfiguration;
import de.black.core.input.concrete.VNInput;
import de.black.core.main.MainGameWindow;
import de.black.core.tools.vnengine.SimpleVNSequence;

public class VNGameState extends AGameState{
	
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
		for(int i = 0; i < CoreVNContentProvider.getTestSequences().size(); i++) {
			sequenceConfig.put(("test" + i).hashCode(), CoreVNContentProvider.getTestSequences().get(i));
		}
	}
	
	public void triggerAction(String action, int returningState) {
		if(sequenceConfig.containsKey(action.hashCode())) {
			currentSequence = sequenceConfig.get(action.hashCode());
			currentSequence.setIndex(0);
			currentAction = action;
			if(returningState == ID) {
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
		if(currentSequence != null && !currentSequence.isDone() ) {
			currentSequence.render(camera);
		}
	}

	public void triggerNext() {
		currentSequence.next();
		if(currentSequence.isDone()) {
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
