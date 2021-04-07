package modernclient;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import modernclient.model.Person;
import modernclient.model.SampleData;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonUIController implements Initializable {

    @FXML    private TextField firstnameTextField;
    @FXML    private TextField lastnameTextField;
    @FXML    private TextArea notesTextArea;
    @FXML    private Button removeButton;
    @FXML    private Button createButton;
    @FXML    private Button updateButton;
    @FXML    private ListView<Person> listView;
    @FXML    private DatePicker birthDate;
    @FXML    private ComboBox genderSelection;
    @FXML    private MenuItem exitID;
    @FXML    private ImageView imageView;
    @FXML    private Canvas canvas;

    @FXML private Button uploadButton;
    @FXML private AnchorPane pane;
    GraphicsContext gc;

    private final ObservableList<Person> personList = FXCollections.observableArrayList(Person.extractor);

    private Person selectedPerson;
    private final BooleanProperty modifiedProperty = new SimpleBooleanProperty(false);
    private ChangeListener<Person> personChangeListener;
    private ContextMenu contextMenuInfo;
    private String pathSaver = null;

    @FXML    private void exitClicked (ActionEvent event){
        /*exitID.setOnAction((ActionEvent t) -> {
            System.exit(0);
        });*/
        exitID.setOnAction(e -> Platform.exit());
        exitID.setAccelerator(new KeyCharacterCombination(String.valueOf(KeyCode.X), KeyCombination.SHORTCUT_DOWN));
    }

    @FXML    private void aboutClicked (ActionEvent event){
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION, "GM");
        informationAlert.setTitle("About App");
        informationAlert.setHeaderText("");
        informationAlert.setContentText("Date of App Creation: 04/03/2021\n" +
                "Last Edited: 19/03/2021 \nGulMeeri Irfan\ngulmeeri.irfan_2021");
        informationAlert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //for displaying contacts image on canvas
        gc = canvas.getGraphicsContext2D();
        // Disable the Remove/Edit buttons if nothing is selected in the ListView control
        removeButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull());
        updateButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull()
                .or(modifiedProperty.not()));
        createButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNotNull());

        SampleData.fillSampleData(personList);
        //for MenuItem Exit
        exitID.setOnAction(e -> Platform.exit());
        exitID.setAccelerator(new KeyCharacterCombination(String.valueOf(KeyCode.X), KeyCombination.SHORTCUT_DOWN));


        // Use a sorted list; sort by lastname; then by firstname
        SortedList<Person> sortedList = new SortedList<>(personList);

        // sort by lastname first, then by firstname; ignore notes
        sortedList.setComparator((p1, p2) -> {
            int result = p1.getLastname().compareToIgnoreCase(p2.getLastname());
            if (result == 0) {
                result = p1.getFirstname().compareToIgnoreCase(p2.getFirstname());
            }
            return result;
        });

        listView.setItems(sortedList);

        listView.getSelectionModel().selectedItemProperty().addListener(personChangeListener = (observable, oldValue, newValue) -> {
            System.out.println("Selected item: " + newValue);
            // newValue can be null if nothing is selected
            selectedPerson = newValue;

            // Boolean property modifiedProperty tracks whether the user has changed any of the
            //three text controls in the form. We reset this flag after each ListView selection and use
            //this property in a bind expression to control the Update button’s disable property.


            if (newValue != null) {
                //displaying circle on canvas
                gc.drawImage(new Image(selectedPerson.getImagePath(),100, 100, false, false), 0, 0);
                Circle circle = new Circle(50, 50,0);
                circle.setRadius(50);
                canvas.setClip(circle);
                // Populate controls with selected Person
                /*firstnameTextField.setText(selectedPerson.getFirstname());
                lastnameTextField.setText(selectedPerson.getLastname());
                notesTextArea.setText(selectedPerson.getNotes());
                genderComboBox.setValue(selectedPerson.getGender());*/

            } else {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                /*firstnameTextField.setText("");
                lastnameTextField.setText("");
                notesTextArea.setText("");
                genderComboBox.setValue("");*/
            }
            modifiedProperty.set(false);
        });

        // Pre-select the first item
        listView.getSelectionModel().selectFirst();

        //context menu on right click
        pane.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                if (contextMenuInfo != null) {
                    contextMenuInfo.hide();
                }
                contextMenuInfo  = new ContextMenu();
                MenuItem firstName = new MenuItem(
                        "First Name: " + listView.getSelectionModel().getSelectedItem().getFirstname());
                MenuItem lastName = new MenuItem(
                        "Last name:  " + listView.getSelectionModel().getSelectedItem().getLastname());
                MenuItem notes = new MenuItem(
                        "Notes:         " + listView.getSelectionModel().getSelectedItem().getNotes());
                MenuItem birthDate = new MenuItem(
                        "Birth Date:  " + listView.getSelectionModel().getSelectedItem().getBirthDate().toString());
                MenuItem gender = new MenuItem(
                        "Gender:      " + listView.getSelectionModel().getSelectedItem().getGender());
                contextMenuInfo.getItems().addAll(firstName, lastName, notes, birthDate, gender);
                contextMenuInfo.show(pane, event.getScreenX(), event.getScreenY());
            }
        });
    }

    @FXML
    private void handleKeyAction(KeyEvent keyEvent) {
        modifiedProperty.set(true);
    }

    @FXML
    private void createButtonAction(ActionEvent actionEvent) {
        /*System.out.println("Create");
        Person person = new Person(firstnameTextField.getText(), lastnameTextField.getText(), notesTextArea.getText(),
                genderComboBox.getValue());
        personList.add(person);
        // and select it
        listView.getSelectionModel().select(person);*/
    }

    @FXML
    private void removeButtonAction(ActionEvent actionEvent) {
        System.out.println("Remove " + selectedPerson);
        personList.remove(selectedPerson);
    }

    @FXML
    private void updateButtonAction(ActionEvent actionEvent) {
/*        System.out.println("Update " + selectedPerson);
        Person p = listView.getSelectionModel().getSelectedItem();
        listView.getSelectionModel().selectedItemProperty().removeListener(personChangeListener);
        p.setFirstname(firstnameTextField.getText());
        p.setLastname(lastnameTextField.getText());
        p.setNotes(notesTextArea.getText());
        p.setGender(genderComboBox.getValue());
        listView.getSelectionModel().selectedItemProperty().addListener(personChangeListener);
        modifiedProperty.set(false);
    */
    }

}