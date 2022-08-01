package de.hsos.gaertner_kirkesler_knodt.game.population

/**
 *
 *
 * @author Gaertner
 */
interface PopulationAlg {
    fun evolve(round: Int, current: Int, incident: Incident): Int;
}

class Incident {

}
