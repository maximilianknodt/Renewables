package de.hsos.gaertner_kirkesler_knodt

import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import de.hsos.gaertner_kirkesler_knodt.game.ui.GameUIController
import de.hsos.gaertner_kirkesler_knodt.menu.MainMenuController
import de.hsos.gaertner_kirkesler_knodt.menu.MenuModel
import de.hsos.gaertner_kirkesler_knodt.routing.RouteController
import de.hsos.gaertner_kirkesler_knodt.routing.Router
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class RenewablesApp() : Application() {

    val router: RouteController = RouteController()
    override fun start(stage: Stage) {

        // TODO: don't forget!
        val model = GameModel()

        val fxmlLoader = FXMLLoader(RenewablesApp::class.java.getResource("fxml/game/game.fxml"))
        val scene = Scene(fxmlLoader.load(), 720.0, 540.0)
        val gameController = fxmlLoader.getController<GameUIController>()
        gameController.initData(model)

        stage.title = "Hello!"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(RenewablesApp::class.java)
}