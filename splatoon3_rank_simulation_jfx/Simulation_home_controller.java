/**
 * "Splatoon3_rank_simulation_jfx.fxml"コントローラ・クラスのサンプル・スケルトン
 */

package splatoon3_rank_simulation_jfx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class Simulation_home_controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="text_field_win"
    TextField text_field_win; // Value injected by FXMLLoader

    @FXML // fx:id="text_field_lose"
    private TextField text_field_lose; // Value injected by FXMLLoader

    @FXML // fx:id="text_field_rank_p"
    private TextField text_field_rank_p; // Value injected by FXMLLoader

    @FXML // fx:id="text_field_splus_level"
    private TextField text_field_splus_level; // Value injected by FXMLLoader

    @FXML // fx:id="button_a"
    private ToggleButton button_a; // Value injected by FXMLLoader

    @FXML // fx:id="button_b"
    private ToggleButton button_b; // Value injected by FXMLLoader

    @FXML // fx:id="button_c"
    private ToggleButton button_c; // Value injected by FXMLLoader

    @FXML // fx:id="button_s"
    private ToggleButton button_s; // Value injected by FXMLLoader

    @FXML // fx:id="button_splus"
    private ToggleButton button_splus; // Value injected by FXMLLoader

    @FXML // fx:id="button_start"
    private Button button_start; // Value injected by FXMLLoader

    @FXML
    void button_a_action(ActionEvent event) {
        System.out.println("Pushed [A]");
    }

    @FXML
    void button_b_action(ActionEvent event) {
        System.out.println("Pushed [B]");
    }

    @FXML
    void button_c_action(ActionEvent event) {
        System.out.println("Pushed [C]");
    }

    @FXML
    void button_s_action(ActionEvent event) {
        System.out.println("Pushed [S]");
    }

    @FXML
    void button_splus_action(ActionEvent event) {
        System.out.println("Pushed [S+]");
    }

    @FXML
    void button_start_action(ActionEvent event) {
        System.out.println(text_field_win.getText());
        System.out.println(text_field_lose.getText());
        System.out.println(text_field_rank_p.getText());
        System.out.println(text_field_splus_level.getText());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert button_a != null : "fx:id=\"button_a\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert button_b != null : "fx:id=\"button_b\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert button_c != null : "fx:id=\"button_c\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert button_s != null : "fx:id=\"button_s\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert button_splus != null : "fx:id=\"button_splus\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert button_start != null : "fx:id=\"button_start\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert text_field_lose != null : "fx:id=\"text_field_lose\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert text_field_rank_p != null : "fx:id=\"text_field_rank_p\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert text_field_splus_level != null : "fx:id=\"text_field_splus_level\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert text_field_win != null : "fx:id=\"text_field_win\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";

    }

}
