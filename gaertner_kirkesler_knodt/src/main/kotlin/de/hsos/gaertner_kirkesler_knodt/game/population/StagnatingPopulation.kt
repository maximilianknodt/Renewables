package de.hsos.gaertner_kirkesler_knodt.game.population

import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident

/**
 * Algorithmus zur Berechnung der Bevoelkerungsentwicklung, der mit einer Annaeherung an einen Asymptotenwert arbeitet.
 * @param asymptote Zielwert fuer Bevoelkerungsentwicklung
 * @param strength Staerke des Einflusses der Asymptote (0.0 = keine Auswirkung des Zielwerts, 1.0 = Halten des Zielwerts)
 *
 * @author Gaertner
 */
class StagnatingPopulation(
    private val asymptote: Int = 50000,
    private val strength: Double = 0.5,
) : PopulationAlg {

    init {
        require(strength in 0.0..1.0) { "strength must be in range [0.0, 1.0]" }
    }

    override fun evolve(round: Int, current: Int, incident: Incident?): Int {
        var newPopulation = if(current == 0) asymptote else current

        if (incident != null) {
            newPopulation = (newPopulation * (1.0 - incident.proportionOfPopulationKilled())).toInt()
        }

        val diff = asymptote - newPopulation
        val change = (diff * strength).toInt()
        return newPopulation + change
    }

}