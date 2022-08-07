package de.hsos.gaertner_kirkesler_knodt.game.population

import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident

/**
 * Interface zur Bevoelkerungs-Berechnung je Runde. Verfolgt das Strategy-Pattern, sodass die Variante der Berechnung
 * austauschbar ist. Realisierende Klassen müssen die Methode [evolve] mit ihrem eigenen Berechnungsalgorithmus
 * implementieren.
 *
 * @author Gaertner
 */
interface PopulationAlg {

    /**
     * @param round aktuelle Runde der Simulation
     * @param current aktuelle Populationsgroesse
     * @param incident aufgetretenes Ereignis, das Einfluss auf Bevoelkerungsentwicklung haben koennte
     * @return neue Populationsgroesse nach dieser Runde
     */
    fun evolve(round: Int, current: Int, incident: Incident?): Int
}
