package de.hsos.gaertner_kirkesler_knodt.gameover

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.Label
import java.net.URL
import java.util.*

class GameOverController : Initializable, EventHandler<ActionEvent> {
    private lateinit var model: GameOverModel

    @FXML
    private lateinit var cause: Label

    @FXML
    private lateinit var backToMenuButton: Button

    public fun initData(model: GameOverModel) {
        this.model = model
    }

    override fun initialize(p0: URL?, p1: ResourceBundle?) {

    }

    public fun onReturn() {
        model.backToMainMenu()
    }

    public fun onEnd() {
        model.endGame()
    }

    override fun handle(event: ActionEvent?) {

    }

}