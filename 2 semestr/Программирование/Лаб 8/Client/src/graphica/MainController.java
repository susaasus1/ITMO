package graphica;


import Commands.CommandManager;
import Commands.DataBase;
import Data.*;
import Exceptions.FiledIncorrect;
import Exceptions.NotDatabaseUpdateException;
import Exceptions.SpaceMarineNotFound;
import aplicattion.MainApp;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;


import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import javafx.scene.paint.Color;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import language.AlbMessenger;
import language.RuMessenger;
import language.SerMessenger;
import language.SpaMessenger;

public class MainController extends Thread implements Initializable {

    static final int WINDOW_X = 896;
    static final int WINDOW_Y = 502;
    public static String ll;
    @FXML
    public TextField inputField;
    @FXML
    public TextArea outputField;

    public ComboBox languageBox;

    @FXML
    public Button exitButton;
    @FXML
    public Button readyButton;

    @FXML
    public TableColumn<SpaceMarine, Long> idColoumn;
    @FXML
    public TableColumn<SpaceMarine, String> nameColoumn;
    @FXML
    public TableColumn<SpaceMarine, Float> coord_xColoumn;
    @FXML
    public TableColumn<SpaceMarine, Integer> coord_yColoumn;
    @FXML
    public TableColumn<SpaceMarine, LocalDate> creatColoumn;
    @FXML
    public TableColumn<SpaceMarine, Double> heightColoumn;
    @FXML
    public TableColumn<SpaceMarine, Float> healthColoumn;
    @FXML
    public TableColumn<SpaceMarine, AstartesCategory> categoryColoumn;
    @FXML
    public TableColumn<SpaceMarine, String> chap_nameColoumn;
    @FXML
    public TableColumn<SpaceMarine, String> chap_parentColoumn;
    @FXML
    public TableColumn<SpaceMarine, Integer> chap_countColoumn;
    @FXML
    public TableColumn<SpaceMarine, String> chap_worldColoumn;
    @FXML
    public TableColumn<SpaceMarine, User> userColoumn;
    @FXML
    public TableColumn<SpaceMarine, Weapon> weaponColoumn;


    @FXML
    public Canvas canvas = new Canvas(WINDOW_X, WINDOW_Y);
    @FXML
    private Text username;
    @FXML
    public TableView<SpaceMarine> dbTable;

    public TextField idField;
    public TextField nameField;
    public TextField healthField;
    public TextField heightField;
    public TextField createField;
    public TextField coordxField;
    public TextField coordyField;
    public TextField categoryField;
    public TextField weaponField;
    public TextField chapNameField;
    public TextField chapLegField;
    public TextField chapCountField;
    public TextField chapWorldField;
    public TextField userField;
    public Tab tabV;
    public Tab tabT;
    public AutoUpdater autoUpdater;
    private List<Color> colors = new ArrayList<>();
    public static AstartesCategory cate;
    public static Weapon we;
    private String pressReady = " и нажмите Готовы";
    private ObservableList<SpaceMarine> masterData = FXCollections.observableArrayList();
    private ObservableList<SpaceMarine> masterDatas = FXCollections.observableArrayList();
    public LinkedList<SpaceMarine> persons = new LinkedList<>();
    public List<Integer> users_keys = new ArrayList<>();
    private AnimationTimer timer;
    private Timeline timeline;
    private DoubleProperty koef;
    public boolean drawUpdate;
    public boolean removeUpdate;
    public boolean addUpdate;
    public SpaceMarine spike;
    public SpaceMarine spikuchka;
    public Menu menu;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.ORANGE);
        colors.add(Color.HOTPINK);
        colors.add(Color.AQUA);
        colors.add(Color.SILVER);
        colors.add(Color.DARKBLUE);
        ObservableList<String> category = FXCollections.observableArrayList("RUS", "SRP", "ALB", "SPA");
        languageBox.setItems(category);
        languageBox.setValue(AuthController.language);
        if (AuthController.language.equals("RUS")) {
            AuthController.mess = new RuMessenger();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    tabV.setText(AuthController.mess.visual());
                    tabT.setText(AuthController.mess.table());
                    inputField.setPromptText(AuthController.mess.inputfield());
                    outputField.setPromptText(AuthController.mess.outputfield());
                    menu.setText(AuthController.mess.commandss());
                    readyButton.setText(AuthController.mess.ready());
                    exitButton.setText(AuthController.mess.exitt());
                    username.setText(AuthController.mess.userf()+" :"+MainApp.username);
                }
            });
            AuthController.language = "";
        }
        if (AuthController.language.equals("SRP")) {
            AuthController.mess = new SerMessenger();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    tabV.setText(AuthController.mess.visual());
                    tabT.setText(AuthController.mess.table());
                    inputField.setPromptText(AuthController.mess.inputfield());
                    outputField.setPromptText(AuthController.mess.outputfield());
                    menu.setText(AuthController.mess.commandss());
                    readyButton.setText(AuthController.mess.ready());
                    exitButton.setText(AuthController.mess.exitt());
                   username.setText(AuthController.mess.userf()+" :"+MainApp.username);
                }
            });
            AuthController.language = "";
        }
        if (AuthController.language.equals("ALB")) {
            AuthController.mess = new AlbMessenger();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    tabV.setText(AuthController.mess.visual());
                    tabT.setText(AuthController.mess.table());
                    inputField.setPromptText(AuthController.mess.inputfield());
                    outputField.setPromptText(AuthController.mess.outputfield());
                    menu.setText(AuthController.mess.commandss());
                    readyButton.setText(AuthController.mess.ready());
                    exitButton.setText(AuthController.mess.exitt());
                    username.setText(AuthController.mess.userf()+" :"+MainApp.username);
                }
            });
            AuthController.language = "";
        }
        if (AuthController.language.equals("SPA")) {
            AuthController.mess = new SpaMessenger();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    tabV.setText(AuthController.mess.visual());
                    tabT.setText(AuthController.mess.table());
                    inputField.setPromptText(AuthController.mess.inputfield());
                    outputField.setPromptText(AuthController.mess.outputfield());
                    menu.setText(AuthController.mess.commandss());
                    readyButton.setText(AuthController.mess.ready());
                    exitButton.setText(AuthController.mess.exitt());
                    username.setText(AuthController.mess.userf()+" :"+MainApp.username);
                }
            });
            AuthController.language = "";
        }
        autoUpdater = new AutoUpdater(this);
        autoUpdater.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MainApp.mainController = this;
        dbTable.setEditable(true);

        idColoumn.setCellValueFactory(new PropertyValueFactory<SpaceMarine, Long>("id"));

        nameColoumn.setCellValueFactory(new PropertyValueFactory<SpaceMarine, String>("name"));
        nameColoumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColoumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SpaceMarine, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SpaceMarine, String> event) {
                if (MainApp.username.equals(persons.get(event.getTablePosition().getRow()).getOwner().getLogin())) {
                    persons.get(event.getTablePosition().getRow()).setName(event.getNewValue());
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    outputField.setText("");
                    SpaceMarine spac = persons.get(event.getTablePosition().getRow());
                    MainApp.client.executeCommand("update " + spac.getId() + "\n" + spac.getName() + "\n" + spac.getX() + "\n" + spac.getY() + "\n" +
                            spac.getHealth() + "\n" + spac.getHeight() + "\n" + spac.getCategory().toString() + "\n" +
                            spac.getWeaponType().toString() + "\n" + spac.getNames() + "\n" + spac.getLegion() + "\n" + spac.getCount() + "\n" + spac.getWorld());

                } else {
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    dbTable.refresh();
                    outputField.setText("Вы не можете изменить данное поле");
                }

            }
        });

        coord_xColoumn.setCellValueFactory(new PropertyValueFactory<SpaceMarine, Float>("x"));
        coord_xColoumn.setCellFactory(TextFieldTableCell.<SpaceMarine, Float>forTableColumn(new FloatStringConverter()));
        coord_xColoumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SpaceMarine, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SpaceMarine, Float> event) {
                if (MainApp.username.equals(persons.get(event.getTablePosition().getRow()).getOwner().getLogin())) {
                    persons.get(event.getTablePosition().getRow()).setX(event.getNewValue());
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    outputField.setText("");
                    SpaceMarine spac = persons.get(event.getTablePosition().getRow());
                    MainApp.client.executeCommand("update " + spac.getId() + "\n" + spac.getName() + "\n" + spac.getX() + "\n" + spac.getY() + "\n" +
                            spac.getHealth() + "\n" + spac.getHeight() + "\n" + spac.getCategory().toString() + "\n" +
                            spac.getWeaponType().toString() + "\n" + spac.getNames() + "\n" + spac.getLegion() + "\n" + spac.getCount() + "\n" + spac.getWorld());

                } else {
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    dbTable.refresh();
                    outputField.setText("Вы не можете изменить данное поле");
                }
            }
        });

        coord_yColoumn.setCellValueFactory(new PropertyValueFactory<SpaceMarine, Integer>("y"));
        coord_yColoumn.setCellFactory(TextFieldTableCell.<SpaceMarine, Integer>forTableColumn(new IntegerStringConverter()));
        coord_yColoumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SpaceMarine, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SpaceMarine, Integer> event) {
                if (MainApp.username.equals(persons.get(event.getTablePosition().getRow()).getOwner().getLogin())) {
                    persons.get(event.getTablePosition().getRow()).setY(event.getNewValue());
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    outputField.setText("");
                    SpaceMarine spac = persons.get(event.getTablePosition().getRow());
                    MainApp.client.executeCommand("update " + spac.getId() + "\n" + spac.getName() + "\n" + spac.getX() + "\n" + spac.getY() + "\n" +
                            spac.getHealth() + "\n" + spac.getHeight() + "\n" + spac.getCategory().toString() + "\n" +
                            spac.getWeaponType().toString() + "\n" + spac.getNames() + "\n" + spac.getLegion() + "\n" + spac.getCount() + "\n" + spac.getWorld());
                } else {
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    dbTable.refresh();
                    outputField.setText("Вы не можете изменить данное поле");
                }
            }
        });

        creatColoumn.setCellValueFactory(new PropertyValueFactory<SpaceMarine, LocalDate>("creationDate"));
        heightColoumn.setCellValueFactory(new PropertyValueFactory<SpaceMarine, Double>("height"));
        heightColoumn.setCellFactory(TextFieldTableCell.<SpaceMarine, Double>forTableColumn(new DoubleStringConverter()));
        heightColoumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SpaceMarine, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SpaceMarine, Double> event) {
                if (MainApp.username.equals(persons.get(event.getTablePosition().getRow()).getOwner().getLogin())) {
                    persons.get(event.getTablePosition().getRow()).setHeight(event.getNewValue());
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    outputField.setText("");
                    SpaceMarine spac = persons.get(event.getTablePosition().getRow());
                    MainApp.client.executeCommand("update " + spac.getId() + "\n" + spac.getName() + "\n" + spac.getX() + "\n" + spac.getY() + "\n" +
                            spac.getHealth() + "\n" + spac.getHeight() + "\n" + spac.getCategory().toString() + "\n" +
                            spac.getWeaponType().toString() + "\n" + spac.getNames() + "\n" + spac.getLegion() + "\n" + spac.getCount() + "\n" + spac.getWorld());
                } else {
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    dbTable.refresh();
                    outputField.setText("Вы не можете изменить данное поле");
                }
            }
        });
        healthColoumn.setCellValueFactory(new PropertyValueFactory<SpaceMarine, Float>("health"));
        healthColoumn.setCellFactory(TextFieldTableCell.<SpaceMarine, Float>forTableColumn(new FloatStringConverter()));
        healthColoumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SpaceMarine, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SpaceMarine, Float> event) {
                if (MainApp.username.equals(persons.get(event.getTablePosition().getRow()).getOwner().getLogin())) {
                    persons.get(event.getTablePosition().getRow()).setHealth(event.getNewValue());
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    outputField.setText("");
                    SpaceMarine spac = persons.get(event.getTablePosition().getRow());
                    MainApp.client.executeCommand("update " + spac.getId() + "\n" + spac.getName() + "\n" + spac.getX() + "\n" + spac.getY() + "\n" +
                            spac.getHealth() + "\n" + spac.getHeight() + "\n" + spac.getCategory().toString() + "\n" +
                            spac.getWeaponType().toString() + "\n" + spac.getNames() + "\n" + spac.getLegion() + "\n" + spac.getCount() + "\n" + spac.getWorld());
                } else {
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    dbTable.refresh();
                    outputField.setText("Вы не можете изменить данное поле");
                }
            }
        });
        categoryColoumn.setCellValueFactory(new PropertyValueFactory<SpaceMarine, AstartesCategory>("category"));
        categoryColoumn.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<SpaceMarine, AstartesCategory>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SpaceMarine, AstartesCategory> event) {
                if (MainApp.username.equals(persons.get(event.getTablePosition().getRow()).getOwner().getLogin())) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/graphica/category.fxml"));
                    Parent root;
                    try {
                        Stage stage = new Stage();
                        root = loader.load();
                        stage.setTitle("AstartesCategory");
                        stage.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/nek.png")));
                        stage.setScene(new Scene(root));
                        stage.setResizable(false);
                        stage.sizeToScene();
                        stage.initOwner(AuthController.stage);
                        stage.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    persons.get(event.getTablePosition().getRow()).setCategory(cate);
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    outputField.setText("");
                    SpaceMarine spac = persons.get(event.getTablePosition().getRow());
                    MainApp.client.executeCommand("update " + spac.getId() + "\n" + spac.getName() + "\n" + spac.getX() + "\n" + spac.getY() + "\n" +
                            spac.getHealth() + "\n" + spac.getHeight() + "\n" + spac.getCategory().toString() + "\n" +
                            spac.getWeaponType().toString() + "\n" + spac.getNames() + "\n" + spac.getLegion() + "\n" + spac.getCount() + "\n" + spac.getWorld());
                } else {
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    dbTable.refresh();
                    outputField.setText("Вы не можете изменить данное поле");
                }

            }
        });
        weaponColoumn.setCellValueFactory(new PropertyValueFactory<SpaceMarine, Weapon>("weaponType"));
        weaponColoumn.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<SpaceMarine, Weapon>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SpaceMarine, Weapon> event) {
                if (MainApp.username.equals(persons.get(event.getTablePosition().getRow()).getOwner().getLogin())) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/graphica/weapon.fxml"));
                    Parent root;
                    int k = persons.size();
                    try {
                        Stage stage = new Stage();
                        root = loader.load();
                        stage.setTitle("Weapon");
                        stage.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/nek.png")));
                        stage.setScene(new Scene(root));
                        stage.setResizable(false);
                        stage.sizeToScene();
                        stage.initOwner(AuthController.stage);
                        stage.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    persons.get(event.getTablePosition().getRow()).setWeaponType(we);
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    outputField.setText("");
                    SpaceMarine spac = persons.get(event.getTablePosition().getRow());
                    MainApp.client.executeCommand("update " + spac.getId() + "\n" + spac.getName() + "\n" + spac.getX() + "\n" + spac.getY() + "\n" +
                            spac.getHealth() + "\n" + spac.getHeight() + "\n" + spac.getCategory().toString() + "\n" +
                            spac.getWeaponType().toString() + "\n" + spac.getNames() + "\n" + spac.getLegion() + "\n" + spac.getCount() + "\n" + spac.getWorld());
                } else {
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    dbTable.refresh();
                    outputField.setText("Вы не можете изменить данное поле");
                }

            }
        });
        chap_nameColoumn.setCellValueFactory(new PropertyValueFactory<SpaceMarine, String>("names"));
        chap_nameColoumn.setCellFactory(TextFieldTableCell.forTableColumn());
        chap_nameColoumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SpaceMarine, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SpaceMarine, String> event) {
                if (MainApp.username.equals(persons.get(event.getTablePosition().getRow()).getOwner().getLogin())) {
                    persons.get(event.getTablePosition().getRow()).setNames(event.getNewValue());
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    outputField.setText("");
                    SpaceMarine spac = persons.get(event.getTablePosition().getRow());
                    MainApp.client.executeCommand("update " + spac.getId() + "\n" + spac.getName() + "\n" + spac.getX() + "\n" + spac.getY() + "\n" +
                            spac.getHealth() + "\n" + spac.getHeight() + "\n" + spac.getCategory().toString() + "\n" +
                            spac.getWeaponType().toString() + "\n" + spac.getNames() + "\n" + spac.getLegion() + "\n" + spac.getCount() + "\n" + spac.getWorld());
                } else {
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    dbTable.refresh();
                    outputField.setText("Вы не можете изменить данное поле");
                }
            }
        });
        chap_parentColoumn.setCellValueFactory(new PropertyValueFactory<SpaceMarine, String>("legion"));
        chap_parentColoumn.setCellFactory(TextFieldTableCell.forTableColumn());
        chap_parentColoumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SpaceMarine, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SpaceMarine, String> event) {
                if (MainApp.username.equals(persons.get(event.getTablePosition().getRow()).getOwner().getLogin())) {
                    persons.get(event.getTablePosition().getRow()).setLegion(event.getNewValue());
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    outputField.setText("");
                    SpaceMarine spac = persons.get(event.getTablePosition().getRow());
                    MainApp.client.executeCommand("update " + spac.getId() + "\n" + spac.getName() + "\n" + spac.getX() + "\n" + spac.getY() + "\n" +
                            spac.getHealth() + "\n" + spac.getHeight() + "\n" + spac.getCategory().toString() + "\n" +
                            spac.getWeaponType().toString() + "\n" + spac.getNames() + "\n" + spac.getLegion() + "\n" + spac.getCount() + "\n" + spac.getWorld());
                } else {
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    dbTable.refresh();
                    outputField.setText("Вы не можете изменить данное поле");
                }
            }
        });
        chap_countColoumn.setCellValueFactory(new PropertyValueFactory<SpaceMarine, Integer>("count"));
        chap_countColoumn.setCellFactory(TextFieldTableCell.<SpaceMarine, Integer>forTableColumn(new IntegerStringConverter()));
        chap_countColoumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SpaceMarine, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SpaceMarine, Integer> event) {
                if (MainApp.username.equals(persons.get(event.getTablePosition().getRow()).getOwner().getLogin())) {
                    persons.get(event.getTablePosition().getRow()).setCount(event.getNewValue());
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    outputField.setText("");
                    SpaceMarine spac = persons.get(event.getTablePosition().getRow());
                    MainApp.client.executeCommand("update " + spac.getId() + "\n" + spac.getName() + "\n" + spac.getX() + "\n" + spac.getY() + "\n" +
                            spac.getHealth() + "\n" + spac.getHeight() + "\n" + spac.getCategory().toString() + "\n" +
                            spac.getWeaponType().toString() + "\n" + spac.getNames() + "\n" + spac.getLegion() + "\n" + spac.getCount() + "\n" + spac.getWorld());
                } else {
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    dbTable.refresh();
                    outputField.setText("Вы не можете изменить данное поле");
                }
            }
        });
        chap_worldColoumn.setCellValueFactory(new PropertyValueFactory<SpaceMarine, String>("world"));
        chap_worldColoumn.setCellFactory(TextFieldTableCell.forTableColumn());
        chap_worldColoumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SpaceMarine, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SpaceMarine, String> event) {
                if (MainApp.username.equals(persons.get(event.getTablePosition().getRow()).getOwner().getLogin())) {
                    persons.get(event.getTablePosition().getRow()).setWorld(event.getNewValue());
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    outputField.setText("");
                    SpaceMarine spac = persons.get(event.getTablePosition().getRow());
                    MainApp.client.executeCommand("update " + spac.getId() + "\n" + spac.getName() + "\n" + spac.getX() + "\n" + spac.getY() + "\n" +
                            spac.getHealth() + "\n" + spac.getHeight() + "\n" + spac.getCategory().toString() + "\n" +
                            spac.getWeaponType().toString() + "\n" + spac.getNames() + "\n" + spac.getLegion() + "\n" + spac.getCount() + "\n" + spac.getWorld());
                } else {
                    masterData.clear();
                    masterData.addAll(persons);
                    dbTable.setItems(masterData);
                    dbTable.refresh();
                    outputField.setText("Вы не можете изменить данное поле");
                }
            }
        });
        userColoumn.setCellValueFactory(new PropertyValueFactory<SpaceMarine, User>("owner"));

        koef = new SimpleDoubleProperty();

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(5), new KeyValue(koef, 1)),
                new KeyFrame(Duration.seconds(0), new KeyValue(koef, 0))
        );

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw(koef);
                if (timeline.getStatus() == Animation.Status.STOPPED) {
                    timer.stop();
                    timeline.stop();
                }
            }
        };

        timer.start();
        timeline.play();
        this.start();
    }


    @Override
    public void run() {
        while (!isInterrupted()) {
            if (addUpdate) {
                koef = new SimpleDoubleProperty();
                timeline = new Timeline(

                        new KeyFrame(Duration.seconds(3), new KeyValue(koef, 1)),
                        new KeyFrame(Duration.seconds(0), new KeyValue(koef, 0))
                );

                timer = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        canvas.getGraphicsContext2D().clearRect((int) spike.getX(), spike.getY(), (int) spike.getHeight() / 2 * koef.doubleValue(), (int) spike.getHeight() / 2 * koef.doubleValue());
                        drawPerson((int) spike.getX(), spike.getY(), (int) spike.getHeight() / 2, spike.getOwner().getLogin().length() % 7, koef);
                        if (timeline.getStatus() == Animation.Status.STOPPED) {
                            timer.stop();
                            timeline.stop();
                        }
                    }
                };
                timer.start();
                timeline.play();
                addUpdate = false;
            }
            if (removeUpdate) {
                koef = new SimpleDoubleProperty();

                timeline = new Timeline(

                        new KeyFrame(Duration.seconds(3), new KeyValue(koef, 0)),
                        new KeyFrame(Duration.seconds(0), new KeyValue(koef, 1))

                );

                timer = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        canvas.getGraphicsContext2D().clearRect((int) spike.getX(), spike.getY(), (int) spike.getHeight() * 1.1 / 2 * koef.doubleValue(), (int) spike.getHeight() * 1.11 / 2 * koef.doubleValue());
                        drawPerson((int) spike.getX(), spike.getY(), (int) spike.getHeight() / 2, spike.getOwner().getLogin().length() % 7, koef);
                        if (timeline.getStatus() == Animation.Status.STOPPED) {
                            timer.stop();
                            timeline.stop();
                        }
                    }
                };
                timer.start();
                timeline.play();
                removeUpdate = false;
            }
            if (drawUpdate) {
                koef = new SimpleDoubleProperty();
                SimpleDoubleProperty kaef = new SimpleDoubleProperty();
                timeline = new Timeline(

                        new KeyFrame(Duration.seconds(3), new KeyValue(koef, 1)),
                        new KeyFrame(Duration.seconds(0), new KeyValue(koef, 0)),
                        new KeyFrame(Duration.seconds(3), new KeyValue(kaef, 0)),
                        new KeyFrame(Duration.seconds(0), new KeyValue(kaef, 1))
                );

                timer = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        canvas.getGraphicsContext2D().clearRect((int) spike.getX(), spike.getY(), (int) spike.getHeight() / 2 * koef.doubleValue(), (int) spike.getHeight() / 2 * koef.doubleValue());
                        drawPerson((int) spike.getX(), spike.getY(), (int) spike.getHeight() / 2, spike.getOwner().getLogin().length() % 7, koef);
                        canvas.getGraphicsContext2D().clearRect((int) spikuchka.getX(), spikuchka.getY(), (int) spikuchka.getHeight() / 2 * kaef.doubleValue() * 1.1, (int) spikuchka.getHeight() * 1.1 / 2 * kaef.doubleValue());
                        drawPerson((int) spikuchka.getX(), spikuchka.getY(), (int) spikuchka.getHeight() / 2, spikuchka.getOwner().getLogin().length() % 7, kaef);
                        if (timeline.getStatus() == Animation.Status.STOPPED) {
                            timer.stop();
                            timeline.stop();
                        }
                    }
                };
                timer.start();
                timeline.play();
                drawUpdate = false;
            }
            if (AuthController.language.equals("RUS")) {
                AuthController.mess = new RuMessenger();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        tabV.setText(AuthController.mess.visual());
                        tabT.setText(AuthController.mess.table());
                        inputField.setPromptText(AuthController.mess.inputfield());
                        outputField.setPromptText(AuthController.mess.outputfield());
                        menu.setText(AuthController.mess.commandss());
                        readyButton.setText(AuthController.mess.ready());
                        exitButton.setText(AuthController.mess.exitt());
                        username.setText(AuthController.mess.userf()+" :"+MainApp.username);
                    }
                });
                ll=AuthController.language;
                AuthController.language = "";

            }
            if (AuthController.language.equals("SRP")) {
                AuthController.mess = new SerMessenger();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        tabV.setText(AuthController.mess.visual());
                        tabT.setText(AuthController.mess.table());
                        inputField.setPromptText(AuthController.mess.inputfield());
                        outputField.setPromptText(AuthController.mess.outputfield());
                        menu.setText(AuthController.mess.commandss());
                        readyButton.setText(AuthController.mess.ready());
                        exitButton.setText(AuthController.mess.exitt());
                        username.setText(AuthController.mess.userf()+" :"+MainApp.username);

                    }
                });
                ll=AuthController.language;
                AuthController.language = "";
            }
            if (AuthController.language.equals("ALB")) {
                AuthController.mess = new AlbMessenger();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        tabV.setText(AuthController.mess.visual());
                        tabT.setText(AuthController.mess.table());
                        inputField.setPromptText(AuthController.mess.inputfield());
                        outputField.setPromptText(AuthController.mess.outputfield());
                        menu.setText(AuthController.mess.commandss());
                        readyButton.setText(AuthController.mess.ready());
                        exitButton.setText(AuthController.mess.exitt());
                        username.setText(AuthController.mess.userf()+" :"+MainApp.username);

                    }
                });
                ll=AuthController.language;
                AuthController.language = "";
            }
            if (AuthController.language.equals("SPA")) {
                AuthController.mess = new SpaMessenger();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        tabV.setText(AuthController.mess.visual());
                        tabT.setText(AuthController.mess.table());
                        inputField.setPromptText(AuthController.mess.inputfield());
                        outputField.setPromptText(AuthController.mess.outputfield());
                        menu.setText(AuthController.mess.commandss());
                        readyButton.setText(AuthController.mess.ready());
                        exitButton.setText(AuthController.mess.exitt());
                        username.setText(AuthController.mess.userf()+" :"+MainApp.username);

                    }
                });
                ll=AuthController.language;
                AuthController.language = "";
            }
        }
    }
    public void setLang(ActionEvent event) {
        AuthController.language = languageBox.getValue().toString();
    }
    public void update(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/graphica/update.fxml"));
        Parent root2;
        try {
            root2 = loader.load();
            Stage stage2 = new Stage();
            stage2.setTitle("Update");
            stage2.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/nek.png")));
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.sizeToScene();
            stage2.initOwner(AuthController.stage);
            stage2.showAndWait();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setTitle("Error");
            alert.setHeaderText("Person error");
            alert.setContentText("Can't open update window");
        }
    }

    public void insert(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/graphica/insert.fxml"));
        Parent root2;
        try {
            root2 = loader.load();
            Stage stage2 = new Stage();
            stage2.setTitle("Insert");
            stage2.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/nek.png")));
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.sizeToScene();
            stage2.initOwner(AuthController.stage);
            stage2.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setTitle("Error");
            alert.setHeaderText("Person error");
            alert.setContentText("Can't open update window");
        }
    }

    public void remove(ActionEvent actionEvent) {
        inputField.clear();
        AtomicReference<String> remove_by_id = new AtomicReference<>("remove_by_id ");
        outputField.setText("Введите id" + pressReady);
        readyButton.setOnAction(event1 -> {
            remove_by_id.set(remove_by_id + inputField.getText());
            inputField.clear();
            MainApp.client.executeCommand(String.valueOf(remove_by_id));
            waitForAnswer();
            outputField.setText(MainApp.answerLine);
        });
    }

    public void removeIn(ActionEvent actionEvent) {
        inputField.clear();
        AtomicReference<String> remove_at_index = new AtomicReference<>("remove_at_index ");
        outputField.setText("Введите index" + pressReady);
        readyButton.setOnAction(event1 -> {
            remove_at_index.set(remove_at_index + inputField.getText());
            inputField.clear();
            MainApp.client.executeCommand(String.valueOf(remove_at_index));
            waitForAnswer();
            outputField.setText(MainApp.answerLine);
        });
    }

    public void filterCont(ActionEvent actionEvent) {
        inputField.clear();
        AtomicReference<String> filt = new AtomicReference<>("filter_contains_name ");
        outputField.setText("Введите имя" + pressReady);
        readyButton.setOnAction(event1 -> {
            filt.set(filt + inputField.getText());
            inputField.clear();
            MainApp.client.executeCommand(String.valueOf(filt));
            waitForAnswer();
            outputField.setText(MainApp.answerLine);
        });
    }

    public void filterLess(ActionEvent actionEvent) {
        inputField.clear();
        AtomicReference<String> filt = new AtomicReference<>("filter_less_than_weapon_type ");
        outputField.setText("Введите weaponType" + pressReady);
        readyButton.setOnAction(event1 -> {
            filt.set(filt + inputField.getText());
            inputField.clear();
            MainApp.client.executeCommand(String.valueOf(filt));
            waitForAnswer();
            outputField.setText(MainApp.answerLine);
        });
    }

    public void removegreater(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/graphica/remove.fxml"));
        Parent root2;
        try {
            root2 = loader.load();
            Stage stage2 = new Stage();
            stage2.setTitle("Remove");
            stage2.getIcons().add(new Image(getClass().getResourceAsStream("../pictures/nek.png")));
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.sizeToScene();
            stage2.initOwner(AuthController.stage);
            stage2.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setTitle("Error");
            alert.setHeaderText("Person error");
            alert.setContentText("Can't open update window");
        }
    }

    public void help(ActionEvent actionEvent) throws InterruptedException, FiledIncorrect {
        MainApp.client.executeCommand("help");
        waitForAnswer();
        outputField.setText(MainApp.answerLine);

    }

    public void printF(ActionEvent actionEvent) throws InterruptedException, FiledIncorrect {
        MainApp.client.executeCommand("print_field_descending_height");
        waitForAnswer();
        outputField.setText(MainApp.answerLine);
    }

    public void info(ActionEvent actionEvent) throws InterruptedException, FiledIncorrect {
        MainApp.client.executeCommand("info");
        waitForAnswer();
        outputField.setText(MainApp.answerLine);
    }

    public void add(ActionEvent actionEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/graphica/add.fxml"));
        Parent root1;
        try {
            root1 = loader.load();
            Stage stage1 = new Stage();
            stage1.setTitle("Add");
            stage1.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/nek.png")));
            stage1.setScene(new Scene(root1));
            stage1.setResizable(false);
            stage1.sizeToScene();
            stage1.initOwner(AuthController.stage);
            stage1.showAndWait();
            stage1.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                }
            });

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setTitle("Error");
            alert.setHeaderText("add error");
            alert.setContentText("Can't open remove's window");
            alert.showAndWait().ifPresent(rs -> {
            });
        }
    }

    public void startDraw(Event event) {

        timer.start();
        timeline.play();
    }

    public void show(ActionEvent actionEvent) throws InterruptedException, FiledIncorrect {
        MainApp.client.executeCommand("show");
        waitForAnswer();
        outputField.setText(MainApp.answerLine);
    }

    public void execute_script(ActionEvent actionEvent) {
        inputField.clear();
        AtomicReference<String> execute_script = new AtomicReference<>("execute_script ");
        outputField.setText("Введите имя файла вместе с форматом" + pressReady);
        readyButton.setOnAction(event1 -> {
            execute_script.set(execute_script + inputField.getText());
            System.out.println(execute_script);
            inputField.clear();
            MainApp.client.executeCommand(String.valueOf(execute_script));
            waitForAnswer();
            outputField.setText(MainApp.answerLine);
        });
    }

    public void clear(ActionEvent actionEvent) throws InterruptedException, FiledIncorrect {
        MainApp.client.executeCommand("clear");
        waitForAnswer();
        outputField.setText(MainApp.answerLine);
    }

    public void exitWindow(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void ready(ActionEvent actionEvent) {
    }


    public void drawPerson(int x, int y, int size, int color, DoubleProperty koef) {
        int xx = x;
        int yy = y;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(colors.get(color).deriveColor(0, 1, 1, 1));
//        if (xx > 900 - size*2) {
//            xx = 900 - size*2;
//        }
//        if (yy > 580 - size*2) {
//            yy = 580 - size*2;
//        }
        gc.fillOval(xx, yy, koef.doubleValue() * size, koef.doubleValue() * size);


    }

    public void draw(DoubleProperty koef) {

        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (SpaceMarine person : persons) {
            int color_owner_id = (int) (users_keys.get(persons.indexOf(person)) % 7);
            int x = (int) person.getCoordinates().getX();
            int y = person.getCoordinates().getY();
            int size = (int) (person.getHeight() / 2);
            drawPerson(x, y, size, color_owner_id, koef);
        }

    }

    public void showPerson(MouseEvent mouseEvent) {
        boolean t = false;
        double mouse_x = mouseEvent.getX();
        double mouse_y = mouseEvent.getY();
        for (SpaceMarine person : persons) {
            int x = (int) person.getCoordinates().getX();
            int y = (int) person.getCoordinates().getY();
            int size = (int) (person.getHeight() / 2);
            double offset = System.currentTimeMillis() / 100.0;
            if (mouse_x > x && mouse_x < x + size && mouse_y > y && mouse_y < y + size) {
                MainApp.person = person;
                t = true;
                break;
            }
        }
        if (t) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/graphica/Show.fxml"));
            Parent root2;
            try {
                SpaceMarine spaceMarine = MainApp.person;
                int k = persons.size();
                root2 = loader.load();
                Stage stage2 = new Stage();
                stage2.setTitle("Show");
                stage2.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/nek.png")));
                stage2.setScene(new Scene(root2));
                stage2.setResizable(false);
                stage2.sizeToScene();
                stage2.initOwner(AuthController.stage);
                stage2.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setTitle("Error");
                alert.setHeaderText("Show error");
                alert.setContentText("Can't open show window");
            }
        }
        t = false;


    }

    public static void waitForAnswer() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    public void createListeners(FilteredList<SpaceMarine> filteredData) {
        idField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue;
                if (String.valueOf(person.getId()).contains(value)) {
                    return true;
                } else return false;
            });
        });
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue;
                if (String.valueOf(person.getName()).contains(value)) {
                    return true;
                } else return false;
            });
        });

        healthField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue;
                if (String.valueOf(person.getHealth()).contains(value)) {
                    return true;
                } else return false;
            });
        });

        heightField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue;
                if (String.valueOf(person.getHeight()).contains(value)) {
                    return true;
                } else return false;
            });
        });

        coordxField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue;
                if (String.valueOf(person.getX()).contains(value)) {
                    return true;
                } else return false;
            });
        });

        coordyField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue;
                if (String.valueOf(person.getY()).contains(value)) {
                    return true;
                } else return false;
            });
        });

        createField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue;
                if (String.valueOf(person.getCreationDate()).contains(value)) {
                    return true;
                } else return false;
            });
        });

        categoryField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue;
                if (String.valueOf(person.getCategory()).contains(value)) {
                    return true;
                } else return false;
            });
        });

        userField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue;
                if (String.valueOf(person.getOwner().getLogin()).contains(value)) {
                    return true;
                } else return false;
            });
        });

        weaponField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue;
                if (String.valueOf(person.getWeaponType()).contains(value)) {
                    return true;
                } else return false;
            });
        });

        chapNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue;
                if (String.valueOf(person.getNames()).contains(value)) {
                    return true;
                } else return false;
            });
        });
        chapLegField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue;
                if (String.valueOf(person.getLegion()).contains(value)) {
                    return true;
                } else return false;
            });
        });
        chapCountField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue;
                if (String.valueOf(person.getCount()).contains(value)) {
                    return true;
                } else return false;
            });
        });
        chapWorldField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue;
                if (String.valueOf(person.getWorld()).contains(value)) {
                    return true;
                } else return false;
            });
        });
    }
}