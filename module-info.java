module splatoon3_rank_simulation {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens splatoon3_rank_simulation_jfx to javafx.graphics, javafx.fxml;
}
