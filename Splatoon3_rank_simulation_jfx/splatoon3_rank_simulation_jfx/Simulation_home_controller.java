/**
 * "Splatoon3_rank_simulation_jfx.fxml"コントローラ・クラスのサンプル・スケルトン
 */

package splatoon3_rank_simulation_jfx;

import java.io.IOException;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Simulation_home_controller {

	//match用変数
	String win_str, lose_str, rank = "C", splus_level_str, rank_point_str;
	int win, lose, splus_level, rank_point;
	
	//リザルトテキスト保存
	List<String> result_text_list = new ArrayList<>();
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="text_field_win"
    private TextField text_field_win; // Value injected by FXMLLoader

    @FXML // fx:id="text_field_lose"
    private TextField text_field_lose; // Value injected by FXMLLoader

    @FXML // fx:id="text_field_rank_point"
    private TextField text_field_rank_point; // Value injected by FXMLLoader

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

    @FXML // fx:id="group_rank"
    private ToggleGroup group_rank; // Value injected by FXMLLoader

    @FXML // fx:id="button_start"
    private Button button_start; // Value injected by FXMLLoader

    @FXML
    void button_a_action(ActionEvent event) {
    	rank = "A-";
//        System.out.println("Pushed [A]");
    }

    @FXML
    void button_b_action(ActionEvent event) {
    	rank = "B-";
//        System.out.println("Pushed [B]");
    }

    @FXML
    void button_c_action(ActionEvent event) {
    	rank = "C-";
//        System.out.println("Pushed [C]");
    }

    @FXML
    void button_s_action(ActionEvent event) {
    	rank = "S";
//        System.out.println("Pushed [S]");
    }

    @FXML
    void button_splus_action(ActionEvent event) {
    	rank = "S+";
//        System.out.println("Pushed [S+]");
    }

    @FXML
    void button_start_action(ActionEvent event) {
        System.out.println(text_field_win.getText());
        System.out.println(text_field_lose.getText());
        System.out.println(text_field_rank_point.getText());
        System.out.println(text_field_splus_level.getText());
        
        try {
        	win_str = Normalizer.normalize(text_field_win.getText(), Normalizer.Form.NFKC); //全角を半角に変える
            win = Integer.parseInt(win_str); //文字列をintに変換
            lose_str = Normalizer.normalize(text_field_lose.getText(), Normalizer.Form.NFKC); //全角を半角に変える
            lose = Integer.parseInt(lose_str); //文字列をintに変換
            rank_point_str = Normalizer.normalize(text_field_rank_point.getText(), Normalizer.Form.NFKC); //全角を半角に変える
            rank_point = Integer.parseInt(rank_point_str); //文字列をintに変換
            splus_level_str = Normalizer.normalize(text_field_splus_level.getText(), Normalizer.Form.NFKC); //全角を半角に変える
            splus_level = Integer.parseInt(splus_level_str); //文字列をintに変換
            
            if (splus_level < 0 || 50 < splus_level) { //S+の数値が正しくないときエラー
            	System.out.println("Error! : 正しい数値を入力してください !");
            } if (rank_point < -9999 || 9999 < rank_point) { //ウデマエの数値が正しくないとエラー
            	System.out.println("Error! : -9999から9999の間で入力してください !");
            } if (win < 0 || lose < 0) { //勝ち数か負け数が正しくないときエラー
            	System.out.println("Error! : 勝敗は正の整数で入力 !");
            } else { //エラーチェックを逃れた場合シミュレーション開始
            	result_text_list = Splatoon3_rank_simulation_jfx.splatoon3_rank_simulation_jfx(win, lose, rank, splus_level, rank_point);
            	simulation_result();
            }
        } catch (Exception e) {
        	System.out.println("Catch Error! [home_controller]");
        	e.printStackTrace();
        }
    }
    
    void simulation_result() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Simulation_result.fxml"));
		BorderPane root = (BorderPane) loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		Simulation_result_controller result_con = loader.<Simulation_result_controller>getController();
		
		//リザルト画面にシミュレーション結果のテキスト入れる
//		result_con.text_area_result.appendText("setText test");
		for(int i = 0; i < result_text_list.size(); i++) {
			result_con.text_area_result.appendText(result_text_list.get(i));
		}
		//テキスト編集不可
		result_con.text_area_result.setEditable(false);
		stage.setTitle("Simulation Result");
		stage.setScene(scene);
		stage.showAndWait();
		
	}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert button_a != null : "fx:id=\"button_a\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert button_b != null : "fx:id=\"button_b\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert button_c != null : "fx:id=\"button_c\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert button_s != null : "fx:id=\"button_s\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert button_splus != null : "fx:id=\"button_splus\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert rank != null : "fx:id=\"rank\" was not injected: check your FXML file 'Simulation_home.fxml'.";
        assert button_start != null : "fx:id=\"button_start\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert text_field_lose != null : "fx:id=\"text_field_lose\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert text_field_rank_point != null : "fx:id=\"text_field_rank_p\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert text_field_splus_level != null : "fx:id=\"text_field_splus_level\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";
        assert text_field_win != null : "fx:id=\"text_field_win\" was not injected: check your FXML file 'Splatoon3_rank_simulation_jfx.fxml'.";

    }

}
