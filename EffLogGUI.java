package effortLoggerPro;

import javafx.application.Application;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
 
public class EffLogGUI extends Application {
	private Stage primaryStage;
	
    public static void main(String[] args) {
        launch(args);
    }
    

    
    public void start(Stage primaryStage) {
    	
    	System.out.println("Effort Logger time!");	//I am so sorry about the mess of this code. This is really rough right now.
    	System.out.println("Alright, here comes the main UI...");
    	
    	UIComponentsManager componentsManager = new UIComponentsManager(primaryStage);
    	componentsManager.setupUI();
    }
    
    public Scene startApp() {
    	primaryStage.show();
    	return primaryStage.getScene();
    }
    



    

    
    
    

    
}