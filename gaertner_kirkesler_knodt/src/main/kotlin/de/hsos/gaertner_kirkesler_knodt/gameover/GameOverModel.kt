package de.hsos.gaertner_kirkesler_knodt.gameover

import de.hsos.gaertner_kirkesler_knodt.BaseModel
import de.hsos.gaertner_kirkesler_knodt.routing.Route
import de.hsos.gaertner_kirkesler_knodt.routing.Router
import kotlin.system.exitProcess

class GameOverModel(
    override var router: Router,
) : BaseModel(router) {

    fun backToMainMenu() {
        router.showScene(Route.MENU)
    }

    fun endGame() {
        println("Beende Spiel.")
        exitProcess(0)
    }
}