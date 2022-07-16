package de.hsos.gaertner_kirkesler_knodt.game

import javafx.fxml.Initializable

abstract class GameBaseController : Initializable {

    lateinit var game: GameModel

    fun initData(game: GameModel) {
        this.game = game
    }
}