package de.black.core.content.game;

import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;

import de.black.core.asset.assets.ASCIIPicture;
import de.black.core.constants.Constants;
import de.black.core.constants.Settings;
import de.black.core.constants.Tags;
import de.black.core.gameengine.StupidAnimationComponent;
import de.black.core.gameengine.basic.GameObject;
import de.black.core.gameengine.physics.BodyAdaptorComponent;
import de.black.core.gameengine.renderer.ASCIISpriteAnimation;
import de.black.core.tools.physics.BodyBuilder;

public class CoreGameContentProvider {

	
	public static void initGameObjects(World physicsWorld) {
		GameObject player = new GameObject(new Vector2f(256f,256f), Tags.PLAYER);
		String cat = 
			    "  _._     _,-'\"\"`-._	    \n" +
			    "  (,-.`._,'(       |\\`-/| \n" +
			    "      `-.-' \\ )-`( , o o) \n" +
			    "  -bf-      `-    \\`_`\\\"'- \n"		
		;
		String catRun1 = cat;
		String catRun2 = 
			    "  _._     _,-'\"\"`-._	   \n" +
			    "  (,-.`._,'(       |\\`-/| \n" +
			    "      `-.-' \\ )-`( , o o) \n" +
			    "  -bf-      `-    /`_`/\"'- \n"		
		;
		ASCIIPicture idlePic = new ASCIIPicture(cat);
		ASCIIPicture run1 = new ASCIIPicture(catRun1);
		ASCIIPicture run2 = new ASCIIPicture(catRun2);
		ASCIIPicture[] idle = {idlePic};
		ASCIIPicture[] run = {run1,run2};
		ASCIISpriteAnimation ascii = new ASCIISpriteAnimation(idle);
		ascii.addFromArray(run);
		ascii.color = Color.white;
		player.addRenderComp(ascii);
		player.addLogicComp(new StupidAnimationComponent());
		BodyBuilder catBuilder = BodyBuilder.getBodyBuilder(physicsWorld).withAwake(true).withActive(true).withDefaultPolygonShape().withType(BodyType.DYNAMIC);
		player.addLogicComp(new BodyAdaptorComponent(catBuilder));
        GameObject floor = new GameObject(new Vector2f(0f,Settings.getInstance().getInt(Settings.SCREEN_HEIGHT)));
		BodyBuilder floorBuilder = BodyBuilder.getBodyBuilder(physicsWorld).
				withDensity(Float.MAX_VALUE).withRectanglePolygonShape(Settings.getInstance().getInt(Settings.SCREEN_WIDTH), 5f);
		BodyAdaptorComponent bodyFloor = new BodyAdaptorComponent(floorBuilder);
		floor.addLogicComp(bodyFloor);
	}
}
