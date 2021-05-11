package sample;

import Controllers.UserController;
import Model.Conference;
import Model.ConferenceParticipant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoginAuthor implements Initializable {
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField hpfee;
    @FXML
    public TextField cardnumber;
    @FXML
    public TextField affiliation;
    @FXML
    public TextField webpage;
    @FXML
    public TextField id;
    @FXML
    public TextField role;
    @FXML
    public Button PayfeeButton;
    private ConferenceParticipant c;
    UserController userController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.userController = new UserController();
    }

    private void fillinpersonaldata() {
        this.nameTextField.setText(this.c.getName());
        this.hpfee.setText(String.valueOf(this.c.isHasPayedFee()));
        this.hpfee.setEditable(false);
        this.cardnumber.setText(this.c.getCardNumber());
        this.webpage.setText(this.c.getWebPage());
        this.affiliation.setText(this.c.getAffiliation());
        this.id.setText(String.valueOf(this.c.getId()));
        this.role.setText(this.c.getRole());
        hide_fee();
    }

    public void send_message(ConferenceParticipant c1){
        this.c=c1;
        fillinpersonaldata();
    }
    public void hide_fee(){
        if(this.c.isHasPayedFee()){
            this.PayfeeButton.setVisible(false);
        }
    }
    public void payfee(ActionEvent actionEvent) {
        this.userController.payfee(this.c.getId());
        this.c= userController.findOne(this.c.getId());
        this.hpfee.setText(String.valueOf(this.c.isHasPayedFee()));
        this.hpfee.setEditable(false);
        this.hide_fee();
    }
}
