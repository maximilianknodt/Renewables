package de.hsos.gaertner_kirkesler_knodt.game

/**
 * Interface mit Methode fuer die jeweiligen Produktionszustaende
 *
 * @author Knodt
 */
interface ProducerState {
    /**
     * Aendert den Zustand des Konstrukts
     *
     * @return den aktuellen Zustand
     */
    fun nextState() : ProducerState
}