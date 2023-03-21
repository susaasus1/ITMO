package graphica;

import Exceptions.FiledIncorrect;
import aplicattion.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.net.URL;
import java.util.ResourceBundle;

public class UpdateController implements Initializable{

    public TextField idField;
    public TextField nameField;
    public TextField coord_xField;
    public TextField coord_yField;
    public TextField heightField;
    public TextField healthField;
    public TextField chap_worldField;
    public TextField chap_nameField;
    public TextField chap_parentlegionField;
    public TextField chap_marinescountField;
    public TextField userField;
    public ComboBox categoryBox;
    public ComboBox weaponBox;
    public Button readyButton;


    public void upd(ActionEvent actionEvent) throws InterruptedException, FiledIncorrect {
        String upd = "update " + idField.getText() + "\n"+nameField.getText() + "\n" + coord_xField.getText() + "\n" + coord_yField.getText() + "\n" +
                healthField.getText() + "\n" + heightField.getText() + "\n" + categoryBox.getValue().toString() + "\n" +
                weaponBox.getValue().toString() + "\n" + chap_nameField.getText() + "\n" + chap_parentlegionField.getText() + "\n" + chap_marinescountField.getText() + "\n" + chap_worldField.getText();
        MainApp.client.executeCommand(upd);
        MainApp.mainController.waitForAnswer();

        MainApp.mainController.outputField.setText(MainApp.answerLine);
        Stage stage = (Stage) readyButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userField.setText(MainApp.username);
        userField.setEditable(false);
        readyButton.setText(AuthController.mess.update());
        ObservableList<String> category = FXCollections.observableArrayList("DREADNOUGHT", "INCEPTOR", "LIBRARIAN", "CHAPLAIN","HELIX");
        categoryBox.setItems(category);
        categoryBox.setValue("DREADNOUGHT");

        ObservableList<String> weapon = FXCollections.observableArrayList("COMBI_FLAMER", "GRENADE_LAUNCHER", "HEAVY_FLAMER","MULTI_MELTA");
        weaponBox.setItems(weapon);
        weaponBox.setValue("COMBI_FLAMER");

    }
}