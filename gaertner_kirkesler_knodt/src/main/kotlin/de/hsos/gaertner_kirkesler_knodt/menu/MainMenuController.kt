package de.hsos.gaertner_kirkesler_knodt.menu

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import java.net.URL
import java.util.*

class MainMenuController : Initializable {

    private lateinit var model: MenuModel

    @FXML
    private lateinit var newGameButton: Button

    @FXML
    private lateinit var exitButton: Button

    fun initData(data: MenuModel) {
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

    override fun initialize(location: URL?, resources: ResourceBundle?) { }
}