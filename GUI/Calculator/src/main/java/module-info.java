module com.example.calculator {
    requires javafx.controls;
    requires javafx.fxml;
//    requires javafx.media;


    opens com.example.calculator to javafx.fxml;
    exports com.example.calculator;
}