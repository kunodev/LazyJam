package de.kuno.lazyjam.gameengine.basic;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import de.kuno.lazyjam.gamestatemanagement.GameStateContextManager;
import de.kuno.lazyjam.gamestatemanagement.concrete.GameState;
import de.kuno.lazyjam.tools.cdi.injector.InjectorCaller;
import de.kuno.lazyjam.tools.cdi.manager.ServiceManager;
import de.kuno.lazyjam.tools.log.LogManager;

public class GameObject {

	private Vector2f pos;
	private String tag;
	private List<Object> components;

	public GameObject(Vector2f pos, GameState gs) {
		this(pos, null, gs);
	}

	public GameObject(Vector2f pos, String tag, GameState gs) {
		this.tag = tag;
		this.setPos(pos);
		components = new ArrayList<Object>();
		gs.addGameObject(this, tag);
	}

	public void onUpdate(GameState gs, ServiceManager serviceMan) {
		InjectorCaller.callUpdate(this, gs, serviceMan);
	}

	public void onRender(GameState gs, ServiceManager serviceMan) {
		InjectorCaller.callRender(this, gs, serviceMan);
	}

	public Vector2f getPos() {
		return pos;
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
	}
	
	public void addComponent(Object comp) {
		this.components.add(comp);
	}

	public <T> T getComponent(Class<T> clazz) {
		if (this.components.stream().filter(l -> clazz.isInstance(l)).count() > 0) {
			T result = clazz.cast(this.components.stream().filter(l -> clazz.isInstance(l)).findFirst().get());
			return result;
		} 
		return null;
	}

	public void selfDestruct(GameState gs) {
		gs.removeGameObject(this, tag);
	}
	
	public List<Object> getComponents() {
		return components;
	}
}
