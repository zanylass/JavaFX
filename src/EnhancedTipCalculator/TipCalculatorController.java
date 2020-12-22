package EnhancedTipCalculator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Bidi;
import java.text.NumberFormat;

public class TipCalculatorController {
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();
    private BigDecimal tipPercentage = new BigDecimal(0.15);

    @FXML
    private Label AmountLabel;

    @FXML
    private Label TipPercentageLabel;

    @FXML
    private Label TipLabel;

    @FXML
    private Label TotalLabel;

    @FXML
    private TextField AmountTextField;

    @FXML
    private Slider TipPercentageSlider;

    @FXML
    private TextField TipTextField;

    @FXML
    private TextField TotalTextField;

    @FXML
    private Button calculateButton;

    @FXML
    void calculateButtonPressed(ActionEvent event) {
        try{
            BigDecimal amount = new BigDecimal(AmountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            TipTextField.setText(currency.format(tip));
            TotalTextField.setText(currency.format(total));
        }catch (NumberFormatException e ){
            AmountTextField.setText("Enter amount");
            AmountTextField.selectAll();
            AmountTextField.requestFocus();
        }

    }

    public void initialize(){
        currency.setRoundingMode(RoundingMode.HALF_UP);
        TipPercentageSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                        tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
                        TipPercentageLabel.setText(percent.format(tipPercentage));
                    }
                }
        );
    }
}
