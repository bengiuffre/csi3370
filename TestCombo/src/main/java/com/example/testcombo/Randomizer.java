package com.example.testcombo;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizer {

    public static void display() {
        System.out.println("createdLists size: " + ListEditor.createdLists.size());
        System.out.println("createdLists content: " + ListEditor.createdLists);

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Randomizer");

        VBox listsContainer = new VBox(5);
        listsContainer.setAlignment(Pos.CENTER);
        List<CheckBox> listCheckboxes = new ArrayList<>();

        for (String list : ListEditor.createdLists.keySet()) {
            CheckBox checkBox = new CheckBox(list);
            listCheckboxes.add(checkBox);
            listsContainer.getChildren().add(checkBox);
        }

        Label outputLabel = new Label();

        Button randomizeButton = new Button("Randomize");
        randomizeButton.setOnAction(e -> {
            List<String> selectedLists = new ArrayList<>();

            for (CheckBox checkBox : listCheckboxes) {
                if (checkBox.isSelected()) {
                    selectedLists.add(checkBox.getText());
                }
            }

            if (selectedLists.size() > 0) {
                StringBuilder result = new StringBuilder();
                Random random = new Random();

                for (String selectedList : selectedLists) {
                    ObservableList<String> items = ListEditor.getItems(selectedList);

                    if (items != null && !items.isEmpty()) {
                        int randomIndex = random.nextInt(items.size());
                        result.append(items.get(randomIndex)).append(", ");
                    }
                }

                if (result.length() > 0) {
                    result.setLength(result.length() - 2);
                    outputLabel.setText("Randomized Items: " + result.toString());
                } else {
                    outputLabel.setText("Selected lists are empty");
                }
            } else {
                outputLabel.setText("Select at least one list");
            }
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(listsContainer, randomizeButton, outputLabel);

        Scene scene = new Scene(layout, 300, 350);
        window.setScene(scene);
        window.showAndWait();
    }
}
