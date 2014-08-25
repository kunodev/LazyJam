package de.black.core.content.game;

import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;

import de.black.core.asset.assets.ASCIIPicture;
import de.black.core.asset.manager.AssetManager;
import de.black.core.constants.Constants;
import de.black.core.constants.Settings;
import de.black.core.constants.Tags;
import de.black.core.gameengine.StupidAnimationComponent;
import de.black.core.gameengine.basic.GameObject;
import de.black.core.gameengine.basic.helper.ComponentRegistry;
import de.black.core.gameengine.basic.helper.VectorHelper;
import de.black.core.gameengine.logics.concrete.physics.BodyAdaptorComponent;
import de.black.core.gameengine.renderer.concrete.ASCIISpriteAnimation;
import de.black.core.tools.physics.BodyBuilder;
import de.black.core.tools.physics.FixtureDefBuilder;

public class CoreGameContentProvider {

	
	public static void initGameObjects(World physicsWorld) {
		GameObject player = new GameObject(new Vector2f(256f,256f), Tags.PLAYER);
		
		ASCIISpriteAnimation ascii = (ASCIISpriteAnimation) AssetManager.getInstance().getAsset("cat");
		ascii.color = Color.white;
		player.addRenderComp(ascii);
		player.addLogicComp(new StupidAnimationComponent());
		BodyBuilder catBuilder = BodyBuilder.getBodyBuilder(physicsWorld).withAwake(true).withActive(true).withType(BodyType.DYNAMIC);
		BodyAdaptorComponent catBody = new BodyAdaptorComponent();
		player.addLogicComp(catBody);

		ASCIIPicture[] floorPic = {new ASCIIPicture("_________________________________________")};
		ASCIISpriteAnimation asciiFloor = new ASCIISpriteAnimation(floorPic);
		
		GameObject floor = new GameObject(new Vector2f(0f,Settings.getInstance().getInt(Settings.SCREEN_HEIGHT) - 20f));
		BodyBuilder floorBuilder = BodyBuilder.getBodyBuilder(physicsWorld)
				.withType(BodyType.STATIC)
						.withActive(true);
		BodyAdaptorComponent bodyFloor = new BodyAdaptorComponent();
		
		asciiFloor.color = Color.cyan;
		floor.addLogicComp(bodyFloor);
		floor.addRenderComp(asciiFloor);
//		FixtureDefBuilder fd = new FixtureDefBuilder();
		bodyFloor.buildBodyWithSpriteCollider(floorBuilder);
		catBody.buildBodyWithSpriteCollider(catBuilder);
		ComponentRegistry.getInstance();
	}
}
