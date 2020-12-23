/*
*********************
* Author: Gul Meeri *
*********************

VM path
--module-path ${PATH_TO_JAVAFX} --add-modules javafx.controls,javafx.fxml
 */
package PainterAppModified;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PainterApp extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PainterApp.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Painter App"); // Displayed in window's title bar
        stage.show();
    }
}
