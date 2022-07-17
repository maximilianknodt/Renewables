package de.hsos.gaertner_kirkesler_knodt.menu

import kotlin.system.exitProcess

class MenuModel {

    fun startGame() {
        println("Starte Spiel")

    }

    fun endApplication() {
        println("Beende Spiel")
        exitProcess(0)
    }

    fun showMainMenu() {
        println("Zeige Hauptmenü")
    }

}