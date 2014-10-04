package de.kuno.lazyjam.tools.vnengine;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.kuno.lazyjam.camera.Cam;
import de.kuno.lazyjam.constants.Settings;
import de.kuno.lazyjam.tools.text.FontManager;

/**
 * Takes care of rendering, rendering should be encapsulated in this class
 * 
 * @author kuro
 *
 */
public class SimpleVNSequence {
	/**
	 * Frames for this sequence
	 */
	private List<VNFrame> frames;
	/**
	 * How much the rectangle is set to the bottom
	 */
	private int yoffset;
	/**
	 * How Big the character Picture is
	 */
	private int xwidth;
	/**
	 * which text and pic to show
	 */
	private int currentPosition;

	/**
	 * TextBoxAsset
	 */
	private Animation textBoxAsset;

	public SimpleVNSequence() {
		this.xwidth = 128;
		this.yoffset = Settings.getInstance().getInt(Settings.SCREEN_HEIGHT) - 128;
		this.frames = new ArrayList<VNFrame>();
		this.textBoxAsset = new Animation();
		try {
			this.textBoxAsset.addFrame(new Image("assets/missing.png"), Integer.MAX_VALUE);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void addFrame(VNFrame frame) {
		this.frames.add(frame);
	}

	public void render(Cam cam) {
		VNFrame currentFrame = frames.get(currentPosition);
		currentFrame.character.draw(cam.getX(), cam.getY() + yoffset, xwidth, xwidth);
		textBoxAsset.draw(cam.getX() + xwidth, cam.getY() + yoffset,
				Settings.getInstance().getInt(Settings.SCREEN_WIDTH) - xwidth, xwidth);
		FontManager.getInstance().drawTextRelative(xwidth, yoffset, currentFrame.text);
	}

	public void next() {
		currentPosition++;
	}

	public void setIndex(int i) {
		this.currentPosition = i;

	}

	public boolean isDone() {
		return currentPosition >= frames.size();
	}

}