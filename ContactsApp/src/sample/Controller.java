/*
*********************
* Author: Gul Meeri *
*********************

VM path
--module-path ${PATH_TO_JAVAFX} --add-modules javafx.controls,javafx.fxml
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ContactsApp.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Contacts App"); // Displayed in window's title bar
        stage.show();
    }
}
