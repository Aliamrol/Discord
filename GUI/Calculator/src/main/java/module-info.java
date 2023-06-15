module com.example.calculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires rt;
    requires jfxrt;


    opens com.example.calculator to javafx.fxml;
    exports com.example.calculator;
}