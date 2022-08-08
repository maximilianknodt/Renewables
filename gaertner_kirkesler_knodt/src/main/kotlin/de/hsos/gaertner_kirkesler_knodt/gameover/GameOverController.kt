package de.hsos.gaertner_kirkesler_knodt.gameover

import javafx.fxml.FXML
import javafx.fxml.Initializable
import java.net.URL
import java.util.*

/**
 * Controller, der sich um die Darstellung des GameOver-Screens kuemmert
 *
 * @author Kirkesler, Gaertner
 */
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