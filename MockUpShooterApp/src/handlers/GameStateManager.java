/**
 * 
 */
package handlers;

import java.util.Stack;

import application.MainThread;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import states.GameBase;
import states.GameState;

/**
 * @author TofuSensei
 * Credit goes to foreign.guy.mike@gmail.com tutorials for LibGdx on Youtube
 */
public class GameStateManager 
{

	private MainThread gameLoop;
	private Stack<GameState> gameStates;
    private Pane pane;
    private GraphicsContext gc;
	
	public static final int STARTSCREEN_MENU = 100001;
	public static final int GAME_BASE = 100002;
	
	public GameStateManager(MainThread mainThread) 
	{
		this.gameLoop = mainThread;
		this.gameStates = new Stack<GameState>();
		this.pane = mainThread.getPane();
		pushState(GAME_BASE);
	}
	
	public MainThread getGameLoop()
	{
		return this.gameLoop;
	}
	
	public Pane getPane()
	{
		return this.pane;
	}
	
	public GraphicsContext getGraphicsContext()
	{
		return this.gc;
	}
	
	public void update(double delta)
	{
		gameStates.peek().update(delta);
	}
	
	public void render()
	{
		gameStates.peek().render();
	}
	
	private GameState getState(int state)
	{
		if(state == GAME_BASE)
		{
			return new GameBase(this);
		}

		return null;
	}
	
	public void setState(int state)
	{
		pushState(state);
	}
	
	public void pushState(int state)
	{
		gameStates.push(getState(state));
	}
	
	public void popState()
	{
		GameState gs = gameStates.pop();
		gs.dispose();
	}

}
