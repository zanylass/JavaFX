package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

        Text text = new Text();
        text.setEffect(dropShadow);
        text.setCache(true);
        text.setX(10.0);
        text.setY(70.0);
        text.setFill(Color.web("0x3b596d"));
        text.setText("JavaFX...");
        text.setFont(Font.font(null, FontWeight.BOLD, 40));

        

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
