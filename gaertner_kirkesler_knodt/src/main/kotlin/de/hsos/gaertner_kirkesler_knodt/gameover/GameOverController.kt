package de.hsos.gaertner_kirkesler_knodt.gameover

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.Label
import java.net.URL
import java.util.*

class GameOverController : Initializable {
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

    override fun initialize(location: URL?, resources: ResourceBundle?) { }
}