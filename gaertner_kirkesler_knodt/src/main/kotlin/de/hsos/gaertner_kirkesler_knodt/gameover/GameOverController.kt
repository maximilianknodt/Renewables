package de.hsos.gaertner_kirkesler_knodt.gameover

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.Label
import java.net.URL
import java.util.*

class GameOverController : Initializable {
    private lateinit var model: GameOverModel

    fun initData(model: GameOverModel) {
        this.model = model
    }

    @FXML
    fun onReturn() {
        model.backToMainMenu()
    }

    @FXML
    fun onEnd() {
        model.endGame()
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) { }
}