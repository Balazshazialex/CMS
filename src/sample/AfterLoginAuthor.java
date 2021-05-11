package sample;

import Controllers.ConferenceController;
import Controllers.UserController;
import Model.Conference;
import Model.ConferenceParticipant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoginAuthor implements Initializable {
    private ConferenceController conferenceController;
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
    @FXML
    public TableView conf_table;
    private ConferenceParticipant c;
    UserController userController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.userController = new UserController();
        this.conferenceController = new ConferenceController();
        this.addcols();
        populateConferencesList();
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


    private void addcols(){
        TableColumn id = new TableColumn("ID");
        id.setPrefWidth(50);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setPrefWidth(125.00);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn startDateColumn = new TableColumn("Start date");
        startDateColumn.setPrefWidth(150);
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn endDateColumn = new TableColumn("End date");
        endDateColumn.setPrefWidth(150);
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        TableColumn callColumn = new TableColumn("Call for papers");
        callColumn.setPrefWidth(150);
        callColumn.setCellValueFactory(new PropertyValueFactory<>("callForPapers"));

        TableColumn proposalDeadlineColumn = new TableColumn("Proposal deadline");
        proposalDeadlineColumn.setPrefWidth(150);
        proposalDeadlineColumn.setCellValueFactory(new PropertyValueFactory<>("proposalDeadline"));

        TableColumn fullDeadlineColumn = new TableColumn("Full paper deadline");
        fullDeadlineColumn.setPrefWidth(150);
        fullDeadlineColumn.setCellValueFactory(new PropertyValueFactory<>("fullpaperDeadline"));

        TableColumn phaseColumn = new TableColumn("Phase");
        phaseColumn.setPrefWidth(75.00);
        phaseColumn.setCellValueFactory(new PropertyValueFactory<>("phase"));

        this.conf_table.getColumns().removeAll();
        this.conf_table.getColumns().addAll(id,nameColumn, startDateColumn,endDateColumn,callColumn,
                proposalDeadlineColumn,fullDeadlineColumn,phaseColumn);
    }
    private void populateConferencesList() {

        this.conf_table.getItems().clear();
        for (Conference conference : this.conferenceController.findAll()) {
            this.conf_table.getItems().add(conference);
        }
    }

    public void enterconference(ActionEvent actionEvent) {
        int  index = this.conf_table.getSelectionModel().getSelectedIndex();
        Conference conference= (Conference) this.conf_table.getItems().get(index);
        String nextScreen = "/sample/Conference.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nextScreen));
        try {
            Parent parent = loader.load();
            sample.ConferenceController scene2Controller = loader.getController();
            scene2Controller.send_message(conference);
            this.conf_table.getScene().setRoot(parent);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
