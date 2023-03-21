package graphica;

import Data.AstartesCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;



import java.net.URL;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {
    public ComboBox categoryBox;
    public Button readyButton;

    public void send (ActionEvent actionEvent){
        MainController.cate= AstartesCategory.valueOf(categoryBox.getValue().toString());
        Stage stage = (Stage) readyButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> category = FXCollections.observableArrayList("DREADNOUGHT", "INCEPTOR", "LIBRARIAN", "CHAPLAIN","HELIX");
        categoryBox.setItems(category);
        categoryBox.setValue("DREADNOUGHT");
    }
}
