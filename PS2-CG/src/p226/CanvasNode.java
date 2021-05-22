//--module-path ${PATH_TO_JAVAFX} --add-modules javafx.controls,javafx.fxml
package p226;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CanvasNode extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("canvas1.fxml"));
        primaryStage.setTitle("Canvas");
        primaryStage.setScene(new Scene(root, 750, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
