package de.hsos.gaertner_kirkesler_knodt.gameover

import de.hsos.gaertner_kirkesler_knodt.BaseModel
import de.hsos.gaertner_kirkesler_knodt.routing.Route
import kotlin.system.exitProcess

class GameOverModel : BaseModel() {
    fun backToMainMenu() {
        router.showScene(Route.MENU)
    }

    fun endGame() {
        println("Beende Spiel.")
        exitProcess(0)
    }
}