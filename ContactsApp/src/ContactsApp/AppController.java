package ContactsApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AppController {

    private final ObservableList<MyContacts> myContacts = FXCollections.observableArrayList();
    private MyContacts c;

    @FXML private ListView<MyContacts> contactsListView;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField cellNumberTextField;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;

    public void initialize() {
        myContacts.add(new MyContacts("Emma","Stone",
                "Stone.emma@gmail.com","4567899"));
        myContacts.add(new MyContacts("Sang Won","Lee",
                "Lee89@gmail.com","4566699"));
        contactsListView.setItems(myContacts);

        contactsListView.getSelectionModel().selectedItemProperty().addListener((arg0,oV,nV)-> {
            c = nV;
            firstNameTextField.setText(nV.getFirstName());
            lastNameTextField.setText(nV.getLastName());
            emailTextField.setText(nV.getEmail());
            cellNumberTextField.setText(nV.getPhoneNumber());
        });

    }

    @FXML
    void addContactOnPressed(ActionEvent event) {
        MyContacts newContact = new MyContacts();
        newContact.setFirstName(firstNameTextField.getText().trim());
        newContact.setLastName(lastNameTextField.getText().trim());
        newContact.setEmail(emailTextField.getText().trim());
        newContact.setPhoneNumber(cellNumberTextField.getText().trim());
        myContacts.add(newContact);
    }

    @FXML
    void deleteContactOnPressed(ActionEvent event) {
        myContacts.remove(c);
    }

    @FXML
    void updateContactOnPressed(ActionEvent event) {
        c.setFirstName(firstNameTextField.getText().trim());
        c.setLastName(lastNameTextField.getText().trim());
        c.setEmail(emailTextField.getText().trim());
        c.setPhoneNumber(cellNumberTextField.getText().trim());
    }

    @FXML
    void clearOnPressed(ActionEvent event){
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        emailTextField.setText("");
        cellNumberTextField.setText("");
    }

}
