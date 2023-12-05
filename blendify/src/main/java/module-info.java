module blendgroup {
    requires javafx.controls;
    requires javafx.fxml;

    opens blendgroup to javafx.fxml;
    exports blendgroup;
}
