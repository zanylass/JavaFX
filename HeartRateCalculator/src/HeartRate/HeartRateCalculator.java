/*
*********************
* Author: Gul Meeri *
*********************

VM path
--module-path ${PATH_TO_JAVAFX} --add-modules javafx.controls,javafx.fxml
 */
package HeartRate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HeartRateCalculator extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("HeartRateCalculator.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Target Heart Rate Calculator"); // Displayed in window's title bar
        stage.show();
    }
}
