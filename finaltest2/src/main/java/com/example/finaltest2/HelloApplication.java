package com.example.finaltest2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private ListView<String> itemList;  // ListView to display available items
    private ListView<String> outputList; // ListView to display items in the cart

    public static void main(String[] args) {
        launch(args);
    }
    private ChoiceBox<String> fileChoiceBox;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Blendify");
        primaryStage.setWidth(940);
        primaryStage.setHeight(940);

        // Create a BorderPane for the main content
        BorderPane mainPane = new BorderPane();

        // Create a GridPane for the main buttons
        GridPane buttonGrid = new GridPane();
        buttonGrid.setBackground(new Background(new BackgroundFill(Color.rgb(217, 217, 217), null, null)));

        Button randomizerButton = createButton("Randomizer");
        randomizerButton.setOnAction(e -> {randomizer();
        });
        Button listEditorButton = createButton("List Editor");
        Button lightDarkModeButton = createButton("Light/Dark Mode");
        Button exitButton = createButton("Exit Application");

        setButtonSize(randomizerButton, 345, 100);
        setButtonSize(listEditorButton, 345, 100);
        setButtonSize(lightDarkModeButton, 345, 100);
        setButtonSize(exitButton, 345, 100);

        buttonGrid.add(randomizerButton, 0, 0);
        buttonGrid.add(listEditorButton, 1, 0);
        buttonGrid.add(lightDarkModeButton, 0, 1);
        buttonGrid.add(exitButton, 1, 1);

        // Create an HBox for the bottom buttons
        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.CENTER_LEFT);
        bottomBox.setBackground(new Background(new BackgroundFill(Color.rgb(217, 217, 217), null, null)));

        Button uninstallButton = createButton("Uninstall");
        Button resetButton = createButton("Reset");

        setButtonSize(uninstallButton, 170, 55);
        setButtonSize(resetButton, 170, 55);

        uninstallButton.setOnAction(e -> showAlert("Uninstall Button Clicked"));
        resetButton.setOnAction(e -> showAlert("Reset Button Clicked"));

        bottomBox.getChildren().addAll(uninstallButton, resetButton);

        // Add components to the main BorderPane
        mainPane.setCenter(buttonGrid);
        mainPane.setBottom(bottomBox);

        // Create a scene
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 18;");
        return button;
    }

    private void setButtonSize(Button button, double width, double height) {
        button.setMinSize(width, height);
        button.setMaxSize(width, height);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private List<String> loadItemsFromFile(String filePath) {
        List<String> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                items.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error loading items from file: " + filePath);
        }
        return items;
    }
    private void randomizer() {
        Stage randomrandomstage = new Stage();
        randomrandomstage.setTitle("Randomizer");

        // Available items
        fileChoiceBox = new ChoiceBox<>();
        fileChoiceBox.getItems().addAll("C:\\finaltest2\\src\\main\\java\\com\\example\\finaltest2\\items1.txt", "C:\\finaltest2\\src\\main\\java\\com\\example\\finaltest2\\items2.txt", "C:\\finaltest2\\src\\main\\java\\com\\example\\finaltest2\\items3.txt");
        fileChoiceBox.setValue("C:\\finaltest2\\src\\main\\java\\com\\example\\finaltest2\\items1.txt");

        itemList = new ListView<>();
        itemList.getItems().addAll(loadItemsFromFile(fileChoiceBox.getValue()));

        fileChoiceBox.setOnAction(e -> itemList.getItems().setAll(loadItemsFromFile(fileChoiceBox.getValue())));

        Button addButton = new Button("Add List");
        addButton.setOnAction(e -> {
            String selectedItem = itemList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                outputList.getItems().add(selectedItem);
                itemList.getItems().remove(selectedItem);
            }
        });

        Button removeButton = new Button("Remove List");
        removeButton.setOnAction(e -> {
            String selectedItem = outputList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                outputList.getItems().remove(selectedItem);
                itemList.getItems().add(selectedItem);
            }
        });

        outputList = new ListView<>();

        ToggleButton toggleButton = new ToggleButton("Toggle Repeats");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setId("myChoiceBox");
        choiceBox.getItems().addAll("0", "1", "2", "3","4");
        choiceBox.setValue("0");

        Button randomizerButton = new Button("Randomize");
        randomizerButton.setOnAction(e -> show1RandomizedItems());

        // Create VBox for the bottom section
        VBox bottomVBox = new VBox();
        bottomVBox.getChildren().addAll(new Label("Select List:"), fileChoiceBox, outputList);
        bottomVBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomVBox.setSpacing(10);

        FlowPane topButtonLayout = new FlowPane();
        topButtonLayout.getChildren().addAll(addButton, removeButton);
        topButtonLayout.setAlignment(Pos.CENTER);
        topButtonLayout.setHgap(10);
        topButtonLayout.setPadding(new Insets(0, 10, 0, 10));

        FlowPane bottomButtonLayout = new FlowPane();
        bottomButtonLayout.getChildren().addAll(toggleButton, new Label("Pair Amount:"), choiceBox);
        bottomButtonLayout.setAlignment(Pos.CENTER);
        bottomButtonLayout.setHgap(10);
        bottomButtonLayout.setPadding(new Insets(0, 10, 0, 10));

        FlowPane randomizerButtonLayout = new FlowPane();
        randomizerButtonLayout.getChildren().addAll(randomizerButton);
        randomizerButtonLayout.setAlignment(Pos.CENTER);
        randomizerButtonLayout.setPadding(new Insets(10, 0, 0, 0));

        BorderPane topLayout = new BorderPane();
        topLayout.setTop(itemList);
        topLayout.setCenter(topButtonLayout);
        topLayout.setBottom(bottomButtonLayout);
        topLayout.setMargin(bottomButtonLayout, new Insets(10, 0, 0, 0));

        BorderPane layout = new BorderPane();
        BorderPane.setMargin(addButton, new Insets(10));
        BorderPane.setMargin(removeButton, new Insets(10));

        layout.setTop(topLayout);
        layout.setCenter(randomizerButtonLayout);
        layout.setBottom(bottomVBox);

        Scene scene = new Scene(layout, 400, 550);
        randomrandomstage.setScene(scene);
        randomrandomstage.show();
    }


    private void show1RandomizedItems() {
        Stage randomizerStage = new Stage();
        randomizerStage.setTitle("Randomized Items");

        ListView<String> randomizedListView = new ListView<>();
        randomizedListView.getItems().addAll(outputList.getItems());

        BorderPane randomizerLayout = new BorderPane();
        randomizerLayout.setCenter(randomizedListView);

        Scene randomizerScene = new Scene(randomizerLayout, 200, 300);
        randomizerStage.setScene(randomizerScene);

        randomizerStage.show();
    }
}