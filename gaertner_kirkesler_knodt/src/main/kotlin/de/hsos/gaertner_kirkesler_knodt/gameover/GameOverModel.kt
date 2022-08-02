package de.hsos.gaertner_kirkesler_knodt.gameover

import de.hsos.gaertner_kirkesler_knodt.BaseModel
import de.hsos.gaertner_kirkesler_knodt.routing.Route

class GameOverModel : BaseModel() {
    fun backToMainMenu() {
        router.showScene(Route.MENU)
    }
}