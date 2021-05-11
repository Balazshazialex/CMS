package sample;

import Controllers.UserController;
import Model.Conference;
import Model.ConferenceParticipant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    @FXML
    public Button UpdateDetailsButton;
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
        this.id.setEditable(false);
        this.role.setText(this.c.getRole());
        this.role.setEditable(false);
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

    public void updatedetails(ActionEvent actionEvent) {
        var name=this.nameTextField.getText();
        var hpf=this.hpfee.getText();
        var card=this.cardnumber.getText();
        var webpage=this.webpage.getText();
        var role=this.role.getText();
        var affiliation=this.affiliation.getText();
        var id=this.id.getText();
        var username=this.c.getUsername();
        var pass=this.c.getPassword();
        ConferenceParticipant conf= new ConferenceParticipant(Integer.valueOf(id),name,username,pass,Boolean.parseBoolean(hpf),card,affiliation,webpage,role);
        this.userController.update(conf);
        this.c=this.userController.findOne(this.c.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Update completeed");
        alert.setContentText("I have updated your details");

        alert.showAndWait();
    }
}
