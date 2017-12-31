/**
 * 
 */
package entities;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import gfx.Sprite;

/**
 * @author TofuSensei
 * Class represents mobile game objects (mobs)
 */
public class MoB
{
	private Sprite sprite;
	private Body body;
	private float time;
	private float delay;
	private int currentFrame;
	private int timesPlayed;
	
	/**
	 * A MoB needs a Sprite as image and a body for physical interaction
	 * @param sprite
	 * @param body
	 */
	public MoB(Sprite sprite, Body body) 
	{
		this.sprite = sprite;
		this.body = body;
		this.body.getLocalCenter().set(0, 0);
		this.delay = this.sprite.getDelay();
		this.currentFrame = 0;
		this.timesPlayed = 0;
		this.time = 0;
	}
	
	/**
	 * Sets the MoBs position on the game field
	 * @param x
	 * @param y
	 */
	public void setPos(float x, float y)
	{
		this.body.getLocalCenter().set(x, y);
		this.sprite.setX(x);
		this.sprite.setY(y);
	}
	
	/**
	 * Returns the MoBs position on the game field
	 * @param x
	 * @param y
	 */
	public Vec2 getPos()
	{
		return this.body.getLocalCenter();
	}
	
	/**
	 * Returns the body attachted to this MoB 
	 */
	public Body getBody()
	{
		return this.body;
	}
	
	/**
	 * Returns the sprite attachted to this mob
	 * @return
	 */
	public Sprite getSprite()
	{
		return this.sprite;
	}
	
	/**
	 * Counts the delay until the next frame.
	 * (The speed of the animation)
	 * @param delta
	 */
	public void updateAnimation(float delta)
	{
		if(this.delay <= 0)
		{
			return;
		}
		this.time += delta;
		while(this.time >= this.delay)
		{
			this.step();
		}
	}
	
	/**
	 * Iterates animations frames and creates hit boxes if needed
	 */
	private void step()
	{
		this.time -= this.delay;
		++this.currentFrame;

		if(this.currentFrame >= this.sprite.getFramesLength())//upper and lower frames must have the same length
		{
			this.currentFrame = 0;
			++this.timesPlayed;
		}
	}
	
	/**
	 * Updates the logic and graphic of this mob
	 * @param delta
	 */
	public void update(float delta)
	{
		updateAnimation(delta);
	}
	
	/**
	 * Draws this mob
	 */
	public void draw()
	{
		this.sprite.setViewport(currentFrame);
	}
	
}
