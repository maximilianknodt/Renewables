package com.example.renewables

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class RenewablesApp : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(RenewablesApp::class.java.getResource("menu.fxml"))
        val scene = Scene(fxmlLoader.load(), 720.0, 480.0)

        stage.title = "Hello!"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(RenewablesApp::class.java)
}




