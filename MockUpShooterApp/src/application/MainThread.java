package application;

import handlers.GameStateManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * @author TofuSensei
 * Based on Timeline as gameloop inspired by @https://github.com/tutsplus/Introduction-to-JavaFX-for-Game-Development/blob/master/Example3T.java
 * Credit goes to: Michael James Williams Initial files from Lee Stemkoski @github
 */
public class MainThread 
{

	private Timeline gameLoop;
	private GameStateManager gsm;
    private Pane pane;
    private GraphicsContext gc;
    
    private double delta; 
    private long time;
    private double lastTime;
	
    
	public MainThread(Pane pane, Canvas canvas) 
	{
        this.gameLoop = new Timeline();
        this.gameLoop.setCycleCount( Timeline.INDEFINITE );
		this.pane = pane;
        this.gc = canvas.getGraphicsContext2D(); // Used only for clearing screen atm
        this.gsm = new GameStateManager(this);

        this.lastTime = System.nanoTime();
        
        KeyFrame kf = new KeyFrame
        (
            Duration.seconds(0.017), // 60 FPS
            new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent ae)
                {
                	// Interpolate time to avoid stutters
                    time = System.nanoTime();
                    delta = ((time - lastTime) / 1000000);
                    lastTime = time;
                    update(delta);
                    render();
                }
            });
        
        this.gameLoop.getKeyFrames().add(kf);
        this.gameLoop.play();
	}
	
	/**
	 * Pane is used to hold Sprites and textures
	 * @return
	 */
	public Pane getPane()
	{
		return this.pane;
	}
	
	/**
	 * Updates logic
	 */
	public void update(double delta)
	{
		this.gsm.update(delta);
	}
	
	/**
	 * Renders Sprites and textures
	 */
	public void render()
	{
        // Clear the screen
        this.gc.clearRect(0, 0, this.pane.getWidth(), this.pane.getHeight());
        this.gsm.render();
	}
	
}
