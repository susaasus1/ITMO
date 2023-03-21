package graphica;

import aplicattion.MainApp;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import language.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthController extends Thread implements Initializable {
    @FXML
    private Button authButton;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField = new PasswordField();
    @FXML
    private Button registerButton;
    @FXML
    private ComboBox languageBox;
    @FXML
    private Label authLabel;
    public static Stage stage = new Stage();
    public static String lang;
    public static String language;
    public static Messenger mess = new RuMessenger();

    public void auth(ActionEvent actionEvent) {
        if (!loginField.getText().equals("") && !passwordField.getText().equals("")) {
            MainApp.client.executeCommand("auth\n" + loginField.getText() + "\n" + passwordField.getText());
            waitForAnswer();
            if (!MainApp.answerLine.equals("Пользователь с таким логином или паролем не найден") && !MainApp.answerLine.equals("Сервер не доступен") && !MainApp.answerLine.equals("Не удалось получить ответ")) {
                System.out.println(MainApp.answerLine);
                MainApp.username = loginField.getText();
                MainApp.pass = passwordField.getText();
                authButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/graphica/main.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    stage.setTitle("Лаба8");
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/nek.png")));
                    stage.setScene(new Scene(root));
                    stage.setFullScreen(true);
                    stage.setFullScreenExitHint("");
                    stage.setResizable(false);
                    stage.sizeToScene();
                    stage.setOnCloseRequest(t -> System.exit(0));
                    stage.showAndWait();
                    this.interrupt();
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.setTitle("Error");
                    alert.setHeaderText("Auth error");
                    alert.setContentText("Не удалось запустить основное окно");
                    alert.showAndWait().ifPresent(rs -> {
                    });
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setTitle("Error");
                alert.setHeaderText("Auth error");
                alert.setContentText(MainApp.answerLine);
                alert.showAndWait().ifPresent(rs -> {
                });
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.WINDOW_MODAL);
            alert.initStyle(StageStyle.DECORATED);
            alert.setTitle("Error");
            alert.setHeaderText("Auth error");
            alert.setContentText("Заполните все поля!");
            alert.showAndWait().ifPresent(rs -> {
            });
        }


    }

    public void register(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        if (!loginField.getText().equals("") && !passwordField.getText().equals("")) {
            MainApp.client.executeCommand("register\n" + loginField.getText() + "\n" + passwordField.getText());
            waitForAnswer();
            MainApp.username = loginField.getText();
            MainApp.pass = passwordField.getText();
            MainApp.client.executeCommand("auth\n" + loginField.getText() + "\n" + passwordField.getText());
            waitForAnswer();
            registerButton.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("/graphica/main.fxml"));
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/graphica/main.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
                this.interrupt();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void setLang(ActionEvent event) {
        lang = languageBox.getValue().toString();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDaemon(true);
        ObservableList<String> category = FXCollections.observableArrayList("RUS", "SRP", "ALB", "SPA");
        languageBox.setItems(category);
        languageBox.setValue("RUS");
        lang = "RUS";
        authLabel.setText(mess.autorization());
        this.start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            if (lang.equals("RUS")) {
                mess = new RuMessenger();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        authLabel.setText(mess.autorization());
                        loginField.setPromptText(mess.askLogin());
                        passwordField.setPromptText(mess.askPassword());
                        authButton.setText(mess.autorization());
                        registerButton.setText(mess.registration());
                    }
                });
                language=lang;
                lang = "";
            }
            if (lang.equals("SRP")) {
                mess = new SerMessenger();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        authLabel.setText(mess.autorization());
                        loginField.setPromptText(mess.askLogin());
                        passwordField.setPromptText(mess.askPassword());
                        authButton.setText(mess.autorization());
                        registerButton.setText(mess.registration());
                    }
                });
                language=lang;
                lang = "";
            }
            if (lang.equals("ALB")) {
                mess = new AlbMessenger();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        authLabel.setText(mess.autorization());
                        loginField.setPromptText(mess.askLogin());
                        passwordField.setPromptText(mess.askPassword());
                        authButton.setText(mess.autorization());
                        registerButton.setText(mess.registration());
                    }
                });
                language=lang;
                lang = "";
            }
            if (lang.equals("SPA")) {
                mess = new SpaMessenger();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        authLabel.setText(mess.autorization());
                        loginField.setPromptText(mess.askLogin());
                        passwordField.setPromptText(mess.askPassword());
                        authButton.setText(mess.autorization());
                        registerButton.setText(mess.registration());
                    }
                });
                language=lang;
                lang = "";
            }

        }
    }

    public void waitForAnswer() {
        try {
            this.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
