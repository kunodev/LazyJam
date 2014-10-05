package de.kuno.lazyjam.gamestatemanagement;

import org.newdawn.slick.GameContainer;

import de.kuno.lazyjam.tools.cdi.manager.ServiceManager;

/**
 * Defines a gamestate Interesting is what happens on the input and how often
 * this game state needs updates
 * 
 * @author kuro
 *
 */
public interface IGameState {

	/**
	 * Does update things
	 */
	public void onUpdate(ServiceManager serviceMan, int deltaInMilliseconds);

	/**
	 * Does the rendering
	 */
	public void onRender(ServiceManager serviceMan);

	/**
	 * returns the time to be update -1 if as fast as possible
	 * 
	 * @return
	 */
	public int getTickTimer();

	/**
	 * Does things that should happen on leavin ghte state, e.g. stopping music
	 */
	public void onLeaveState();

}
