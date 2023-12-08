module com.example.testcombo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testcombo to javafx.fxml;
    exports com.example.testcombo;
}