//--module-path ${PATH_TO_JAVAFX} --add-modules javafx.controls,javafx.fxml
package p2217;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Kaleidoscope extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Kaleidoscope.fxml"));
        primaryStage.setTitle("Kaleidoscope");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
