package modernclient;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import modernclient.model.Person;
import modernclient.model.SampleData;

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
    @FXML    private MenuItem exitID;
    @FXML    private MenuItem aboutID;
    @FXML    private DatePicker birthDateBox;
    @FXML    private ComboBox<String> genderComboBox;
    private ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female");

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
                "Last Edited: 07/03/2021 \nGulMeeri Irfan\ngulmeeri.irfan_2021");
        informationAlert.showAndWait();
    }

    private final ObservableList<Person> personList = FXCollections.observableArrayList(Person.extractor);
    // Observable objects returned by extractor (applied to each list element) are listened for changes and
    // transformed into "update" change of ListChangeListener.

    private Person selectedPerson;
    private final BooleanProperty modifiedProperty = new SimpleBooleanProperty(false);
    private ChangeListener<Person> personChangeListener;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Disable the Remove/Edit buttons if nothing is selected in the ListView control
        removeButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull());
        updateButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull()
                .or(modifiedProperty.not()).or(firstnameTextField.textProperty().isEmpty().or(lastnameTextField.textProperty().isEmpty())
                ));
        createButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNotNull()
                .or(firstnameTextField.textProperty().isEmpty().or(lastnameTextField.textProperty().isEmpty())));

        SampleData.fillSampleData(personList);
        //for MenuItem Exit
        exitID.setOnAction(e -> Platform.exit());
        exitID.setAccelerator(new KeyCharacterCombination(String.valueOf(KeyCode.X), KeyCombination.SHORTCUT_DOWN));

        //for setting gender items
        genderComboBox.setItems(genderList);

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
            modifiedProperty.set(false);

            if (newValue != null) {
                // Populate controls with selected Person
                firstnameTextField.setText(selectedPerson.getFirstname());
                lastnameTextField.setText(selectedPerson.getLastname());
                notesTextArea.setText(selectedPerson.getNotes());
                genderComboBox.setValue(selectedPerson.getGender());

            } else {
                firstnameTextField.setText("");
                lastnameTextField.setText("");
                notesTextArea.setText("");
                genderComboBox.setValue("");
            }
        });

        // Pre-select the first item
        listView.getSelectionModel().selectFirst();

    }

    @FXML
    private void handleKeyAction(KeyEvent keyEvent) {
        modifiedProperty.set(true);
    }

    @FXML
    private void createButtonAction(ActionEvent actionEvent) {
        System.out.println("Create");
        Person person = new Person(firstnameTextField.getText(), lastnameTextField.getText(), notesTextArea.getText(),
                genderComboBox.getValue());
        personList.add(person);
        // and select it
        listView.getSelectionModel().select(person);
    }

    @FXML
    private void removeButtonAction(ActionEvent actionEvent) {
        System.out.println("Remove " + selectedPerson);
        personList.remove(selectedPerson);
    }

    @FXML
    private void updateButtonAction(ActionEvent actionEvent) {
        System.out.println("Update " + selectedPerson);
        Person p = listView.getSelectionModel().getSelectedItem();
        listView.getSelectionModel().selectedItemProperty().removeListener(personChangeListener);
        p.setFirstname(firstnameTextField.getText());
        p.setLastname(lastnameTextField.getText());
        p.setNotes(notesTextArea.getText());
        p.setGender(genderComboBox.getValue());
        listView.getSelectionModel().selectedItemProperty().addListener(personChangeListener);
        modifiedProperty.set(false);
    }

}
