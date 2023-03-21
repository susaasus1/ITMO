package graphica;

import Exceptions.FiledIncorrect;
import aplicattion.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ShowController implements Initializable {

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
    public Button updateButton;
    public Button removeButton;
    public TextField dateField;
    public static int xxx;
    public static int yyy;
    public static int height;
    public static String log;
    public static boolean t;
    public static boolean r;
    public void show(ActionEvent actionEvent) {
        Stage stage = (Stage) readyButton.getScene().getWindow();
        stage.close();
    }

    public void upda(ActionEvent actionEvent) throws InterruptedException, FiledIncorrect {
        String upd = "update " + idField.getText() + "\n" + nameField.getText() + "\n" + coord_xField.getText() + "\n" + coord_yField.getText() + "\n" +
                healthField.getText() + "\n" + heightField.getText() + "\n" + categoryBox.getValue().toString() + "\n" +
                weaponBox.getValue().toString() + "\n" + chap_nameField.getText() + "\n" + chap_parentlegionField.getText() + "\n" + chap_marinescountField.getText() + "\n" + chap_worldField.getText();
        MainApp.client.executeCommand(upd);
        xxx = (int) Double.parseDouble(coord_xField.getText());
        yyy = Integer.parseInt(coord_yField.getText());
        height = (int) Double.parseDouble(heightField.getText());
        log = userField.getText();
        if (MainApp.username.equals(userField.getText())) {
            t = true;
        }
        Thread.sleep(300);
        MainApp.mainController.outputField.setText(MainApp.answerLine);
        Stage stage = (Stage) readyButton.getScene().getWindow();
        stage.close();
    }

    public void remove(ActionEvent actionEvent) throws InterruptedException, FiledIncorrect {
        String upd = "remove_by_id " + idField.getText();
        xxx = (int) Double.parseDouble(coord_xField.getText());
        yyy = Integer.parseInt(coord_yField.getText());
        height = (int) Double.parseDouble(heightField.getText());
        log = userField.getText();
        MainApp.client.executeCommand(upd);
        if (MainApp.username.equals(userField.getText())) {
            r = true;
        }
        Thread.sleep(300);
        MainApp.mainController.outputField.setText(MainApp.answerLine);
        Stage stage = (Stage) readyButton.getScene().getWindow();
        stage.close();
    }

    public void initialize(URL location, ResourceBundle resources) {
        readyButton.setText(AuthController.mess.ready());
        removeButton.setText(AuthController.mess.delete());
        updateButton.setText(AuthController.mess.update());
        idField.setEditable(false);
        idField.setText(String.valueOf(MainApp.person.getId()));
        userField.setEditable(false);
        userField.setText(MainApp.person.getOwner().getLogin());
        nameField.setText(MainApp.person.getName());
        coord_xField.setText(String.valueOf(MainApp.person.getCoordinates().getX()));
        coord_yField.setText(String.valueOf(MainApp.person.getCoordinates().getY()));
        heightField.setText(String.valueOf(MainApp.person.getHeight()));
        healthField.setText(String.valueOf(MainApp.person.getHealth()));
        chap_nameField.setText(MainApp.person.getChapter().getName());
        chap_parentlegionField.setText(MainApp.person.getChapter().getParentLegion());
        chap_marinescountField.setText(String.valueOf(MainApp.person.getChapter().getMarinesCount()));
        chap_worldField.setText(MainApp.person.getChapter().getWorld());
        ObservableList<String> category = FXCollections.observableArrayList("DREADNOUGHT", "INCEPTOR", "LIBRARIAN", "CHAPLAIN", "HELIX");
        ObservableList<String> weapon = FXCollections.observableArrayList("COMBI_FLAMER", "GRENADE_LAUNCHER", "HEAVY_FLAMER", "MULTI_MELTA");
        weaponBox.setItems(weapon);
        categoryBox.setItems(category);
        weaponBox.setValue(MainApp.person.getWeaponType().toString());
        categoryBox.setValue(MainApp.person.getCategory().toString());
        dateField.setEditable(false);
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String str = formatterDate.format(LocalDate.parse(MainApp.person.getCreationDate().toString()));
        dateField.setText(str);


    }
}