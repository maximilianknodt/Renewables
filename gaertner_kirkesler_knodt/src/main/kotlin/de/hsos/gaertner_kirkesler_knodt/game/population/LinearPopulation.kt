package de.hsos.gaertner_kirkesler_knodt.game.population

import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident

/**
 * Algorithmus zur Berechnung der Bevoelkerungsentwicklung, der mit linearem Wachstum arbeitet. Pro Runde veraendert
 * sich die Bevoelkerungsgroesse um den Faktor [change].
 *
 * @param change Veraenderung der Bevoelkerungsgroesse pro Runde
 *
 * @author Gaertner
 */
class LinearPopulation(
    private val change: Int = 1000,
) : PopulationAlg {

    override fun evolve(round: Int, current: Int, incident: Incident?): Int {
        var newPopulation = current

        if (incident != null) {
            newPopulation = (newPopulation * incident.proportionOfPopulationKilled()).toInt()
        }

        return newPopulation + change;
    }
}