/**
 * 
 */
package states;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import entities.MoB;
import gfx.SpriteSheet;
import handlers.CollisionListener;
import handlers.GameStateManager;

/**
 * @author TofuSensei
 *
 */
public class GameBase extends GameState
{

	private GameStateManager gsm; // Needed to change states
	private World world;
    private ArrayList<MoB> mobs; // Holds all entities
	
	public GameBase(GameStateManager gsm) 
	{
		super(gsm); 
		this.gsm = gsm;
		this.pane = gsm.getPane();
		this.gc = gsm.getGraphicsContext();
		
		this.world = new World(new Vec2(0.0f, 0.0f));
		this.world.setContactListener(new CollisionListener());
		
        this.mobs = new ArrayList<MoB>();
        
        // Test for Sprite insert
        MoB test = new MoB(SpriteSheet.getSprite(0, 0, 200.0f), null);
        test.setPos(10, 10);
        this.mobs.add(test);
        this.pane.getChildren().add(test.getSprite());
        // Test for Sprite insert
	}

	@Override
	public void handleInput(double delta) 
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * Update the game logic
	 */
	@Override
	public void update(double delta) 
	{
		for(int i = 0; i < this.mobs.size(); i++)
			this.mobs.get(i).update((float)delta);
	}

	/**
	 * Render Sprites and textures
	 */
	@Override
	public void render() 
	{
		for(int i = 0; i < this.mobs.size(); i++)
			this.mobs.get(i).draw();
	}

	@Override
	public void dispose() 
	{
		// TODO Auto-generated method stub
		
	}

}
