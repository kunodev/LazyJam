package de.black.core.menu;

public class GameStateChanger extends SimpleMenuObject{

	public static final int ID = 2;
	private String vnSequenceToTrigger;
	private int gameStateId;
	
	public GameStateChanger(String text, String vnKey, int gameStateID) {
		super(text);
		this.vnSequenceToTrigger = vnKey;
		this.gameStateId = gameStateID;
	}
	
	@Override
	public int getActionID() {
		return ID;
	}
	
	public String getVNChange() {
		return vnSequenceToTrigger;
	}
	
	public int getGameStateID() {
		return gameStateId;
	}
	public boolean hasVN() {
		return vnSequenceToTrigger != null;
	}
	
}
