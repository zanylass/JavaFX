package p2224;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class ShadowsController implements Initializable {

    @FXML private Rectangle rect;
    @FXML private Slider sliderRadius;
    @FXML private Slider sliderXoffset;
    @FXML private Slider sliderYoffset;

    DropShadow dropShadow = new DropShadow();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        rect.setEffect(dropShadow);

        sliderRadius.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                dropShadow.setRadius(sliderRadius.getValue());
            }
        });

        sliderXoffset.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                dropShadow.setOffsetX(sliderXoffset.getValue());
            }
        });

        sliderYoffset.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                dropShadow.setOffsetY(sliderYoffset.getValue());
            }
        });
    }

}
