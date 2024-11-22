package com.example.dataentryform;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

class Person {
    private String name;
    private String fatherName;
    private String cnic;
    private String dob;
    private String gender;
    private String city;
    private String imageFilePath;

    public Person(String name, String fatherName, String cnic, String dob, String gender, String city, String imageFilePath) {
        this.name = name;
        this.fatherName = fatherName;
        this.cnic = cnic;
        this.dob = dob;
        this.gender = gender;
        this.city = city;
        this.imageFilePath = imageFilePath;
    }
    public String getName() {
        return name;
    }
    public String getFatherName() {
        return fatherName;
    }
    public String getCnic() {
        return cnic;
    }
    public String getDob() {
        return dob;
    }
    public String getGender() {
        return gender;
    }
    public String getCity() {
        return city;
    }
}

public class UniversityFormApp extends Application {

    private ArrayList<Person> personList = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #3F3F3F3F;");
        
        ImageView banner = new ImageView(new Image("file:/mnt/data/image.png"));
        banner.setFitWidth(600);
        banner.setFitHeight(120);
        banner.setPreserveRatio(true);

        Text bannerText = new Text("Data Entry Form");
        bannerText.setFont(Font.font("Arial", 28));
        bannerText.setFill(Color.WHITE);
        StackPane bannerPane = new StackPane(banner, bannerText);
        StackPane.setAlignment(bannerText, Pos.CENTER);
        bannerPane.setStyle("-fx-background-color: #56788B;");
        root.setTop(bannerPane);

        GridPane form = new GridPane();
        form.setPadding(new Insets(15));
        form.setHgap(15);
        form.setVgap(15);
        form.setAlignment(Pos.CENTER_LEFT);
        form.setStyle("-fx-background-color: #2F2F2F2F; -fx-border-color: #cccccc; -fx-border-radius: 8px; -fx-background-radius: 8px;");

        Label nameLabel = new Label("Name:");
        nameLabel.setStyle("-fx-text-fill: #333333;");
        TextField nameField = new TextField();
        nameField.setStyle("-fx-background-color: #e0f7fa; -fx-border-color: #26c6da;");

        Label fatherNameLabel = new Label("Father Name:");
        fatherNameLabel.setStyle("-fx-text-fill: #333333;");
        TextField fatherNameField = new TextField();
        fatherNameField.setStyle("-fx-background-color: #e0f7fa; -fx-border-color: #26c6da;");

        Label cnicLabel = new Label("CNIC:");
        cnicLabel.setStyle("-fx-text-fill: #333333;");
        TextField cnicField = new TextField();
        cnicField.setStyle("-fx-background-color: #e0f7fa; -fx-border-color: #26c6da;");

        Label dateLabel = new Label("Date of Birth:");
        dateLabel.setStyle("-fx-text-fill: #333333;");
        DatePicker datePicker = new DatePicker();

        Label genderLabel = new Label("Gender:");
        genderLabel.setStyle("-fx-text-fill: #333333;");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, maleRadio, femaleRadio);

        Label cityLabel = new Label("City:");
        cityLabel.setStyle("-fx-text-fill: #333333;");
        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.getItems().addAll("Lahore", "Karachi", "Islamabad");
        cityComboBox.setStyle("-fx-background-color: #e0f7fa; -fx-border-color: #26c6da;");

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #32CD32; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px;");
        saveButton.setOnAction(e -> savePersonData(nameField, fatherNameField, cnicField, datePicker, genderGroup, cityComboBox));

        Label imageLabel = new Label();
        ImageView imageView = new ImageView();
        imageView.setFitWidth(220);
        imageView.setFitHeight(220);
        imageView.setPreserveRatio(true);

        Button fileChooserButton = new Button("Choose File");
        fileChooserButton.setStyle("-fx-background-color: #56788B; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px;");
        fileChooserButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select an Image");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                imageView.setImage(new Image(selectedFile.toURI().toString()));
            }
        });

        form.add(nameLabel, 0, 0);
        form.add(nameField, 1, 0);
        form.add(fatherNameLabel, 0, 1);
        form.add(fatherNameField, 1, 1);
        form.add(cnicLabel, 0, 2);
        form.add(cnicField, 1, 2);
        form.add(dateLabel, 0, 3);
        form.add(datePicker, 1, 3);
        form.add(genderLabel, 0, 4);
        form.add(genderBox, 1, 4);
        form.add(cityLabel, 0, 5);
        form.add(cityComboBox, 1, 5);
        form.add(saveButton, 1, 6);

        VBox imageBox = new VBox(20, imageLabel, imageView, fileChooserButton);
        imageBox.setPadding(new Insets(20));
        imageBox.setAlignment(Pos.CENTER);

        HBox centerContent = new HBox(30, form, imageBox);
        centerContent.setPadding(new Insets(20));
        root.setCenter(centerContent);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("University Form Template");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void savePersonData(TextField nameField, TextField fatherNameField, TextField cnicField, DatePicker datePicker, ToggleGroup genderGroup, ComboBox<String> cityComboBox) {
        String name = nameField.getText();
        String fatherName = fatherNameField.getText();
        String cnic = cnicField.getText();
        String dob = datePicker.getValue() != null ? datePicker.getValue().toString() : "";
        String gender = ((RadioButton) genderGroup.getSelectedToggle()) != null ? ((RadioButton) genderGroup.getSelectedToggle()).getText() : "";
        String city = cityComboBox.getValue();

        Person newPerson = new Person(name, fatherName, cnic, dob, gender, city, "");
        personList.add(newPerson);
        printPersonList();

        nameField.clear();
        fatherNameField.clear();
        cnicField.clear();
        datePicker.setValue(null);
        genderGroup.selectToggle(null);
        cityComboBox.getSelectionModel().clearSelection();
    }
    private void printPersonList() {
        System.out.println("\n=== Saved Persons ===");
        for (Person person : personList) {
            System.out.println("Name: " + person.getName());
            System.out.println("Father Name: " + person.getFatherName());
            System.out.println("CNIC: " + person.getCnic());
            System.out.println("Date of Birth: " + person.getDob());
            System.out.println("Gender: " + person.getGender());
            System.out.println("City: " + person.getCity());
            System.out.println("---------------------");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}