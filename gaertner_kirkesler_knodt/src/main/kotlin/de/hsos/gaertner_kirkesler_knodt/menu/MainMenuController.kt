package de.hsos.gaertner_kirkesler_knodt.menu

import javafx.fxml.FXML
import javafx.scene.control.Button

class MainMenuController  {

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
}