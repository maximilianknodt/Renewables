module com.example.renewables {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires com.almasb.fxgl.all;

    opens com.example.renewables to javafx.fxml;
    exports com.example.renewables;
}