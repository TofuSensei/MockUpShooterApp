package application;
	
import java.io.File;

import gfx.SpriteSheet;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @author TofuSensei
 * Standard JavaFX application main class
 */
public class Main extends Application 
{
	private Stage window; // The Window
	private Pane root; // The only element on the window so far
	
	@Override
	public void init()
	{
		// TODO this should not be here, needs to get integrated to Content class
		SpriteSheet.loadSheet(new Image(new File("assets/Sprites01.png").toURI().toString()));
	}
	
	@Override
	public void start(Stage primaryStage) 
	{
		this.window = primaryStage;
		this.window.setTitle("Mock-Up Shooter App");
		
		try 
		{
			// Sets the application up as fullscreen 
			// Reads the screens bounds and then set the window up with the collected parameters
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();
			
			this.window.setX(bounds.getMinX());
			this.window.setY(bounds.getMinY());
			this.window.setWidth(bounds.getWidth());
			this.window.setHeight(bounds.getHeight());
			
			// Pane, usable as drawing space
			this.root = new Pane();
			Scene s = new Scene(root, bounds.getWidth(), bounds.getHeight());
			this.root.setStyle("-fx-background-color: white;");
			this.root.setPrefSize(bounds.getWidth(), bounds.getHeight());
			
			// Starts the game loop
			final Canvas canvas = new Canvas(bounds.getWidth(), bounds.getHeight());
			new MainThread(this.root, canvas);
			
			this.window.setResizable(false);
			this.window.setScene(s);
			this.window.show();

		} 
		catch(Exception e) 
		{
			e.printStackTrace();
			Platform.exit();
		}
	}
	
	@Override
	public void stop()
	{
		
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
