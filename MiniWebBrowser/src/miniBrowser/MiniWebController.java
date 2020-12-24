<<<<<<< HEAD
package miniBrowser;public class MiniWebController {
}
=======
package miniBrowser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MiniWebController implements Initializable {

    @FXML
    private WebView web;

    @FXML
    private TextField searchBar;

    WebEngine webEngine;
    String htLink = "https://";
    String adrsLink;

    @FXML
    void goSearch(ActionEvent event) {
        adrsLink = searchBar.getText().toString();
        webEngine.load(htLink+ adrsLink);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webEngine = web.getEngine();
        webEngine.load("https://www.google.com");
    }
}
>>>>>>> d23fbd8e135fd60cf83312f484a22fc432b5fc93
