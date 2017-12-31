package gfx;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author TofuSensei
 * Sprites are basically an image with a Rectangle2D viewport on top.
 */
public class Sprite extends ImageView
{
	private Image sheet;
	private Rectangle2D[] frames;
	private float delay;
	
	public Sprite(Image sheet, Rectangle2D[] frames, float delay) 
	{
		this.sheet = sheet;
		this.frames = frames;
		this.delay = delay;
		
		setImage(this.sheet);
		setViewport(this.frames[0]);
	}
	
	/**
	 * Get delay for next frame to display
	 * @return
	 */
	public float getDelay()
	{
		return this.delay;
	}
	
	/**
	 * Returns the current frames coodrinates as Rectangle2D
	 * @param currentFrame
	 * @return
	 */
	public Rectangle2D getFrame(int currentFrame)
	{
		return this.frames[currentFrame];
	}
	
	/**
	 * Returns the total count of frames
	 * @return
	 */
	public int getFramesLength()
	{
		return this.frames.length;
	}
	
	/**
	 * Sets the viewport to the specified frame number
	 * @param frame
	 */
	public void setViewport(int frame)
	{
		setViewport(this.frames[frame]);
	}

}
