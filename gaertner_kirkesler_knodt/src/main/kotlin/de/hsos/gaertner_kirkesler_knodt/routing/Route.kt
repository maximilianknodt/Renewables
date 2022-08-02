package de.hsos.gaertner_kirkesler_knodt.routing
/**
 * Diese Klasse stellt einen Vorfall dar. Sie enthaelt eine Builder-Klasse
 * zur Erstellung von Vorfaellen.
 *
 * Builder-Pattern: vgl. https://stackoverflow.com/questions/36140791/how-to-implement-builder-pattern-in-kotlin
 *
 * @param type Art des Vorfalls
 * @param severity Schwere des Vorfalls
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