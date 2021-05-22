//--module-path ${PATH_TO_JAVAFX} --add-modules javafx.controls,javafx.fxml
package p2219;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DigitalClock extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("DigitalClock.fxml"));
        primaryStage.setTitle("Digital Clock");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
