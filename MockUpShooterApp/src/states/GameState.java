/**
 * 
 */
package states;

import application.MainThread;
import handlers.GameStateManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

/**
 * @author TofuSensei
 *  * Credit goes to foreign.guy.mike@gmail.com tutorials for LibGdx on Youtube
 */
public abstract class GameState 
{
	
	protected GameStateManager gsm;
	protected MainThread mainThread;
	
    protected Pane pane;
    protected GraphicsContext gc;
	
	protected GameState(GameStateManager gsm)
	{
		this.gsm = gsm;
		this.mainThread = gsm.getGameLoop();
		this.pane = gsm.getPane();
		this.gc = gsm.getGraphicsContext();
	}
	
	public abstract void handleInput(double delta);
	public abstract void update(double delta);
	public abstract void render();
	public abstract void dispose();

}
