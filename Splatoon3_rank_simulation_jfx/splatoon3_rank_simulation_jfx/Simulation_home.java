package splatoon3_rank_simulation_jfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Simulation_home extends Application{
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/splatoon3_rank_simulation_jfx/Simulation_home.fxml"));
			Scene scene = new Scene(root,450,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Simulation Home");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}