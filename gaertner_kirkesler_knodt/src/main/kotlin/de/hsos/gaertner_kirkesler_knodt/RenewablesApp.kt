package de.hsos.gaertner_kirkesler_knodt

import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import de.hsos.gaertner_kirkesler_knodt.game.ui.GameUIController
import de.hsos.gaertner_kirkesler_knodt.menu.MainMenuController
import de.hsos.gaertner_kirkesler_knodt.menu.MenuModel
import de.hsos.gaertner_kirkesler_knodt.routing.Route
import de.hsos.gaertner_kirkesler_knodt.routing.RouteController
import de.hsos.gaertner_kirkesler_knodt.routing.Router
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
/**
 * Hauptklasse 'RenewablesApp'. Instanziiert einen Router und setzt die Szene auf das Hauptmenue.
 *
 * @author
 */
class RenewablesApp() : Application() {
    val router: RouteController = RouteController()
    override fun start(stage: Stage) {
        router.showScene(Route.GAMEOVER)
    }
}

fun main() {
    Application.launch(RenewablesApp::class.java)
}