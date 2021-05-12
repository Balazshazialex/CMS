package sample;

import Model.Conference;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ConferenceController implements Initializable {
    private Conference conf;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void send_message(Conference c)
    {
        this.conf=c;
        System.out.println(conf);
    }
}