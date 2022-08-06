package de.hsos.gaertner_kirkesler_knodt.gameover

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label

class GameOverController {
    private lateinit var model: GameOverModel

    @FXML
    private lateinit var cause: Label

    @FXML
    private lateinit var backToMenuButton: Button

    fun initData(model: GameOverModel) {
        this.model = model
    }


    fun onReturn() {
        model.backToMainMenu()
    }

    fun onEnd() {
        model.endGame()
    }

}