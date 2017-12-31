/**
 * 
 */
package factories;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import application.MainThread;
import entities.MoB;
import gfx.Sprite;
import gfx.SpriteSheet;

/**
 * @author TofuSensei
 *
 */
public class MoBFactory 
{

	private World world;
	/**
	 * 
	 */
	public MoBFactory(World world) 
	{
		this.world = world;
	}
	
	

	/**
	 * Creates a MoB object with a circle hit box
	 * @param master_key Texture identifier
	 * @param name MoBs name
	 * @param circle_radius hit box circle radius
	 * @param center_X hit box circle X position
	 * @param center_Y hit box circle y position
	 * @param categoryBits is of type
	 * @param maskBits collides with type
	 * @param spriteSizes for the use of different spritesheet sizes
	 * @return
	 */
	public MoB createCircleMoB(int master_key, int collisionHash, String name, float circle_radius, float center_X, float center_Y, short categoryBits, short maskBits, float[] spriteSizes)
	{
		BodyDef def = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		def.type = BodyType.DYNAMIC;
		//T B C if needed to set position manually to a default value sometimes
		//def.position.set(posX / GameConstants.PPM, posY / GameConstants.PPM);
		def.fixedRotation = true;
		Body body = this.world.createBody(def);
		
		CircleShape shape = new CircleShape();
		SpriteSheet.loadSheet(MainThread.res.getTexture(master_key + ContentHashCodes.move));
		SpriteSheet.resetToDefault();
		Sprite sprite = SpriteSheet.getFrame(0, 0, 0, 0); // The basic not animated sprite

		MoB mob = null;
		mob = new MoB(name, collisionHash, body, sprite, master_key, spriteSizes);
		mob.loadAnimation(ContentHashCodes.move, ContentHashCodes.idle + ContentHashCodes.down);
		
		// create hit box / collision fixture
		shape.setRadius(circle_radius);
		shape.setPosition(new Vec2(center_X, center_Y));
		
		fdef.filter.categoryBits = categoryBits;//Has category (bits hardcoded)
		fdef.filter.maskBits = maskBits;//Allowed to collide with 
		fdef.shape = shape;
		fdef.friction = 1f;
		fdef.density = 1f;
		
		body.createFixture(fdef).setUserData(mob);
		shape.dispose();
		
		return mob;
	}

}
