//Author: Gul Meeri///
//VM path
//--module-path ${PATH_TO_JAVAFX} --add-modules javafx.controls,javafx.fxml
package BMI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BMICalculator extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BMICalculator.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("BMI Calculator"); // Displayed in window's title bar
        stage.show();
    }
}
