module de.hsos.gaertner_kirkesler_knodt {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens de.hsos.gaertner_kirkesler_knodt to javafx.fxml;
    exports de.hsos.gaertner_kirkesler_knodt;
}