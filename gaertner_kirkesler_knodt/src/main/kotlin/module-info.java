module de.hsos.gaertner_kirkesler_knodt {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    opens de.hsos.gaertner_kirkesler_knodt.menu to javafx.fxml;
    exports de.hsos.gaertner_kirkesler_knodt.menu;

    opens de.hsos.gaertner_kirkesler_knodt.game to javafx.fxml;
    exports de.hsos.gaertner_kirkesler_knodt.game;

    opens de.hsos.gaertner_kirkesler_knodt.game.ui to javafx.fxml;
    exports de.hsos.gaertner_kirkesler_knodt.game.ui;

    opens de.hsos.gaertner_kirkesler_knodt to javafx.fxml;
    exports de.hsos.gaertner_kirkesler_knodt;

    opens de.hsos.gaertner_kirkesler_knodt.gameover to javafx.fxml;
    exports de.hsos.gaertner_kirkesler_knodt.gameover;
}