package TipCalcMod;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class TipCalculatorModController {

    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();
    private BigDecimal tipPercentage = new BigDecimal(0.15);
    private BigDecimal amount = new BigDecimal(0);

    @FXML private Slider tipPercentageSlider;
    @FXML private TextField amountTextField;
    @FXML private TextField tipTextField;
    @FXML private TextField totalTextField;
    @FXML private Label tipPercentageLabel;

    @FXML
    void calculate() {
            if(amount.doubleValue() >=0) {
                BigDecimal tip = amount.multiply(tipPercentage);
                BigDecimal total = amount.add(tip);

                tipTextField.setText(currency.format(tip));
                totalTextField.setText(currency.format(total));
            }else{
                tipTextField.setText("$0");
                totalTextField.setText("$0");
            }


    }

    public void initialize(){
        currency.setRoundingMode(RoundingMode.HALF_UP);
        tipPercentageSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                        tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
                        tipPercentageLabel.setText(percent.format(tipPercentage));
                        calculate();
                    }
                }
        );

        amountTextField.textProperty().addListener((args,oldValue,newValue) ->{
                try {
                    amount = new BigDecimal(newValue);
                }catch (Exception e) {
                    amountTextField.setText("Enter Number");
                    amountTextField.selectAll();
                    amountTextField.requestFocus();
                }
        });
    }

}
