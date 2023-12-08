package com.example.testcombo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ListEditor {

    static Map<String, ObservableList<String>> createdLists = new HashMap<>();
    static String currentListName;

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("List Editor");

        TextField listNameInput = new TextField();
        TextField newItemInput = new TextField();
        ListView<String> listView = new ListView<>();

        Button createListButton = new Button("Create List");
        Button addItemButton = new Button("Add Item");
        Button removeItemButton = new Button("Remove Item");
        Button closeButton = new Button("Close");
        Button resetButton = new Button("Save");

        createListButton.setOnAction(e -> {
            String listName = listNameInput.getText().trim();
            if (!listName.isEmpty()) {
                createdLists.putIfAbsent(listName, FXCollections.observableArrayList());
                currentListName = listName;
                listView.setItems(createdLists.get(currentListName));
                listNameInput.setDisable(true);
                createListButton.setDisable(true);
            }
        });

        addItemButton.setOnAction(e -> {
            String newItem = newItemInput.getText().trim();
            if (!newItem.isEmpty() && currentListName != null) {
                ObservableList<String> currentListItems = createdLists.get(currentListName);
                if (currentListItems != null) {
                    currentListItems.add(newItem);
                    listView.setItems(currentListItems);
                    newItemInput.clear();
                }
            }
        });

        removeItemButton.setOnAction(e -> {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null && currentListName != null) {
                ObservableList<String> currentListItems = createdLists.get(currentListName);
                if (currentListItems != null) {
                    currentListItems.remove(selectedItem);
                    listView.setItems(currentListItems);
                }
            }
        });

        closeButton.setOnAction(e -> window.close());

        resetButton.setOnAction(e -> {
            listNameInput.clear();
            listNameInput.setDisable(false);
            createListButton.setDisable(false);
            newItemInput.clear();
            saveList();
            listView.getItems().clear();
            currentListName = null;
        });

        HBox inputBox = new HBox(10);
        inputBox.getChildren().addAll(listNameInput, createListButton);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(inputBox, newItemInput, addItemButton, removeItemButton, listView, closeButton, resetButton);

        Scene scene = new Scene(layout, 300, 400);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void saveList() {
        for (String listName : createdLists.keySet()) {
            ObservableList<String> currentListItems = createdLists.get(listName);
            if (currentListItems != null) {
                ObservableList<String> copiedList = FXCollections.observableArrayList(currentListItems);
                createdLists.put(listName, copiedList);
            }
        }
    }

    public static ObservableList<String> getItems(String listName) {
        return createdLists.get(listName);
    }
}
