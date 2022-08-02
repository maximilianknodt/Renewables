package de.hsos.gaertner_kirkesler_knodt.routing

enum class Route(
    val path: String,
    val stageTitle: String
) {
    // TODO: check for correct path
    MENU("menu.fxml","Renewables - Menu"),
    GAME("game/game.fxml","Renewables - Game"),
    GAMEOVER("gameover.fxml", "Renewables - Game Over");
}