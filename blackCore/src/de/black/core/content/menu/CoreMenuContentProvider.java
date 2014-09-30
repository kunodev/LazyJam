package de.black.core.content.menu;

import de.black.core.gamestatemanagement.concrete.GameGameState;
import de.black.core.gamestatemanagement.concrete.MenuGameState;
import de.black.core.menu.GameStateChanger;
import de.black.core.menu.SimpleGoDeepMenuObject;
import de.black.core.menu.SimpleMenuObject;
import de.black.core.menu.listbuttons.FontChoose;
import de.black.core.tools.dua.trees.TreeNode;

public class CoreMenuContentProvider {

	public static final String FIRST_MENU_OPTION_STRING = "Black Core Project: Main Menu";

	public static TreeNode<? extends SimpleMenuObject> getMenu() {
		SimpleMenuObject root = new SimpleGoDeepMenuObject(FIRST_MENU_OPTION_STRING);
		SimpleMenuObject testVN = new GameStateChanger("VNTest", "test0", MenuGameState.ID);
		SimpleMenuObject testFont = new FontChoose();
		SimpleMenuObject submenu = new SimpleGoDeepMenuObject("Submenu!");
		SimpleMenuObject subsubmenu = new SimpleGoDeepMenuObject("Some option");
		SimpleMenuObject subsubmenu2 = new SimpleGoDeepMenuObject("another option");
		SimpleMenuObject startGame = new GameStateChanger("Start demo game!", "test1", GameGameState.ID);
		TreeNode<SimpleMenuObject> rootTree = new TreeNode<SimpleMenuObject>(root);
		TreeNode<SimpleMenuObject> testVNTree = new TreeNode<SimpleMenuObject>(testVN);
		TreeNode<SimpleMenuObject> startGameTree = new TreeNode<SimpleMenuObject>(startGame);
		TreeNode<SimpleMenuObject> submenuTree = new TreeNode<SimpleMenuObject>(submenu);
		TreeNode<SimpleMenuObject> subsubmenuTree = new TreeNode<SimpleMenuObject>(subsubmenu);
		TreeNode<SimpleMenuObject> subsubmenu2Tree = new TreeNode<SimpleMenuObject>(subsubmenu2);
		TreeNode<SimpleMenuObject> fontTestTree = new TreeNode<SimpleMenuObject>(testFont);
		rootTree.addChild(testVNTree);
		rootTree.addChild(submenuTree);
		rootTree.addChild(fontTestTree);
		rootTree.addChild(startGameTree);
		submenuTree.addChild(subsubmenuTree);
		submenuTree.addChild(subsubmenu2Tree);
		return rootTree;
	}

}
