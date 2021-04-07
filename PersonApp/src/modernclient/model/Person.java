package modernclient.model;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

import java.time.LocalDate;

import java.util.Objects;

public class Person {
    private final StringProperty firstname = new SimpleStringProperty(this, "firstname", "");
    private final StringProperty lastname = new SimpleStringProperty(this, "lastname", "");
    private final StringProperty notes = new SimpleStringProperty(this, "notes", "sample notes");
    private String gender;
    private LocalDate bday;
    private String imgPath;

    public Person(){
    };

    public Person(String firstname, String lastname, String notes, String gender, LocalDate bday, String imgPath) {
        this.firstname.set(firstname);
        this.lastname.set(lastname);
        this.notes.set(notes);
        this.gender=gender;
        this.bday = bday;
        this.imgPath = imgPath;
    }

    //setters and getters for firstname
    public String getFirstname() {
        return firstname.get();
    }
    public StringProperty firstnameProperty() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    //setters and getters for lastname
    public String getLastname() {
        return lastname.get();
    }
    public StringProperty lastnameProperty() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    //setters and getters for notes
    public String getNotes() {
        return notes.get();
    }

    public StringProperty notesProperty() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    //setters and getters for gender
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    //setters and getters for birth day
    public void setBirthDate(LocalDate bday) {
        this.bday = bday;
    }

    public LocalDate getBirthDate() {
        return bday;
    }

    //setters and getters for image path
    public String getImagePath() {
         return imgPath;
    }

    public void setImagePath(String imgPath) {
         this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return firstname.get() + " " + lastname.get();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return Objects.equals(firstname, person.firstname) &&
                Objects.equals(lastname, person.lastname) &&
                Objects.equals(notes, person.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, notes);
    }

    public static Callback<Person, Observable[]> extractor =
            p-> new Observable[] {
                    p.lastnameProperty(), p.firstnameProperty()
            };
}
