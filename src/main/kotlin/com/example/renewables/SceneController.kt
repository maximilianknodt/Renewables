package com.example.renewables

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage

class SceneController {
    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private fun onHelloButtonClick() {
        welcomeText.text = "Welcome to JavaFX Application!"
    }

    @FXML
    private fun switchToMenu(event: ActionEvent) {
        val fxmlLoader = FXMLLoader(RenewablesApp::class.java.getResource("menu.fxml"))
        val stage = ((event.source as Node).scene.window) as Stage
        val scene = Scene(fxmlLoader.load(), 720.0, 480.0)
        stage.scene = scene
        stage.show()
    }

    @FXML
    private fun switchToGame(event: ActionEvent) {
        val fxmlLoader = FXMLLoader(RenewablesApp::class.java.getResource("game.fxml"))
        val stage = ((event.source as Node).scene.window) as Stage
        val scene = Scene(fxmlLoader.load(), 720.0, 480.0)
        stage.scene = scene
        stage.show()
    }
}