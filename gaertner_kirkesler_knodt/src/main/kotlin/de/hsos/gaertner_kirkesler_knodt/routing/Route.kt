package de.hsos.gaertner_kirkesler_knodt.routing
/**
 * Diese Enum definiert alle verfuegbaren Routen.
 *
 * @param path Pfad zur FXML-Datei
 * @param stageTitle Anzuzeigender Titel in der Stage
 *
 * @author Kirkesler
 */
enum class Route(
    val path: String,
    val stageTitle: String
) {
    MENU("fxml/menu.fxml","Renewables - Menu"),
    GAME("fxml/game/game.fxml","Renewables - Game"),
    GAMEOVER("fxml/gameover.fxml", "Renewables - Game Over");
}