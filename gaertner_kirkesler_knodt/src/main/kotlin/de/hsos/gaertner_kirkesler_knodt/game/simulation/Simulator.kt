package de.hsos.gaertner_kirkesler_knodt.game.simulation

import de.hsos.gaertner_kirkesler_knodt.game.GameModel
/**
 * Dieses Interface stellt Methoden fuer die Registrierung eines Models am Simulator und der
 * Durchfuehrung einer Simulation bereit.
 *
 * @author Kirkesler
 */
interface Simulator {
    fun simulate()
    fun register(model: GameModel)
}