package de.hsos.gaertner_kirkesler_knodt

import de.hsos.gaertner_kirkesler_knodt.menu.MainMenuController
import de.hsos.gaertner_kirkesler_knodt.menu.MenuModel
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class RenewablesApp : Application() {
    override fun start(stage: Stage) {

        val menuModel = MenuModel()

        val fxmlLoader = FXMLLoader(RenewablesApp::class.java.getResource("menu.fxml"))
        val scene = Scene(fxmlLoader.load(), 720.0, 540.0)
        val mainMenuController = fxmlLoader.getController<MainMenuController>()
        mainMenuController.initData(menuModel)

        stage.title = "Hello!"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(RenewablesApp::class.java)
}