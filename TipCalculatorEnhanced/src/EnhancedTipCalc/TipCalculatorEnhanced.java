//VM path
//--module-path ${PATH_TO_JAVAFX} --add-modules javafx.controls,javafx.fxml

package EnhancedTipCalc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TipCalculatorEnhanced extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TipCalculatorEnhanced.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Enhanced Tip Calculator"); // Displayed in window's title bar
        stage.show();
    }
}
