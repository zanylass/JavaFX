///Author: Gul Meeri///

package BMI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BMICalculatorController {

    @FXML
    private TextField weightTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private TextField bmiTextField;

    @FXML
    private TextArea resultTextArea;

    @FXML
    void onCalculateButtonPressed(ActionEvent event) {
        if(weightTextField.getText().trim().isEmpty() ||
        heightTextField.getText().trim().isEmpty()){
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setContentText("Please enter correct weight and height");
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }else{
            float w = Float.parseFloat(weightTextField.getText());
            float h = Float.parseFloat(heightTextField.getText());
            float bmi = w/(h*h);
            bmiTextField.setText(String.valueOf(bmi));

            if(bmi<=18.5){
                resultTextArea.setText("Underweight");
            }else if(bmi>18.5 && bmi<=24.9){
                resultTextArea.setText("Normal");
            }else if(bmi>25 && bmi<=29.9){
                resultTextArea.setText("Overweight");
            }else{
                resultTextArea.setText("Obese");
            }
        }

    }

}
