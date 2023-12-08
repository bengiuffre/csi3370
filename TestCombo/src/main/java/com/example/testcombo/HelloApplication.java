package com.example.testcombo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private boolean isDarkMode = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Application.setUserAgentStylesheet("/primer-light.css");

        primaryStage.setTitle("Blendify");

        //image loading
        Image logoImage = new Image(getClass().getResource("/dice.png").toExternalForm());
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(300); // Set the width of the image
        logoImageView.setPreserveRatio(true);

        Button listEditorButton = new Button("List Editor");
        Button randomizerButton = new Button("Randomizer");
        Button toggleModeButton = new Button("Toggle Dark Mode");
        Button exitButton = new Button("Exit");

        //button actions
        listEditorButton.setOnAction(e -> ListEditor.display());
        randomizerButton.setOnAction(e -> Randomizer.display());
        toggleModeButton.setOnAction(e -> toggleMode(primaryStage));
        exitButton.setOnAction(e -> primaryStage.close());

        // vbox stuff
        VBox vbox = new VBox();
        vbox.getChildren().addAll(logoImageView, listEditorButton, randomizerButton, toggleModeButton, exitButton);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(20, 20, 20, 20));

        // vertical box part 2
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void toggleMode(Stage stage) {
        isDarkMode = !isDarkMode;

        if (isDarkMode) {
            setDarkMode(stage);
        } else {
            setLightMode(stage);
        }
    }

    private void setDarkMode(Stage stage) {
        Scene scene = stage.getScene();
        scene.getRoot().setStyle("-fx-background-color: #333; -fx-text-fill: white;");
    }

    private void setLightMode(Stage stage) {
        Scene scene = stage.getScene();
        scene.getRoot().setStyle("-fx-background-color: white; -fx-text-fill: black;");
    }

    public static void mainMenu() {
        launch();
    }
}
