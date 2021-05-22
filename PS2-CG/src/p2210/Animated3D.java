package p2210;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Animated3D extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Animated3D.fxml"));
        Scene scene = new Scene(root);

        PerspectiveCamera camera = new PerspectiveCamera();
        scene.setCamera(camera);
        stage.setTitle("Animated Art 3D");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

