package p2219;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.util.Duration;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

public class DigitalClockController {

    @FXML private Label timeLabel;
    @FXML private ComboBox<Integer> hourComboBox;
    @FXML private ComboBox<Integer> minuteComboBox;

    ObservableList<Integer> hourList = FXCollections.observableArrayList(
            0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23);
    ObservableList<Integer> minList = FXCollections.observableArrayList(
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
            31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45,
            46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59);

    int hour, minute, second;
    String timeString = "";
    private List<String> alarms = new ArrayList<>();

     public void initialize(){
        // Animating the clock by 1 second
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                                timeLabel.setText(simpleDateFormat.format(time.getTime()));
                            }
                        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        hourComboBox.setItems(hourList);
        minuteComboBox.setItems(minList);
    }

    @FXML void onSetAlarmPressed(ActionEvent event) {
         hour = hourComboBox.getValue();
         String h = String.format("%02d",hour);
         minute = minuteComboBox.getValue();
         String m = String.format("%02d",minute);
         second = 0;
         timeString = h + ":" + m + ":00" ;
         System.out.println("\nThe alarm is set for " + timeString);
         alarms.add(timeString);

         //printing list of Alarms:
        System.out.println("------------Updated Alarms------------");
        for(int i = 0;i<alarms.size();i++){
            System.out.println("    Alarm " + (i+1) + "        " + alarms.get(i));
            if(alarms.get(i) == timeLabel.getText()){
                System.out.println("Alarm for " + alarms.get(i) + " went off!");
            }
        }
    }


}
