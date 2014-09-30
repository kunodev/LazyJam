package de.black.core.gameengine.renderer.concrete;

import java.util.ArrayList;
import java.util.List;

import de.black.core.asset.assets.ARenderableObject;
import de.black.core.asset.assets.SpriteSheet;
import de.black.core.asset.manager.AssetManager;
import de.black.core.gameengine.renderer.abstrct.SimpleAbstractAnimationComponent;

public class PNGSpriteRendererComponent extends SimpleAbstractAnimationComponent {

	public static final String COMPONENT = "PNGSprite";

	protected SpriteSheet ip;

	public PNGSpriteRendererComponent() {
		this.renderableObjects = new ArrayList<ArrayList<? extends ARenderableObject>>();
		this.renderableObjects.add(new ArrayList<SpriteSheet>());
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean load(String filePath) {
		if (!filePath.endsWith(".png")) {
			return false;
		}
		ip = new SpriteSheet(filePath);
		List<SpriteSheet> list = (List<SpriteSheet>) this.renderableObjects.get(0);
		list.add(ip);
		return true;
	}

	@Override
	public void onRender() {
		int tileX = (int) ip.getRectangle().getWidth();
		int tileY = (int) ip.getRectangle().getHeight();
		ip.getPicture().draw(getPos().x, getPos().y, getPos().x + tileX, getPos().y + tileY, tileX * xOffset,
				tileY * state, tileX * (xOffset + 1), tileY * (state + 1));
		// ip.getPicture().draw(getPos().x, getPos().y, tileX * xOffset, tileY *
		// state, tileX * (xOffset + 1), tileY * (state +1));
	}

	public void initWithString(String initString) {
		String[] initVal = initString.split("\\+");
		SimpleAbstractAnimationComponent preFab = AssetManager.getInstance().getAsset(initVal[0]);
		if (preFab instanceof PNGSpriteRendererComponent) {
			PNGSpriteRendererComponent preFabTyped = (PNGSpriteRendererComponent) preFab;
			this.overWriteAsset(preFabTyped);
			ip.maxX = Integer.parseInt(initVal[1]);
			ip.maxY = Integer.parseInt(initVal[2]);
		}
	}

	private void overWriteAsset(PNGSpriteRendererComponent preFabTyped) {
		this.renderableObjects = preFabTyped.renderableObjects;
		this.ip = preFabTyped.ip;
	}

	@Override
	public void setState(int state) {
		this.state = state;
		this.xOffset = this.xOffset % ip.maxX;
	}

	@Override
	public void incrementXOffset() {
		this.xOffset = (this.xOffset + 1) % ip.maxX;
	}

}
