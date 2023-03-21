package graphica;

import Data.AstartesCategory;
import Data.Weapon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WeaponC implements Initializable {
    public ComboBox weaponBox;
    public Button readyButton;

    public void send (ActionEvent actionEvent){
        MainController.we= Weapon.valueOf(weaponBox.getValue().toString());
        Stage stage = (Stage) readyButton.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> weapon = FXCollections.observableArrayList("COMBI_FLAMER", "GRENADE_LAUNCHER", "HEAVY_FLAMER","MULTI_MELTA");
        weaponBox.setItems(weapon);
        weaponBox.setValue("COMBI_FLAMER");
    }
}
