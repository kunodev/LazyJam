package de.black.core.gamestatemanagement.concrete;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import de.black.core.gamestatemanagement.AGameState;
import de.black.core.gamestatemanagement.GameStateManager;
import de.black.core.input.InputConfiguration;
import de.black.core.input.concrete.MenuInput;
import de.black.core.menu.GameStateChanger;
import de.black.core.menu.SimpleGoDeepMenuObject;
import de.black.core.menu.SimpleMenuObject;
import de.black.core.menu.listbuttons.AChooseFromList;
import de.black.core.tools.dua.trees.TreeNode;
import de.black.core.tools.text.FontManager;

public class MenuGameState extends AGameState {

	private TreeNode<? extends SimpleMenuObject> menutreeFull;
	/**
	 * Should always be current.depth+1 in the tree
	 */
	private int selectedOption;

	public static final int ID = 1;
	public static final int CONSTANT_OFFSET_X = 50;
	public static final int CONSTANT_OFFSET_Y = 50;

	public MenuGameState(GameContainer gc) {
		super(new MenuInput().init(gc.getInput(), new InputConfiguration()));
		selectedOption = 0;
	}

	@Override
	public void onRender() {
		// draw title
		FontManager.getInstance().drawTextRelative(CONSTANT_OFFSET_X, CONSTANT_OFFSET_Y,
				menutreeFull.getObject().getText(), Color.white);
		// draw options
		int offsetMultiplicator = 2;
		for (int i = 0; i < menutreeFull.getChildren().size(); i++) {
			TreeNode<? extends SimpleMenuObject> option = menutreeFull.getChildren().get(i);
			if (i == selectedOption) {
				FontManager.getInstance().drawTextRelative(CONSTANT_OFFSET_X, CONSTANT_OFFSET_Y * offsetMultiplicator,
						option.getObject().getText(), Color.green);
			} else {
				FontManager.getInstance().drawTextRelative(CONSTANT_OFFSET_X, CONSTANT_OFFSET_Y * offsetMultiplicator,
						option.getObject().getText(), Color.white);
			}
			offsetMultiplicator++;
		}
	}

	@Override
	protected void update(GameContainer gc) {
		// Nothing happens from within a menu locally, therefore => nothing
		// happens here
	}

	public void clickOkay() {
		TreeNode<? extends SimpleMenuObject> option = menutreeFull.getChildren().get(selectedOption);
		switch (option.getObject().getActionID()) {
		case SimpleGoDeepMenuObject.ID:
			if (!option.isLeaf()) {
				menutreeFull = option;
			}
			break;
		case GameStateChanger.ID:
			GameStateChanger changer = (GameStateChanger) (option.getObject());
			if (changer.hasVN()) {
				this.triggerVN(changer.getVNChange(), changer.getGameStateID());
			} else {
				GameStateManager.getInstance().setGameState(changer.getGameStateID());
			}
			break;
		case AChooseFromList.ID:
			break;
		}
	}

	public void pressSide(boolean left) {
		TreeNode<? extends SimpleMenuObject> option = menutreeFull.getChildren().get(selectedOption);
		switch (option.getObject().getActionID()) {
		case SimpleGoDeepMenuObject.ID:
			break;
		case GameStateChanger.ID:
			break;
		case AChooseFromList.ID:
			AChooseFromList listOption = option.getObjectAs(AChooseFromList.class);
			if (left) {
				listOption.left();
			} else {
				listOption.right();
			}
			break;
		}
	}

	public void clickCancel() {
		if (!menutreeFull.isRoot()) {
			menutreeFull = menutreeFull.getParent();
		}
	}

	public void down() {
		selectedOption++;
		selectedOption %= menutreeFull.getChildren().size();
	}

	public void up() {
		selectedOption--;
		if (selectedOption < 0) {
			selectedOption = menutreeFull.getChildren().size() - 1;
		}
	}

	@Override
	public int getGameStateID() {
		return ID;
	}

}
