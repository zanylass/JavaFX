package modernclient.model;

import javafx.collections.ObservableList;

import java.time.LocalDate;

public class SampleData {
    public static void fillSampleData(ObservableList<Person> backingList) {
        backingList.add(new Person("Waldo", "Soller", "random notes 1", "Male",
                LocalDate.of(2001,01,21), "modernclient/contactImages/img2.jpg"));
        backingList.add(new Person("Herb", "Dinapoli", "random notes 2", "Male"
                , LocalDate.of(2001,02,24), "modernclient/contactImages/img2.jpg"));
        backingList.add(new Person("Shawanna", "Goehring", "random notes 3", "Female"
                , LocalDate.of(2000,03,27), "modernclient/contactImages/img1.jpg"));
        backingList.add(new Person("Flossie", "Goehring", "random notes 4", "Female"
                , LocalDate.of(2002,04,30), "modernclient/contactImages/img3.jpg"));
        backingList.add(new Person("Magdalen", "Meadors", "random notes 5", "Female"
                , LocalDate.of(2002,05,19), "modernclient/contactImages/img1.jpg"));
        backingList.add(new Person("Marylou", "Berube", "random notes 6", "Female"
                , LocalDate.of(2000,06,16), "modernclient/contactImages/img1.jpg"));
        backingList.add(new Person("Ethan", "Nieto", "random notes 7", "Male"
                , LocalDate.of(2004,07,8), "modernclient/contactImages/img4.jpg"));
        backingList.add(new Person("Elli", "Combes", "random notes 8", "Female"
                , LocalDate.of(2002,02,1), "modernclient/contactImages/img3.jpg"));
        backingList.add(new Person("Andy", "Toupin", "random notes 9", "Male"
                , LocalDate.of(2003,03,14), "modernclient/contactImages/img4.jpg"));
        backingList.add(new Person("Zenia", "Linwood", "random notes 10", "Female"
                , LocalDate.of(2003,04,23), "modernclient/contactImages/img3.jpg"));
    }

    /*
    Glenn Marti
    Waldo Soller
    Herb Dinapoli
    Shawanna Goehring
    Flossie Slack
    Magdalen Meadors
    Marylou Berube
    Ethan Nieto
    Elli Combes
    Andy Toupin
    Zenia Linwood
    Alan Mckeithan
    Kattie Mellott
    Benito Kearns
    Lloyd Cundiff
    Karleen Westrich
    Jada Perrotta
    Teofila Holbert
    Moira Heart
    Mitsuko Earwood
     */
}
