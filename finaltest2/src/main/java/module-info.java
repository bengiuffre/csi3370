module com.example.finaltest2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.finaltest2 to javafx.fxml;
    exports com.example.finaltest2;
}