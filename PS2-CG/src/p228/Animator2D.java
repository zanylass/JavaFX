//--module-path ${PATH_TO_JAVAFX} --add-modules javafx.controls,javafx.fxml
package p228;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Animator2D extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Animator2D.fxml"));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("2D Art Animator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
