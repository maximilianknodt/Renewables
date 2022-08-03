package de.hsos.gaertner_kirkesler_knodt.menu

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import java.net.URL
import java.util.*
import kotlin.system.exitProcess

class MainMenuController : Initializable, EventHandler<ActionEvent> {

    private lateinit var model: MenuModel

    @FXML
    private lateinit var newGameButton: Button

    @FXML
    private lateinit var exitButton: Button

    override fun initialize(location: URL?, resources: ResourceBundle?) {

    }

    public fun initData(data: MenuModel) {
        this.model = data
    }

    @FXML
    private fun onStart(){
        model.startGame()
    }

    @FXML
    private fun onEnd(){
        model.endApplication()
    }

    override fun handle(event: ActionEvent?) {

    }

}