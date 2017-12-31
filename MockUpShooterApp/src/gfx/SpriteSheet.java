package gfx;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author TofuSensei
 * Class manages sprite cutting and saving operations works with rows and columns.
 * Total number of frames for an animation has to be set
 */
public class SpriteSheet extends ImageView
{

	private static int SPRITE_FRAMES = 3;//Number of frames (columns on the sheet) taken for an animation 
	
	private static int TOTAL_COLUMNS = 12;//Total Number of columns on the sheet
	private static int TOTAL_ROWS = 8;//Total Number of rows on the sheet
	
	private static int SPRITE_WIDTH = 32;//Width of a single Sprite(block) on the sheet
	private static int SPRITE_HEIGHT = 32;//Height of a single Sprite(block) on the sheet
	
	private static Image sheet;//The sheet itself
	private static Rectangle2D[][] frames;//Region array for the cut sheet
	
	/**
	 * Loads new parameters into the cut and animation methods
	 * @param frames the number of frames taken for an animation
	 */
	public final static void setFrameCount(int frames)
	{
		SPRITE_FRAMES = frames;
	}
	
	/**
	 * Loads new parameters into the cut and animation methods
	 * @param columns, the total number of columns in the sheet 
	 * @param rows, the total number of rows in the sheet
	 */
	public final static void setSheetDimensions(int columns, int rows)
	{
		TOTAL_COLUMNS = columns;
		TOTAL_ROWS = rows;
	}
	
	/**
	 * Loads new parameters into the cut and animation methods
	 * @param width sprites width
	 * @param height sprites height
	 */
	public final static void setSpriteDimensions(int width, int height)
	{
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
	}
	
	/**
	 * Needs to be called before any SpriteSheet operations
	 * ,loads and holds one sheet at a time
	 * @param sheet
	 */
	public static void loadSheet(Image sheet) 
	{
		SpriteSheet.sheet = sheet;
		SpriteSheet.frames = new Rectangle2D[SpriteSheet.TOTAL_ROWS][SpriteSheet.TOTAL_COLUMNS];
		
		for(int i = 0; i < SpriteSheet.TOTAL_ROWS; i++)
			for(int j = 0; j < SpriteSheet.TOTAL_COLUMNS; j++)
			{
				SpriteSheet.frames[i][j] = new Rectangle2D(j*SpriteSheet.SPRITE_WIDTH, i* SpriteSheet.SPRITE_HEIGHT, 
																SpriteSheet.SPRITE_WIDTH, SpriteSheet.SPRITE_HEIGHT);
			}
	}
	
	/**
	 * Returns all cells needed for an animated sprite 
	 * , uses fixed row and column parameters!
	 * @param start_row the start row's index
	 * @param start_colum the start column's index
	 */
	public final static Sprite getSprite(int start_row, int start_column, float frameDelay)
	{
		if(frames == null)
		{
			System.err.println("No sheet texture loaded");
			Platform.exit();
		}

		Rectangle2D[] animationFrames = new Rectangle2D[SPRITE_FRAMES];

		int index = 0;

		for(int i = 0; i < SPRITE_FRAMES; i++)
		{
			try
			{
				animationFrames[index++] = frames[start_row][start_column+i];
			}
			catch(IndexOutOfBoundsException ob)
			{
				System.err.println("Check start row/column,the set row/column total count and the actual sheet cell count");
				Platform.exit();
			}
		}

		return new Sprite(SpriteSheet.sheet, animationFrames, frameDelay);
	}
	
	/**
	 * Returns the cells of a texture 
	 * , uses fixed row and column parameters!
	 * @param start_row the start row's index
	 * @param start_colum the start column's index
	 */
	public final static Texture getTexture(int start_row, int start_column)
	{
		Rectangle2D animationFrames = new Rectangle2D(start_column*SpriteSheet.SPRITE_WIDTH, start_row* SpriteSheet.SPRITE_HEIGHT, 
																				SpriteSheet.SPRITE_WIDTH, SpriteSheet.SPRITE_HEIGHT);
		return new Texture(SpriteSheet.sheet, animationFrames);
	}

}
