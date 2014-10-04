package de.kuno.lazyjam.gameengine.basic;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import de.kuno.lazyjam.gamestatemanagement.GameStateManager;
import de.kuno.lazyjam.gamestatemanagement.concrete.GameGameState;
import de.kuno.lazyjam.tools.log.LogManager;

public class GameObject {

	private Vector2f pos;
	private String tag;
	private List<ALogicComponent> logicComps;
	private List<ARenderComponent> renderComps;

	public GameObject(Vector2f pos) {
		this(pos, null);
	}

	public GameObject(Vector2f pos, String tag) {
		this.tag = tag;
		this.setPos(pos);
		initLists();
		GameStateManager.getInstance().getGameStateAs(GameGameState.class, GameGameState.ID).addGameObject(this, tag);
	}

	public GameObject(Vector2f pos, List<ARenderComponent> renderComps, List<ALogicComponent> logicComp) {
		this(pos);
		this.renderComps = renderComps;
		this.logicComps = logicComp;
		initLists();
	}

	private void initLists() {
		if (this.renderComps == null) {
			this.renderComps = new ArrayList<ARenderComponent>();
		} else {
			renderComps.stream().forEach(e -> e.setGameObject(this));
		}
		if (this.logicComps == null) {
			this.logicComps = new ArrayList<ALogicComponent>();
		} else {
			logicComps.stream().forEach(e -> e.setGameObject(this));
		}
	}

	public void onUpdate() {
		logicComps.stream().forEach(e -> e.onUpdate());
	}

	public void onRender() {
		renderComps.stream().forEach(e -> e.onRender());
	}

	public Vector2f getPos() {
		return pos;
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	public void addRenderComp(ARenderComponent comp) {
		comp.setGameObject(this);
		this.renderComps.add(comp);
		comp.onAdded();
	}

	public void addLogicComp(ALogicComponent comp) {
		comp.setGameObject(this);
		this.logicComps.add(comp);
		comp.onAdded();
	}

	public <T> T getComponent(Class<T> clazz) {
		if (this.logicComps.stream().filter(l -> clazz.isInstance(l)).count() > 0) {
			T result = clazz.cast(this.logicComps.stream().filter(l -> clazz.isInstance(l)).findFirst().get());
			return result;
		} else if (this.renderComps.stream().filter(l -> clazz.isInstance(l)).count() > 0) {
			T result = clazz.cast(this.renderComps.stream().filter(l -> clazz.isInstance(l)).findFirst().get());
			return result;
		}
		return null;
	}

	public void addComponent(AGameObjectComponent comp) {
		if (comp instanceof ALogicComponent) {
			this.addLogicComp((ALogicComponent) comp);
		} else if (comp instanceof ARenderComponent) {
			this.addRenderComp((ARenderComponent) comp);
		} else {
			LogManager.getInstance().log("Unknown AGameobject component!");
		}

	}

	public void selfDestruct() {
		GameStateManager.getInstance().getGameGameState().removeGameObject(this, tag);
	}

}
