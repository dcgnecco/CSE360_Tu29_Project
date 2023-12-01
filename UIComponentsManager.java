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

public class UIComponentsManager {
	private final Stage primaryStage;
	
    ObservableList<String> items = FXCollections.observableArrayList(		//modify this as needed
    		"Temporary",
    		"box lol",
    		"You can add as much",
    		"as you want here",
    		"it's an arrayList, so",
    		"it should scale",
    		"yippee!!"
    		);
    
    int stopa;
    int stopa2;
	
	public UIComponentsManager(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void setupUI() {
		primaryStage.setTitle("EffortLogger V2");	//sets window name
        
        VBox mainVSet = new VBox();			//creates new Vbox for alignments
        HBox withHSet = new HBox();
        
        GridPane root = createGridPane();
        arrangeComponents(root, mainVSet, withHSet);
        
        mainVSet.setAlignment(Pos.CENTER);
        withHSet.setAlignment(Pos.CENTER);
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
	}
	
	private GridPane createGridPane() {
		GridPane root = new GridPane();		//basically makes new window
		
		root.setHgap(10);		//creates gaps, i guess
        root.setVgap(10);
        root.setPadding(new Insets(60));
        //root.setGridLinesVisible(true);
        root.setAlignment(Pos.CENTER);
        
        root.setAlignment(Pos.CENTER);
        
        ColumnConstraints col1 = new ColumnConstraints(); ColumnConstraints col2 = new ColumnConstraints(); ColumnConstraints col3 = new ColumnConstraints();
        col1.setPercentWidth(60); col2.setPercentWidth(60); col3.setPercentWidth(60);
        root.getColumnConstraints().addAll(col1, col2, col3);
        
        
        
        setRowConstraints(root);
        
        centerAllElements(root);
        
        return root;
	}
	
	private void arrangeComponents(GridPane root, VBox mainVSet, HBox withHSet) {
		CreateComboBox projectsBox = new CreateComboBox(items);		//it does not have to be "items", anything can be included.
        CreateComboBox lifeCycBox = new CreateComboBox(items);
        CreateComboBox effBox = new CreateComboBox(items);
        CreateComboBox planBox = new CreateComboBox(items);
        
        CreateLabel firstInst = new CreateLabel("1. When you start a new activity,\n press the \"Start an Activity\"\n button", "Arial", FontWeight.BOLD, 20);
        CreateLabel secInst = new CreateLabel("2. Select the project, life cycle step,\n effect category, and deliverable\n from the following lists:", "Arial", FontWeight.BOLD, 20);
        CreateLabel projects = new CreateLabel("Project:", "Arial", FontWeight.BOLD, 20);
        CreateLabel lifeCyc = new CreateLabel("Life Cycle Steps", "Arial", FontWeight.BOLD, 20);
        CreateLabel effCat = new CreateLabel("Effort Category", "Arial", FontWeight.BOLD, 20);
        CreateLabel plan = new CreateLabel("Plan", "Arial", FontWeight.BOLD, 20);
        CreateLabel thirdInst = new CreateLabel("3. Press the \"Stop this Activity\" to \n generate an effort log entry \nusing the attributes above.", "Arial", FontWeight.BOLD, 20);
        
        CreateButton effortEdit = new CreateButton("Effort Log Editor", "Impact", FontWeight.NORMAL, 15, e -> {
        	System.out.println("temp");
        });
        CreateButton defLog = new CreateButton("Defect Log Console", "Impact", FontWeight.NORMAL, 15, e -> {
        	System.out.println("temp");
        });
        CreateButton effortDefLog = new CreateButton("Effort and Defect Logs", "Impact", FontWeight.NORMAL, 15, e -> {
        	System.out.println("temp");
        });
        CreateButton def = new CreateButton("Definitions", "Impact", FontWeight.NORMAL, 15, e -> {
        	System.out.println("temp");
        });
        CreateButton stopAct = new CreateButton("Stop this Activity", "Impact", FontWeight.NORMAL, 15, e -> {
        	canStop(0);
        	System.out.println(canStop());
        	
        	projectsBox.setDisable(true); lifeCycBox.setDisable(true); effBox.setDisable(true); planBox.setDisable(true);
        });
        stopAct.setDisable(true);
        CreateButton startAct = new CreateButton("Start an Activity!", "Impact", FontWeight.NORMAL, 15, e -> {		//easily add actions or call any method by replacing the temp line. Also no reason for font, just fun.
        	System.out.println("temp");
        	canStop(1);
        	System.out.println(canStop());
        	
        	projectsBox.setDisable(false); lifeCycBox.setDisable(false); effBox.setDisable(false); planBox.setDisable(false); stopAct.setDisable(false);
        });

        
        projectsBox.setDisable(true); lifeCycBox.setDisable(true); effBox.setDisable(true); planBox.setDisable(true);
        
        root.add(firstInst, 0, 0); root.add(startAct, 0, 3); root.add(withHSet, 0, 5); root.add(def, 0, 6);
        root.add(thirdInst, 1, 8); root.add(stopAct, 1, 9);
        root.add(secInst, 2, 0); root.add(projects, 2, 1); root.add(projectsBox, 2, 2); root.add(lifeCyc, 2, 3); root.add(lifeCycBox, 2, 4); root.add(effCat, 2, 5); root.add(effBox, 2, 6); root.add(plan, 2, 7); root.add(planBox, 2, 8);
        
        withHSet.getChildren().addAll(effortEdit, defLog, effortDefLog);
	}
	
	private void centerAllElements(GridPane gPane) {
    	for (int i = 0; i < gPane.getChildren().size(); i++) {
    		Node child = gPane.getChildren().get(i);
    		GridPane.setHalignment(child, javafx.geometry.HPos.CENTER);
    		GridPane.setValignment(child, javafx.geometry.VPos.CENTER);
    	}
    }
	
    private void setRowConstraints(GridPane gPane) {
    	for (int i = 0; i < gPane.getRowCount(); i++) {
    		RowConstraints rowConstraints = new RowConstraints();
    		rowConstraints.setVgrow(javafx.scene.layout.Priority.ALWAYS);
    		
    		gPane.getRowConstraints().add(rowConstraints);
    	}
    }
    
    public class CreateLabel extends Label {
    	public CreateLabel(String text, String fontFamily, FontWeight fontWeight, double fontSize) {
    		super(text);
    		setFont(Font.font(fontFamily, fontWeight, fontSize));
    	}
    }
    
	public class CreateButton extends Button {
    	public CreateButton(String text, String fontFamily, FontWeight fontWeight, double fontSize, EventHandler<ActionEvent> actionHandler) {
    		super(text);
    		setFont(Font.font(fontFamily, fontWeight, fontSize));
    		setOnAction(actionHandler);
    	}
    }
    
    public class CreateComboBox extends ComboBox<String> {
    	public CreateComboBox(ObservableList<String> items) {
    		super(items);
    	}
    }
    
    private int canStop(int i) {
		stopa = i;
		return stopa;
	}
    
    private int canStop() {
    	return stopa;
    }
}
