package gfx;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author TofuSensei
 * Solution for displaying textures as a ViewPort from a sprite sheet
 */
public class Texture extends ImageView
{

	private Image sheet;
	private Rectangle2D texture;
	
	/**
	 * Creates a new texture, takes the origin sheet and the texture as Rectangle2D
	 * @param sheet
	 * @param texture
	 */
	public Texture(Image sheet, Rectangle2D texture) 
	{
		this.sheet = sheet;
		this.texture = texture;
		
		setImage(this.sheet);
		setViewport(this.texture);
	}

}
