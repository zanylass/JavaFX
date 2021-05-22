package TypingApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class TypingApp extends Application {
    private long startTime = 0;
    private double elapsedTime = 0;
    private int correctWord;


    String[] words = new String[] {
            "word", "envelope", "sentence", "selection","hamilton",
            "selector", "cantaloupe", "Bellingcat", "hydrophobic", "hydrophilic",
            "compile", "chromosome", "intensity", "heterogeneous", "homogeneous",
            "integrated", "development", "environment", "monstrosity", "transplantation"
    };

    Random rand = new Random();
    private String getNextWords() {
        String p = "";
        for(int i=0;i<10;i++){
            p += words[rand.nextInt(19)];
            p += " ";
        }
        startTime = System.nanoTime();
        return p;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        SentenceSelector selector = new SentenceSelector();

        Label WPM = new Label();
        WPM.setText("Press enter after typing to find out the WPM");
        WPM.setStyle("-fx-font-size: 16" + ";" + "-fx-text-fill: blueviolet");

        Label p = new Label();
        p.setText("Correct word or not!");
        p.setStyle("-fx-font-size: 16");

        //to check if the method is working
        //System.out.println(getNextWords());
        TextArea exampleText = new TextArea();
        exampleText.setMaxHeight(100);
        exampleText.setMaxWidth(460);
        exampleText.setEditable(false);
        exampleText.setText(getNextWords());
        exampleText.setWrapText(true);
        exampleText.setStyle("-fx-font-size: 16");
        String exText = exampleText.getText();
        String[] ex = exText.split("\\s");
        int l = exText.length();

        TextArea typingText = new TextArea();
        typingText.setMaxHeight(100);
        typingText.setMaxWidth(460);
        typingText.setWrapText(true);
        typingText.setStyle("-fx-font-size: 16");
        typingText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                //System.out.println(keyEvent.getCode());

                if(keyEvent.getCode().equals(KeyCode.SPACE)){
                    String[] test = typingText.getText().split("\\s");
                    if(Arrays.equals(test, Arrays.copyOfRange(ex, 0, test.length))){
                        //System.out.print("i");
                        int wlen = 0;
                        for(int i=0;i<test.length;i++){
                            wlen += ex[i].length();
                        }

                        typingText.selectRange(test.length + wlen, ex[test.length].length() + wlen + test.length);
                        p.setText("Correct Word! Type next word");
                        p.setStyle("-fx-font-size: 16 " + ";" + "-fx-text-fill: forestgreen");
                        correctWord++;
                        System.out.println(correctWord);
                    }
                    else{
                        System.out.println("ERROR! type word again!");
                        p.setText("Error!! Incorrect Word! Type Word again!");
                        p.setStyle("-fx-font-size: 16 " + ";" + " -fx-text-fill: firebrick");
                    }
                }

                if(keyEvent.getCode().equals(KeyCode.ENTER)){
                    elapsedTime = (System.nanoTime() - startTime) /1000000000.0;
                    int wpm = (int) ((((double) l/5) /elapsedTime)*60);
                    WPM.setText("WPM: " + wpm  + "");
                }

            }
        });
        String typed = typingText.getText();

        /*Text output = new Text();
        output.setStyle("-fx-font-size: 16");
        exampleText.textProperty().bind(typingText.textProperty());*/

        Button next = new Button();
        next.setText("Get Example Text");
        next.setStyle("-fx-font-size: 16");
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                exampleText.setText(getNextWords());
                typingText.setText("");
            }
        });

        VBox vbox = new VBox(WPM, exampleText, p, typingText, next);
        vbox.setStyle("-fx-padding: 20");
        vbox.setSpacing(25);
        vbox.setFillWidth(true);
        //vbox.setAlignment(Pos.BASELINE_CENTER);
        AnchorPane root = new AnchorPane(vbox);
        primaryStage.setTitle("Typing Tester");
        primaryStage.setScene(new Scene(root, 500,430));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
