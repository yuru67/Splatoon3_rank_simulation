package splatoon3_rank_simulation_jfx;

/**
 * "Simulation_result.fxml"コントローラ・クラスのサンプル・スケルトン
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Simulation_result_controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="text_area_result"
    private TextArea text_area_result; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert text_area_result != null : "fx:id=\"text_area_result\" was not injected: check your FXML file 'Simulation_result.fxml'.";

    }

}
