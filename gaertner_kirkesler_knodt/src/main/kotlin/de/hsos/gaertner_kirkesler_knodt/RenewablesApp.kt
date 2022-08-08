package de.hsos.gaertner_kirkesler_knodt

import de.hsos.gaertner_kirkesler_knodt.routing.Route
import de.hsos.gaertner_kirkesler_knodt.routing.RouteController
import javafx.application.Application
import javafx.stage.Stage
/**
 * Hauptklasse 'RenewablesApp'. Instanziiert einen Router und setzt die Szene auf das Hauptmenue.
 *
 * @author Gaertner, Kirkesler, Knodt
 */
class RenewablesApp : Application() {
    private val router: RouteController = RouteController
    override fun start(stage: Stage) {
        router.showScene(Route.MENU)
    }
}

fun main() {
    Application.launch(RenewablesApp::class.java)
}