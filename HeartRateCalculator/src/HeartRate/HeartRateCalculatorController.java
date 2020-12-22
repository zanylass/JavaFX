package HeartRate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HeartRateCalculatorController {
    //String target = "";

    @FXML
    private TextField age;

    @FXML
    private TextField maxHR;

    @FXML
    private TextField targetHR;

    @FXML
    void onClickButton(ActionEvent event) {
        try{
            int a = Integer.parseInt(age.getText());
            int mHR = 220 - a;//gives max HR by subtracting age from 220
            int t1 = 50*mHR/100;//gives 50% of maximum HR
            int t2 = 85*mHR/100;//gives 85% of maximum HR
            //target = t1 + "-" + t2 + "(bpm)";
            maxHR.setText(String.valueOf(mHR));
            targetHR.setText(t1 + " - " + t2 );
            //targetHR.setText(target);
        } catch (NumberFormatException e) {
            age.setText("Enter age");
            age.selectAll();
            age.requestFocus();
        }

    }

}
