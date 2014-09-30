package de.black.core.content.vn;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.black.core.tools.vnengine.SimpleVNSequence;
import de.black.core.tools.vnengine.VNFrame;

public class CoreVNContentProvider {
	public static List<SimpleVNSequence> getTestSequences() {
		ArrayList<SimpleVNSequence> testSequences = new ArrayList<SimpleVNSequence>();

		SimpleVNSequence test1 = new SimpleVNSequence();

		VNFrame frame1 = new VNFrame();
		VNFrame frame2 = new VNFrame();
		frame1.text = "So it begins...";
		frame2.text = "The epic journey!";
		frame1.character = new Animation();
		frame2.character = new Animation();
		try {
			frame1.character.addFrame(new Image("assets/missing.png"), Integer.MAX_VALUE);
			frame2.character.addFrame(new Image("assets/missing.png"), Integer.MAX_VALUE);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test1.addFrame(frame1);
		test1.addFrame(frame2);

		testSequences.add(test1);

		SimpleVNSequence game1 = new SimpleVNSequence();

		VNFrame frame3 = new VNFrame();
		frame3.character = new Animation();
		try {
			frame3.character.addFrame(new Image("assets/missing.png"), Integer.MAX_VALUE);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		frame3.text = "TUNA KITTEN! THE GAME";
		game1.addFrame(frame3);
		testSequences.add(game1);

		return testSequences;
	}
}
