package de.black.core.gamestatemanagement;

import java.util.List;

import org.newdawn.slick.GameContainer;

import de.black.core.input.IInput;

/**
 * Defines a gamestate
 * Interesting is what happens on the input and how often this game state needs updates
 * @author kuro
 *
 */
public interface IGameState {
	
	/**
	 * Gets the inputs, decider of what to do on input
	 * @return
	 */
	public List<IInput> getInput();
	
	/**
	 * Does update things
	 */
	public void onUpdate(GameContainer gc, int deltaInMilliseconds);
	
	/**
	 * Does the rendering 
	 */
	public void onRender();
	
	/**
	 * returns the time to be update -1 if as fast as possible
	 * @return
	 */
	public int getTickTimer();
	/**
	 * Does things that should happen on leavin ghte state, e.g. stopping music
	 */
	public void onLeaveState();
	
	
	

}
