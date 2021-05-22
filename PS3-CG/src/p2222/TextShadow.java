//--module-path ${PATH_TO_JAVAFX} --add-modules javafx.controls,javafx.fxml
package p2222;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextShadow extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("TextShadow.fxml"));
        primaryStage.setTitle("Text Shadow");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

        Label textLabel = new Label("JavaFX");
        textLabel.setFont(Font.font(null, FontWeight.BOLD, 60));
        textLabel.setEffect(dropShadow);

        StackPane root = new StackPane();
        root.getChildren().add(textLabel);

        primaryStage.setScene(new Scene(root,300,300));

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
