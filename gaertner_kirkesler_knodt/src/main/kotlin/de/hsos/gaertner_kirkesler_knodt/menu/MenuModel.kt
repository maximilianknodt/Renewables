package de.hsos.gaertner_kirkesler_knodt.menu

import de.hsos.gaertner_kirkesler_knodt.BaseModel
import de.hsos.gaertner_kirkesler_knodt.routing.Route
import de.hsos.gaertner_kirkesler_knodt.routing.Router
import kotlin.system.exitProcess

class MenuModel(
    override var router: Router,
) : BaseModel(router) {

    fun startGame() {
        println("Starte Spiel")
        router.showScene(Route.GAME)
    }

    fun endApplication() {
        println("Beende Spiel")
        exitProcess(0)
    }

}